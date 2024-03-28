package com.showdown.showdown_web.service

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class LessonDto(
    @JsonProperty("dancers")
    val dancers: List<DancerDto>,
    @JsonProperty("scheduleDate")
    private val _date: String,
    @JsonProperty("scheduleDay")
    val day: Int,
    @JsonProperty("startTime")
    private val _startTime: String,
    @JsonProperty("endTime")
    private val _endTime: String? = null,
    @JsonProperty("classLevel")
    val level: String,
    @JsonProperty("enterprise")
    val academy: String
) {
    val date: LocalDate
        get() = LocalDate.parse(_date, DateTimeFormatter.ISO_DATE)
    val startTime: LocalTime
        get() = LocalTime.parse(_startTime, DateTimeFormatter.ISO_LOCAL_TIME)

    val endTime: LocalTime
        get() = LocalTime.parse(_endTime, DateTimeFormatter.ISO_LOCAL_TIME)
}