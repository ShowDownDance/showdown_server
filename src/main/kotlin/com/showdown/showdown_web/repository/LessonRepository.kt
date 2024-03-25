package com.showdown.showdown_web.repository

import com.showdown.showdown_web.entity.Lesson
import org.springframework.data.jpa.repository.JpaRepository

interface LessonRepository: JpaRepository<Lesson, Long> {
}