package com.showdown.showdown_web.service.lesson

import com.showdown.showdown_web.api.dto.LessonDto
import com.showdown.showdown_web.api.dto.LessonResponse
import com.showdown.showdown_web.api.dto.LessonResponse.AcademyLessonsDto
import com.showdown.showdown_web.api.dto.LessonResponse.DateAcademyLessonsDto
import com.showdown.showdown_web.api.dto.LessonResponse.DateLessonsDto
import com.showdown.showdown_web.api.dto.LessonResponse.AcademyDateLessonsDto
import com.showdown.showdown_web.entity.Academy.AcademyName
import com.showdown.showdown_web.entity.Lesson
import com.showdown.showdown_web.repository.LessonRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class LessonService @Autowired constructor(
    private val lessonRepository: LessonRepository,
) {
    @Transactional
    fun getLessonsTypeWithAcademy(
        academyName: AcademyName?
    ) : List<LessonResponse> {
        if (academyName == null) {
            val today = LocalDate.now()
            val academiesLessons : MutableList<AcademyDateLessonsDto> = mutableListOf()

            val lessons: List<Lesson> = lessonRepository.findLessonsByDateBetweenOrderByDate(
                startDate = today,
                lastDate = today.plusDays(14)
            )

            for (academy in AcademyName.entries) {
                val specificAcademyLessons = lessons.filter {
                    it.academy.academyName == academy
                }

                academiesLessons.add(
                    AcademyDateLessonsDto(
                        academy = academy.name,
                        data = sortByDate(specificAcademyLessons = specificAcademyLessons)
                    )
                )
            }

            return academiesLessons
        }
        val lessons: List<Lesson> = lessonRepository.findLessonsByAcademyName(academyName=academyName)
        return sortByDate(specificAcademyLessons = lessons)
    }

    @Transactional
    fun getLessonsTypeWithDate(date: String? = null) : List<LessonResponse> {
        if (date == null) {
            val today = LocalDate.now()
            val lessons: List<Lesson> = lessonRepository.findLessonsByDateBetweenOrderByDate(
                startDate = today,
                lastDate = today.plusDays(14)
            )

            val dateAcademyLessonsDto: MutableList<DateAcademyLessonsDto> = mutableListOf()
            for (i in (0..14)) {
                val specificDate = today.plusDays(i.toLong())
                val specificDayLessons = lessons.filter {
                    it.date == specificDate
                }
                val lessonDetailInfoDto : List<AcademyLessonsDto> = sortByAcademy(specificDayLessons)

                dateAcademyLessonsDto.add(
                    DateAcademyLessonsDto(
                        date = specificDate.toString(),
                        data = lessonDetailInfoDto
                    )
                )
            }
            return dateAcademyLessonsDto
        }

        val specificDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE)
        val lessons: List<Lesson> = lessonRepository.findLessonByDateOrderByStartTime(date = specificDate)

        val lessonDetailInfoDto: List<AcademyLessonsDto> = sortByAcademy(lessons)
        return lessonDetailInfoDto
    }

    private fun sortByDate(specificAcademyLessons: List<Lesson>) : List<DateLessonsDto> {
        val result: MutableList<DateLessonsDto> = mutableListOf()
        val today: LocalDate = LocalDate.now()

        for (i in (0..14)) {
            val lessonsDto: MutableList<LessonDto> = mutableListOf()
            val specificDate = today.plusDays(i.toLong())
            val specificDayLessons = specificAcademyLessons.filter {
                it.date == specificDate
            }

            specificDayLessons.forEach {
                lessonsDto.add(LessonDto.fromEntity(it))
            }

            result.add(DateLessonsDto(
                date = specificDate.toString(),
                lessons = lessonsDto
            ))
        }

        return result
    }

    private fun sortByAcademy(specificDayLessons: List<Lesson>) : List<AcademyLessonsDto> {
        val oneMillionLessons = mutableListOf<LessonDto>()
        val ygxLessons = mutableListOf<LessonDto>()
        val ofdLessons = mutableListOf<LessonDto>()

        specificDayLessons.forEach {
            when (it.academy.academyName) {
                AcademyName.OneMillion -> oneMillionLessons.add(LessonDto.fromEntity(it))
                AcademyName.YGX -> ygxLessons.add(LessonDto.fromEntity(it))
                AcademyName.OFD -> ofdLessons.add(LessonDto.fromEntity(it))
            }
        }

        return listOf(
            AcademyLessonsDto(academy = AcademyName.OneMillion.name, lessons = oneMillionLessons.toList()),
            AcademyLessonsDto(academy = AcademyName.YGX.name, lessons = ygxLessons.toList()),
            AcademyLessonsDto(academy = AcademyName.OFD.name, lessons = ofdLessons.toList()),
        )
    }
}