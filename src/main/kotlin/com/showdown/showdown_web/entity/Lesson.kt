package com.showdown.showdown_web.entity

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalTime

@Entity
@Table(name = "lesson")
class Lesson(
    dancer: Dancer,
    date: LocalDate,
    startTime: LocalTime,
    endTime: LocalTime,
    level: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    var date: LocalDate = date
        protected set

    var startTime: LocalTime = startTime
        protected set

    var endTime: LocalTime = endTime
        protected set

    var dayOfWeek: Int = date.dayOfWeek.value
        protected set

    var level: String = level
        protected set

    @ManyToOne
    @JoinColumn(name = "id")
    var dancer: Dancer = dancer
        protected set
}