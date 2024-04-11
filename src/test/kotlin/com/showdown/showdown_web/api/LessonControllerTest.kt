package com.showdown.showdown_web.api

import com.showdown.showdown_web.api.dto.LessonDto
import com.showdown.showdown_web.api.dto.LessonResponse
import com.showdown.showdown_web.api.dto.LessonResponse.DateLessonsDto
import com.showdown.showdown_web.api.dto.LessonResponse.AcademyLessonsDto
import com.showdown.showdown_web.entity.Dancer
import com.showdown.showdown_web.entity.Lesson
import com.showdown.showdown_web.seed.createDancer
import com.showdown.showdown_web.seed.createLesson
import io.mockk.every
import org.springframework.test.web.servlet.MockMvc
import java.time.LocalDate


class LessonControllerTest: WebMvcTestAdapter() {
    init {

    }
//    init {
//        "특정 Date를 가지고 수업 정보를 받는다" {
//            val specificDate : LocalDate = LocalDate.now()
//
//            val lessons : MutableList<Lesson> = mutableListOf()
//            val findLesson: MutableList<Lesson> = mutableListOf()
//            val dancer: Dancer = createDancer()
//
//            for (i in 0 .. 10) {
//                val lesson = createLesson()
//                lessons.add(lesson)
//                dancer.addLesson(lesson)
//            }
//            for (i in 0 .. 10) {
//                val lesson = createLesson(date = specificDate)
//                findLesson.add(lesson)
//                dancer.addLesson(lesson)
//            }
//            val lessonDto: List<LessonDto> = listOf(
//                LessonDto(
//                    dancer = "abc",
//                    startTime = "abc",
//                    endTime = "abc",
//                    level = "abc"
//                )
//            )
//            val lessonResponse : List<LessonResponse> = listOf<AcademyLessonsDto>(
//                AcademyLessonsDto(
//                    academy = "YGX",
//                    lessons = lessonDto.toMutableList()
//                )
//            )
//
//            every { lessonService.getLessonsTypeWithDate(date = specificDate.toString()) } returns lessonResponse
//
////            val response =
//        }
//    }
}