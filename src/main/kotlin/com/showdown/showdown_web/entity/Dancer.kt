package com.showdown.showdown_web.entity

import com.showdown.showdown_web.entity.common.BaseClass
import jakarta.persistence.*
import org.hibernate.proxy.HibernateProxy

@Entity
@Table(name = "dancer")
class Dancer(
    name: String,
    crew: String?,
    dancerAcademies: List<DancerAcademy>
) : BaseClass() {
    var name: String = name
        protected set

    var crew: String? = crew
        protected set

    @OneToMany(mappedBy = "dancer")
    var dancerAcademies: List<DancerAcademy> = dancerAcademies
        protected set
}