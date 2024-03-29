package com.showdown.showdown_web.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.type.CollectionLikeType
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.showdown.showdown_web.entity.*
import com.showdown.showdown_web.repository.*
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import java.io.File
import org.springframework.stereotype.Service
import java.io.IOException
import java.time.LocalDate

@Service
class SchedulerService @Autowired constructor(
    private val dancerRepository: DancerRepository,
    private val academyRepository: AcademyRepository,
    private val lessonRepository: LessonRepository,
    private val dancerLessonRepository: DancerLessonRepository,
    @Value("\${spring.crawl.location}")
    private val crawledLocation : String
) {
    private val mapper: ObjectMapper = jacksonObjectMapper()
    private val dancersType: CollectionLikeType = mapper.typeFactory.constructCollectionLikeType(Set::class.java, Dancer::class.java)
    private val lessonsType: CollectionLikeType = mapper.typeFactory.constructCollectionLikeType(List::class.java, LessonDto::class.java)

    @Scheduled(cron = "0 20 0 1/1 * *")
    @Transactional
    fun saveCrawledData() {
        val date: LocalDate = LocalDate.now()
        val today: Int = date.dayOfMonth
        val yesterday: Int = today - 1

        val yesterdayData: List<LessonDto> = readJsonData(yesterday)
        val todayData: List<LessonDto> = readJsonData(today)

        if (todayData.isEmpty()) {
            //python 에서 안된 것
            throw Exception("crawled failed")
        }

        val savedDancers: List<Dancer> = dancerRepository.findAll()
        val academies: List<Academy> = academyRepository.findAll()

        if (yesterdayData.isEmpty()) {
            // 새로운 데이터 중 중복으로 겹치는 dancer data 삭제
            val newDancersDto: Set<DancerDto> = findNewDancerFromNewDatas(newData = todayData, savedDancers = savedDancers)
            newDancersDto.forEach {
                val dancer = dancerRepository.save(
                    it.toEntity()
                )
            }

            todayData.forEach {
                val lesson = lessonRepository.save(
                    Lesson(
                        date = it.date,
                        startTime = it.startTime,
                        endTime = it.endTime,
                        level = it.level,
                        academy = findAcademy(it.academy, academies)
                    )
                )

                it.dancers.forEach {d ->
                    val dancer = dancerRepository.findDancerByName(name=d.name)

                    dancerLessonRepository.save(
                        DancerLesson(
                            lesson = lesson,
                            dancer = dancer
                        )
                    )
                }
            }
            return
        }

        val newData : List<LessonDto> = findNewDatas(
            todayCrawledDatas = todayData,
            yesterdayCrawledDatas = yesterdayData
        )
        val deletedData: List<LessonDto> = findDeletedDatas(
            todayCrawledDatas = todayData,
            yesterdayCrawledDatas = yesterdayData,
            todayDate = date
        )

        val newDancerDto: Set<DancerDto> = findNewDancerFromNewDatas(newData = newData, savedDancers = savedDancers)
        newDancerDto.forEach {
            val dancer = dancerRepository.save(
                it.toEntity()
            )
        }

        newData.forEach {
            val lesson = lessonRepository.save(
                Lesson(
                    date = it.date,
                    startTime = it.startTime,
                    endTime = it.endTime,
                    level = it.level,
                    academy = findAcademy(it.academy, academies)
                )
            )

            it.dancers.forEach {d ->
                val dancer = dancerRepository.findDancerByName(name=d.name)

                dancerLessonRepository.save(
                    DancerLesson(
                        lesson = lesson,
                        dancer = dancer
                    )
                )
            }
        }

        deletedData.forEach { l ->
            l.dancers.forEach { d ->
                val lessonIds = lessonRepository.findLessonByDateAndStartTimeAndDancerName(
                    date = l.date,
                    startTime = l.startTime,
                    dancerName = d.name
                )

                lessonIds.forEach { id ->
                    lessonRepository.deleteById(id)
                }
            }
        }

        return
    }

    private fun findAcademy(enterpriseName: String, academies: List<Academy>): Academy {
        academies.forEach {
            if (it.name.equals(enterpriseName))
                return it
        }

        // TODO:: 확정적으로 값을 내놓는 것으로 바꾸보자
        return academies[0]
    }

    private fun findNewDatas(todayCrawledDatas: List<LessonDto>, yesterdayCrawledDatas: List<LessonDto>) : List<LessonDto> {
        val newData: MutableList<LessonDto> = mutableListOf()


        // find new data
        todayCrawledDatas.forEach {
            if(!yesterdayCrawledDatas.contains(it))
                newData.add(it)
        }

        return newData
    }

    private fun findNewDancerFromNewDatas(newData : List<LessonDto>, savedDancers: List<Dancer>) : Set<DancerDto> {
        val savedDancersDto: MutableSet<DancerDto> = mutableSetOf()

        savedDancers.forEach {
            savedDancersDto.add(
                DancerDto.fromEntity(dancer = it)
            )
        }

        val newDancerDto: Set<DancerDto> = newData.flatMap {
            it.dancers
        }.filter {
            !savedDancersDto.contains(it)
        }.toSet()

        return newDancerDto
    }

    private fun findDeletedDatas(
        todayCrawledDatas: List<LessonDto>,
        yesterdayCrawledDatas: List<LessonDto>,
        todayDate: LocalDate
    ) : List<LessonDto> {
        val deletedData: MutableList<LessonDto> = mutableListOf()

        yesterdayCrawledDatas.forEach {
            if (!todayCrawledDatas.contains(it))
                if (it.date != todayDate.minusDays(1))
                    deletedData.add(it)
        }
        return deletedData
    }

    private fun readJsonData(day: Int): List<LessonDto> {
        val file : File = File("$crawledLocation/items_$day.json")

        if (!file.isFile)
            return listOf()

        try {
            val lessonsDto: List<LessonDto> = mapper.readValue(file, lessonsType)

            return lessonsDto
        } catch (e: IOException) {
            throw IOException()
        }

    }
}