package com.showdown.showdown_web.entity

import com.showdown.showdown_web.entity.common.BaseClass
import jakarta.persistence.*
import jakarta.persistence.FetchType.LAZY

@Entity
@Table(name = "dancer_academy")
class DancerAcademy(
    dancer: Dancer,
    academy: Academy
) : BaseClass() {
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "dancer_id")
    var dancer: Dancer = dancer
        protected set


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "academy_id")
    var academy: Academy = academy
        protected set
}