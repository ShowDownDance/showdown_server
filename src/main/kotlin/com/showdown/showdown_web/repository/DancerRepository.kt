package com.showdown.showdown_web.repository

import com.showdown.showdown_web.entity.Dancer
import org.springframework.data.jpa.repository.JpaRepository

interface DancerRepository : JpaRepository<Dancer, Long> {
    fun findDancerByName(name: String) : Dancer
}