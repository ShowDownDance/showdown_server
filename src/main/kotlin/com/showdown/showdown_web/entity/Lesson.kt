package com.showdown.showdown_web.entity

import com.showdown.showdown_web.entity.Academy.Academy
import com.showdown.showdown_web.entity.common.BaseClass
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalTime

@Entity
@Table(name = "lesson")
class Lesson(
    date: LocalDate,
    startTime: LocalTime,
    endTime: LocalTime,
    level: String,
    lessonDancers: MutableSet<DancerLesson> = mutableSetOf(),
    academy: Academy
) : BaseClass() {

    @Column(name = "lesson_date")
    var date: LocalDate = date
        protected set

    @Column(name = "start_time")
    var startTime: LocalTime = startTime
        protected set

    @Column(name = "end_time")
    var endTime: LocalTime = endTime
        protected set

    @Column(name = "day_of_week")
    var dayOfWeek: Byte = date.dayOfWeek.value.toByte()
        protected set

    @Column(name = "lesson_level")
    var level: String = level
        protected set

    @OneToMany(mappedBy = "lesson", orphanRemoval = true)
    var lessonDancers: MutableSet<DancerLesson> = lessonDancers
        protected set

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "academy_id")
    var academy: Academy = academy
        protected set
    override fun toString(): String {
        return "Lesson(" +
                "id=$id, " +
                "date=$date, " +
                "startTime=$startTime, " +
                "endTime=$endTime, " +
                "dayOfWeek=$dayOfWeek, " +
                "level='$level', " +
//                "lessonDancers=$lessonDancers, " +
                "academy=$academy" +
                ")"
    }

    // TODO :: filter를 하는 기능이 도메인 기능인지 고민해보기
//    companion object {
//        fun filterByAcademy(lessons:List<Lesson>, academy: Academy): List<Lesson> = lessons.filter { it.academy.academyName == academy.academyName }
//        fun filterByDate(lessons: List<Lesson>, specificDate: LocalDate) = lessons.filter { it.date == specificDate }
//    }
}