package com.showdown.showdown_web

import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.FreeSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.boot.test.context.SpringBootTest
import io.kotest.core.annotation.Tags


@Tags(integrationTestTag)
@SpringBootTest
class ShowdownWebApplicationTests: FreeSpec({
	"init Test" {

	}
}) {
	override fun extensions(): List<Extension> = listOf(SpringExtension)
}
