package com.showdown.showdown_web.entity

import jakarta.persistence.*


@Entity
@Table(name = "ACADEMY")
class Academy(
    name: String,
    dancerAcademies: List<DancerAcademy>
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0

    var name: String = name
        protected set

    @OneToMany(mappedBy = "academy")
    var dancerAcademies: List<DancerAcademy> = dancerAcademies
        protected set
}