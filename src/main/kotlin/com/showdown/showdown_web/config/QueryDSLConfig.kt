package com.showdown.showdown_web.config

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QueryDSLConfig {
    @Bean
    fun jpaQueryFactory(em: EntityManager) : JPAQueryFactory = JPAQueryFactory(em)
}