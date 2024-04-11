package com.showdown.showdown_web

import io.kotest.core.NamedTag

const val integrationTestTag = "IntegrationTest"

fun String.tag() = NamedTag(this)