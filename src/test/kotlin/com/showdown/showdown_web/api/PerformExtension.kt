package com.showdown.showdown_web.api

import org.springframework.test.web.servlet.*


fun MockMvc.getWithPathParameter(urlTemplate: String, vararg vars: Any?, dsl: MockHttpServletRequestDsl.() -> Unit = {}): ResultActionsDsl =
    this.get(urlTemplate, *vars) {
        dsl(this)
//        requestAttr(ATTRIBUTE_NAME_URL_TEMPLATE, urlTemplate)
    }