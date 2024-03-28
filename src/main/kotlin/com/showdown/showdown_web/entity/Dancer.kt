package com.showdown.showdown_web.entity

import com.showdown.showdown_web.entity.common.BaseClass
import jakarta.persistence.*

@Entity
@Table(name = "dancer")
class Dancer(
    name: String,
    crew: String? = null,
    dancerLessons: MutableSet<DancerLesson> = mutableSetOf()
) : BaseClass() {
    @Column(name = "name", unique = true)
    var name: String = name
        protected set

    @Column(name = "crew", nullable = true)
    var crew: String? = crew
        protected set

    @OneToMany(mappedBy = "dancer", fetch = FetchType.LAZY)
    var dancerLessons: MutableSet<DancerLesson> = dancerLessons
        protected set

    override fun toString(): String {
//        return "Dancer(id=$id, name='$name', crew=$crew, " +
//                "dancerAcademies=$dancerAcademies, dancerLessons=$dancerLessons)"
        return "Dancer(id=$id, name='$name', crew=$crew)"
    }
}