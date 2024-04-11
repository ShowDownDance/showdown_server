package com.showdown.showdown_web.utils

import io.github.serpro69.kfaker.Faker
import io.github.serpro69.kfaker.faker


fun createFaker() : Faker = faker {
    fakerConfig {
        locale = "ko"
    }
}

val faker = createFaker()

fun Faker.newEntityId() = this.random.nextLong(123456789)