package com.showdown.showdown_web.api

import com.showdown.showdown_web.api.dto.LessonDto
import com.showdown.showdown_web.api.dto.LessonResponse
import com.showdown.showdown_web.api.dto.LessonResponse.DateAcademyLessonsDto
import com.showdown.showdown_web.api.dto.LessonResponse.AcademyLessonsDto
import com.showdown.showdown_web.api.utils.mockmvc.documentWithHandle
import com.showdown.showdown_web.api.utils.mockmvc.getWithQueryParameter
import com.showdown.showdown_web.api.utils.restdocs.*
import com.showdown.showdown_web.entity.Academy.AcademyName
import com.showdown.showdown_web.entity.Dancer
import com.showdown.showdown_web.entity.DancerLesson
import com.showdown.showdown_web.entity.Lesson
import com.showdown.showdown_web.seed.createDancer
import com.showdown.showdown_web.seed.createDancerLesson
import com.showdown.showdown_web.seed.createLesson
import io.mockk.every
import org.springframework.test.web.servlet.ResultActionsDsl
import java.time.LocalDate



class LessonControllerTest : WebMvcTestAdapter() {
    init {
        "특정 Date를 가지고 수업 정보를 받는다" {
            //given
            val specificDate : LocalDate = LocalDate.now()

            val findLesson: MutableList<Lesson> = mutableListOf()

            for (i in 0 .. 10) {
                val lesson = createLesson(date = specificDate)

                val dancerLesson: DancerLesson = createDancerLesson(
                    lesson = lesson,
                    dancer =  createDancer()
                )

                findLesson.add(dancerLesson.lesson)
            }



            val lessonResponse : List<LessonResponse> = listOf<AcademyLessonsDto>(
                AcademyLessonsDto(
                    academy = AcademyName.OneMillion.name,
                    lessons = findLesson.subList(0, 3).map { LessonDto.fromEntity(it) }
                ),
                AcademyLessonsDto(
                    academy = AcademyName.YGX.name,
                    lessons = findLesson.subList(3, 6).map { LessonDto.fromEntity(it) }
                ),
                AcademyLessonsDto(
                    academy = AcademyName.OFD.name,
                    lessons = findLesson.subList(6, 11).map { LessonDto.fromEntity(it) }
                )
            )

            every { lessonService.getLessonsTypeWithDate(date = specificDate.toString()) } returns lessonResponse

            // when
            val response: ResultActionsDsl = mockMvc.getWithQueryParameter("/class?type={type}&target={target}", RequestType.DATE, specificDate.toString())


            response.andExpect {
                status {
                    isOk()
                }
            }.andDo {
                documentWithHandle(
                    "get-specific-date-lesson",
                    queryParameters(
                        "type" means "검색 기준" example "DATE",
                        "target" means "구체적이 날짜" example "2024-05-15"
                    ),
                    responseBody(
                        "message" type STRING means "메세지",
                        "body" type ARRAY means "학원들 전체 목록",
                        "body[].academy" type STRING means "학원 이름" example "OneMillion",
                        "body[].lessons" type ARRAY means "특정 학원에 속한 수업 목록",
                        "body[].lessons[].startTime" type STRING means "수업 시작 시간" example "17:00",
                        "body[].lessons[].endTime" type STRING means "수업 종료 시간" example "18:20",
                        "body[].lessons[].dancer" type STRING means "수업 강사 이름" example "Amy Park",
                        "body[].lessons[].level" type STRING means "수업 난이도" example "뚝딱이클래스"
                    )
                )
            }
        }

        "현재 날짜를 기준으로 2주차 수업 정보를 받는다" {
            var specificDate: LocalDate = LocalDate.now()

            val lessons: MutableList<Lesson> = mutableListOf()

            val lessonResponse : MutableList<LessonResponse> = mutableListOf()


            for (i in 0L..14) {
                specificDate = specificDate.plusDays(1L)

                val data : List<AcademyLessonsDto> = listOf<AcademyLessonsDto>(
                    AcademyLessonsDto(
                        academy = AcademyName.OneMillion.name,
                        lessons = listOf(LessonDto.fromEntity(
                            lesson = createLesson(date=specificDate).apply {
                                createDancerLesson(
                                    lesson = this,
                                    dancer = createDancer()
                                ).lesson
                            })
                        )
                    ),
                    AcademyLessonsDto(
                        academy = AcademyName.YGX.name,
                        lessons = listOf(LessonDto.fromEntity(
                            lesson = createLesson(date=specificDate).apply {
                                createDancerLesson(
                                    lesson = this,
                                    dancer = createDancer()
                                ).lesson
                            })
                        )
                    ),
                    AcademyLessonsDto(
                        academy = AcademyName.OFD.name,
                        lessons = listOf(LessonDto.fromEntity(
                            lesson = createLesson(date=specificDate).apply {
                                createDancerLesson(
                                    lesson = this,
                                    dancer = createDancer()
                                ).lesson
                            })
                        )
                    )
                )

                val specificDateLessons = DateAcademyLessonsDto(
                    date = specificDate.toString(),
                    data = data
                )

                lessonResponse.add(specificDateLessons)
            }

            every { lessonService.getLessonsTypeWithDate() } returns lessonResponse

            val response: ResultActionsDsl = mockMvc.getWithQueryParameter("/class?type={type}", RequestType.DATE)

            response.andExpect {
                status {
                    isOk()
                }
            }.andDo {
                documentWithHandle(
                    "get-2week-lessons",
                    queryParameters(
                        "type" means "검색 기준" example "DATE"
                    ),
                    responseBody(
                        "message" type STRING means "메세지",
                        "body" type ARRAY means "학원들 전체 목록",
                        "body[].date" type STRING means "수업 날짜" example "2024-04-03",
                        "body[].data" type ARRAY means "수업 및 학원 정보 모음",
                        "body[].data[].academy" type STRING means "학원 이름" example "OneMillion",
                        "body[].data[].lessons" type ARRAY means "수업 정보",
                        "body[].data[].lessons[].startTime" type STRING means "수업 시작 시간" example "17:00",
                        "body[].data[].lessons[].endTime" type STRING means "수업 종료 시간" example "18:20",
                        "body[].data[].lessons[].dancer" type STRING means "수업 강사 이름" example "Amy Park",
                        "body[].data[].lessons[].level" type STRING means "수업 난이도" example "뚝딱이클래스"
                    )
                )
            }
        }
    }
}