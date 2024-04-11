package com.showdown.showdown_web.seed

import com.showdown.showdown_web.entity.Lesson
import com.showdown.showdown_web.utils.faker
import com.showdown.showdown_web.utils.newEntityId
import com.showdown.showdown_web.utils.setEntityId
import java.time.LocalDate
import java.time.LocalTime

fun createLesson(
    id: Long = faker.newEntityId(),
    level: String = faker.name.name(),
    date: LocalDate = LocalDate.now().plusDays(faker.random.nextLong(20)),
    startTime: LocalTime = LocalTime.of(
        faker.random.nextInt(intRange = 0..23),
        faker.random.nextInt(intRange = 0..59)
    ),
) = setEntityId(
    id,
    Lesson(
        date = date,
        startTime = startTime,
        endTime = startTime.plusHours(1).plusMinutes(20),
        academy = createRandomAcademy(),
        level = level,
    )
)