package com.showdown.showdown_web.api.utils.mockmvc

import org.springframework.restdocs.generate.RestDocumentationGenerator.ATTRIBUTE_NAME_URL_TEMPLATE
import org.springframework.test.web.servlet.*


fun MockMvc.getWithPathParameter(urlTemplate: String, vararg vars: Any?, dsl: MockHttpServletRequestDsl.() -> Unit = {}): ResultActionsDsl =
    this.get(urlTemplate, *vars) {
        dsl(this)
        requestAttr(ATTRIBUTE_NAME_URL_TEMPLATE, urlTemplate)
    }

fun MockMvc.getWithQueryParameter(urlTemplate: String, vararg vars: Any?, dsl: MockHttpServletRequestDsl.() -> Unit = {}): ResultActionsDsl =
    this.get(urlTemplate, *vars) {
//        param()
        dsl(this)
        requestAttr(ATTRIBUTE_NAME_URL_TEMPLATE, urlTemplate)
    }