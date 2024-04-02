package com.showdown.showdown_web.repository

import com.showdown.showdown_web.entity.Academy.AcademyName
import com.showdown.showdown_web.entity.Lesson
import java.time.LocalDate
import java.time.LocalTime

interface LessonRepositoryCustom {
    fun findLessonByDateAndStartTimeAndDancerName(
        date: LocalDate,
        startTime: LocalTime,
        dancerName: String
    ): List<Long>

    fun findLessonsByAcademyName(academyName: AcademyName) : List<Lesson>
}