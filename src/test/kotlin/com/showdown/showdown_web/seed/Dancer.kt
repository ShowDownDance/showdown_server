package com.showdown.showdown_web.seed

import com.showdown.showdown_web.entity.Dancer
import com.showdown.showdown_web.entity.DancerLesson
import com.showdown.showdown_web.utils.faker
import com.showdown.showdown_web.utils.newEntityId
import com.showdown.showdown_web.utils.setEntityId

fun createDancer(
    id : Long = faker.newEntityId(),
    name: String = faker.name.name(),
    crew: String = faker.name.name(),
) = setEntityId(
    id,
    Dancer(
        name = name,
        crew = crew,
    )
)