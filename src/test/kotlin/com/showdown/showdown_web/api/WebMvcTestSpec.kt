package com.showdown.showdown_web.api

import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.FreeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc
import org.springframework.test.context.web.WebAppConfiguration


//@ExtendWith(RestDocumentationExtension::class)
//@AutoConfigureRestDocs
@AutoConfigureWebMvc
@AutoConfigureMockMvc
@WebAppConfiguration
//@Suppress("SpringJavaAutowiredMembersInspection")
open class WebMvcTestSpec(body: FreeSpec.() -> Unit = {}) : FreeSpec(body) {
    override fun extensions(): List<Extension>  = listOf(SpringExtension)
}