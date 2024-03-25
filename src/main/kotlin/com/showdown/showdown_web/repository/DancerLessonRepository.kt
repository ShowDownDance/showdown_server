package com.showdown.showdown_web.repository

import com.showdown.showdown_web.entity.DancerLesson
import org.springframework.data.jpa.repository.JpaRepository

interface DancerLessonRepository: JpaRepository<DancerLesson, Long> {
}