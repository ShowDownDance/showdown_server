package com.showdown.showdown_web.entity.Academy

import com.showdown.showdown_web.entity.common.BaseClass
import jakarta.persistence.*


@Entity
@Table(name = "academy")
class Academy(
    academyName: AcademyName,
) : BaseClass() {
    @Convert(converter = AcademyNameConverter::class)
    @Column(name = "name" , nullable = false)
    var academyName: AcademyName = academyName
        protected set

    override fun toString(): String {
        return "Academy(name='$academyName')"
    }
}