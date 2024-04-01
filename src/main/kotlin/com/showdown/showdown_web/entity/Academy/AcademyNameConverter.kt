package com.showdown.showdown_web.entity.Academy

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class AcademyNameConverter: AttributeConverter<AcademyName, String> {
    override fun convertToDatabaseColumn(academyName: AcademyName): String {
        return academyName.name
    }

    override fun convertToEntityAttribute(dbData: String): AcademyName {
        return AcademyName.valueOf(dbData)
    }
}