package com.showdown.showdown_web.api.utils.mockmvc

import com.showdown.showdown_web.utils.documentRequest
import com.showdown.showdown_web.utils.documentResponse
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.snippet.Snippet
import org.springframework.test.web.servlet.MockMvcResultHandlersDsl

fun MockMvcResultHandlersDsl.documentWithHandle(
    identifier: String,
    vararg snippets: Snippet
): Unit = handle(
    document(
        identifier,
        documentRequest,
        documentResponse,
        *snippets
    )
)