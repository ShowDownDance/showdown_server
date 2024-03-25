package com.showdown.showdown_web.entity

import com.showdown.showdown_web.entity.common.BaseClass
import jakarta.persistence.*


@Entity
@Table(name = "academy")
class Academy(
    name: String,
) : BaseClass() {
    @Column(name = "name" , nullable = false)
    var name: String = name
        protected set
}