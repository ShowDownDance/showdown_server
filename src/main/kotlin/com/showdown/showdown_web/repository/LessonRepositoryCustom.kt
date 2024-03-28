package com.showdown.showdown_web.repository

import java.time.LocalDate
import java.time.LocalTime

interface LessonRepositoryCustom {
    fun findLessonByDateAndStartTimeAndDancerName(
        date: LocalDate,
        startTime: LocalTime,
        dancerName: String
    ): List<Long>
}