package com.showdown.showdown_web.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.showdown.showdown_web.entity.Academy.AcademyName
import com.showdown.showdown_web.entity.Lesson
import com.showdown.showdown_web.entity.QDancerLesson.dancerLesson
import com.showdown.showdown_web.entity.QLesson.lesson
import com.showdown.showdown_web.entity.QDancer.dancer
import java.time.LocalDate
import java.time.LocalTime

class LessonRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory
): LessonRepositoryCustom {
    override fun findLessonByDateAndStartTimeAndDancerName(date: LocalDate, startTime: LocalTime, dancerName: String): List<Long> {
        return queryFactory
            .select(lesson.id)
            .from(lesson)
//            .innerJoin(lesson.lessonDancers, dancerLesson).on(lesson.id.eq(dancerLesson.lesson.id)).fetchJoin()
            .innerJoin(lesson.lessonDancers, dancerLesson).fetchJoin()
//            .innerJoin(dancerLesson.dancer, dancer).on(dancerLesson.dancer.id.eq(dancer.id)).fetchJoin()
            .innerJoin(dancerLesson.dancer, dancer).fetchJoin()
            .where(lesson.date.eq(date))
            .where(lesson.startTime.eq(startTime))
            .where(dancer.name.eq(dancerName))
            .fetch()
    }

    override fun findLessonsByAcademyName(academyName: AcademyName) : List<Lesson> {
        val today: LocalDate = LocalDate.now()
        return queryFactory
            .select(lesson)
            .from(lesson)
            .innerJoin(lesson.lessonDancers, dancerLesson).fetchJoin()
            .innerJoin(dancerLesson.dancer, dancer).fetchJoin()
            .where(lesson.academy.id.eq(academyName.id))
            .where(lesson.date.between(today, today.plusDays(14)))
            .orderBy(lesson.date.asc(), lesson.startTime.asc())
            .fetch()
    }
}