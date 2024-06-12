package com.showdown.showdown_web.seed

import com.showdown.showdown_web.entity.Academy.Academy
import com.showdown.showdown_web.entity.Academy.AcademyName
import com.showdown.showdown_web.utils.faker
import com.showdown.showdown_web.utils.newEntityId
import com.showdown.showdown_web.utils.setEntityId

fun createOneMillionAcademy(
    id: Long = 1L,
    url: String = faker.name.name()
) = setEntityId(
    id,
    Academy(
        academyName = AcademyName.OneMillion,
        url = url
    )
)

fun createYGXAcademy(
    id: Long = 2L,
    url: String = faker.name.name()
) = setEntityId(
    id,
    Academy(
        academyName = AcademyName.OneMillion,
        url = url
    )
)

fun createOFDAcademy(
    id: Long = 3L,
    url: String = faker.name.name()
) = setEntityId(
    id,
    Academy(
        academyName = AcademyName.OneMillion,
        url = url
    )
)

fun createAcademies() = listOf<Academy>(
    createOneMillionAcademy(),
    createYGXAcademy(),
    createOFDAcademy()
)

fun createRandomAcademy() : Academy {
    val randomAcademy : Academy = faker.randomProvider.randomClassInstance<Academy>()

    return randomAcademy
//    val academies = createAcademies()
//    return academies[faker.random.nextInt(0..2)]
}