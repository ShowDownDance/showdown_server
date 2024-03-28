package com.showdown.showdown_web.service

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.showdown.showdown_web.entity.Dancer

data class DancerDto(
    @JsonProperty("name")
    val name: String,
    @JsonProperty("crew")
    val crew: String? = null
) {

    companion object {
        fun fromEntity(dancer: Dancer) : DancerDto = DancerDto(
            name = dancer.name,
            crew = dancer.crew
        )
    }

    fun toEntity(): Dancer = Dancer(name = this.name, crew = this.crew)

}