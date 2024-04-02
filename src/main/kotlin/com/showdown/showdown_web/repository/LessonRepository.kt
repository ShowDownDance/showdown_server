package com.showdown.showdown_web.repository

import com.showdown.showdown_web.entity.Academy.Academy
import com.showdown.showdown_web.entity.Lesson
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface LessonRepository: JpaRepository<Lesson, Long>, LessonRepositoryCustom {

    @Query(
        "select l from Lesson l join fetch l.lessonDancers ld join fetch ld.dancer d " +
                "where l.date = :date order by l.startTime"
    )
    fun findLessonByDateOrderByStartTime(date: LocalDate) : List<Lesson>


    @Query("select l from Lesson l " +
            "join fetch l.lessonDancers ld join fetch ld.dancer d " +
            "where l.date between :startDate and :lastDate order by l.date, l.startTime"
    )
    fun findLessonsByDateBetweenOrderByDate(startDate: LocalDate, lastDate: LocalDate) : List<Lesson>
}