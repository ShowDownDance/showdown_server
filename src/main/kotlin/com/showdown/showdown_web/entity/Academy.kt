package com.showdown.showdown_web.entity

import com.showdown.showdown_web.entity.common.BaseClass
import jakarta.persistence.*


@Entity
@Table(name = "academy")
class Academy(
    name: String,
    academyDancers: MutableSet<DancerAcademy>
) : BaseClass() {
    @Column(name = "name" , nullable = false)
    var name: String = name
        protected set

    @OneToMany(mappedBy = "academy")
    var academyDancers: MutableSet<DancerAcademy> = academyDancers
        protected set
}