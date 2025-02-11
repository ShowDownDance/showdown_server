package com.showdown.showdown_web.api.utils.restdocs

import com.showdown.showdown_web.api.utils.restdocs.RestDocs.DEFAULT_VALUE_KEY
import com.showdown.showdown_web.api.utils.restdocs.RestDocs.EXAMPLE_KEY
import com.showdown.showdown_web.api.utils.restdocs.RestDocs.FORMAT_KEY
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath

object optional

class Field(
    var type: FieldDescriptor
) {
    init {
        type.attributes[DEFAULT_VALUE_KEY] = ""
        type.attributes[EXAMPLE_KEY] = ""
        type.attributes[FORMAT_KEY] = ""
    }

    infix fun means(description: String): Field {
        type = type.description(description)
        return this
    }

    infix fun example(value: String): Field {
        type.attributes[EXAMPLE_KEY] = value
        return this
    }

    infix fun example(value: Number): Field = example(value.toString())
    infix fun example(value: Boolean): Field = example(value.toString())
    infix fun example(value: List<Number>): Field = example(value.joinToString(", ", "[", "]"))

    infix fun and(x: optional): Field {
        type = type.optional()
        return this
    }

    infix fun default(value: String): Field {
        type.attributes[DEFAULT_VALUE_KEY] = value
        return this
    }

    infix fun default(value: Number): Field = default(value.toString())

    infix fun formattedAs(value: String): Field {
        type.attributes[FORMAT_KEY] = value
        return this
    }
}

infix fun String.type(fieldType: DocsFieldType): Field = when (fieldType) {
    is ENUM<*> -> Field(fieldWithPath(this).type(fieldType.type)).example(fieldType.enums.joinToString(" or "))
    is ENUM_ARRAY<*> -> Field(fieldWithPath(this).type(fieldType.type)).example(fieldType.enums.joinToString(" or ", "an Array of Enum[", "]"))
    is DATETIME -> Field(fieldWithPath(this).type(fieldType.type)).formattedAs("yyyy-MM-dd HH:mm:ss")
    is DATE -> Field(fieldWithPath(this).type(fieldType.type)).formattedAs("yyyy-MM-dd")
    else -> Field(fieldWithPath(this).type(fieldType.type))
}