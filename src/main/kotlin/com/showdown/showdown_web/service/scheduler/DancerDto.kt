package com.showdown.showdown_web.service.scheduler

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

        //TODO:: 이게 여기 들어가는게 맞는지 고민해보기 새로운 객체를 하나 파야되는지
        fun toEntities(dancers: Set<DancerDto>): Set<Dancer> = dancers.map {
            it.toEntity()
        }.toSet()

    }

    fun toEntity(): Dancer = Dancer(name = this.name, crew = this.crew)
}