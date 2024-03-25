package com.showdown.showdown_web.repository

import com.showdown.showdown_web.entity.Academy
import org.springframework.data.jpa.repository.JpaRepository

interface AcademyRepository : JpaRepository<Academy, Long> {
}