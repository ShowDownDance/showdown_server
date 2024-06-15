package com.showdown.showdown_web.api.utils.restdocs

import org.springframework.restdocs.request.PathParametersSnippet
import org.springframework.restdocs.request.QueryParametersSnippet
import org.springframework.restdocs.request.RequestDocumentation.pathParameters
import org.springframework.restdocs.request.RequestDocumentation.queryParameters

fun pathParameters(vararg fields: ParameterField): PathParametersSnippet =
    pathParameters(fields.asList().map { it.type })

fun queryParameters(vararg fields: ParameterField): QueryParametersSnippet =
    queryParameters(fields.asList().map { it.type })