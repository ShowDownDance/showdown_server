package com.showdown.showdown_web.entity

import com.showdown.showdown_web.entity.common.BaseClass
import jakarta.persistence.*

@Entity
@Table(name = "dancer")
class Dancer(
    name: String,
    crew: String?,
    dancerAcademies: MutableSet<DancerAcademy>,
    dancerLessons: MutableSet<DancerLesson>
) : BaseClass() {
    var name: String = name
        protected set

    var crew: String? = crew
        protected set

    @OneToMany(mappedBy = "dancer")
    var dancerAcademies: MutableSet<DancerAcademy> = dancerAcademies
        protected set

    @OneToMany(mappedBy = "dancer")
    var dancerLessons: MutableSet<DancerLesson> = dancerLessons
        protected set
}