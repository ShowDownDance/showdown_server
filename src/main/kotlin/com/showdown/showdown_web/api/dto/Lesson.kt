package com.showdown.showdown_web.api.dto

import com.showdown.showdown_web.entity.Lesson
import java.time.format.DateTimeFormatter


sealed class LessonResponse() {
    data class DateAcademyLessonsDto(
        val date: String,
        val data: List<AcademyLessonsDto>
    ): LessonResponse()

    data class AcademyLessonsDto(
        val academy: String,
        val lessons: List<LessonDto>
    ): LessonResponse()

    data class AcademyDateLessonsDto(
        val academy: String,
        val data: List<DateLessonsDto>
    ): LessonResponse()

    data class DateLessonsDto(
        val date: String,
        val lessons: List<LessonDto>
    ): LessonResponse()
}



data class LessonDto(
    val startTime: String,
    val endTime: String,
    val dancer: String,
    val level: String
) {

    companion object {
        fun fromEntity(lesson: Lesson): LessonDto {
            var dancer: String = ""
            val dancersCount = lesson.lessonDancers.size
            for ((index, d) in lesson.lessonDancers.withIndex()) {
                dancer += d.dancer.name
                if (index != dancersCount - 1)
                    dancer += ", "
            }

            return LessonDto(
                startTime = lesson.startTime.format(DateTimeFormatter.ofPattern("H:mm")),
                endTime = lesson.endTime.format(DateTimeFormatter.ofPattern("H:mm")),
                dancer = dancer,
                level = lesson.level
            )
        }
    }
}
