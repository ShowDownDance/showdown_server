package com.showdown.showdown_web.entity.Academy

import com.showdown.showdown_web.entity.common.BaseClass
import jakarta.persistence.*


@Entity
@Table(name = "academy")
class Academy(
    academyName: AcademyName,
    url: String
) : BaseClass() {
    @Convert(converter = AcademyNameConverter::class)
    @Column(name = "name" , nullable = false)
    var academyName: AcademyName = academyName
        protected set

    @Column(name = "url")
    var url: String = url
    override fun toString(): String {
        return "Academy(academyName=$academyName, url='$url')"
    }


}