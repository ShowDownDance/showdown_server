package com.showdown.showdown_web.entity

import jakarta.persistence.*

@Entity
@Table(name = "DANCERACADEMY")
class DancerAcademy(
    dancer: Dancer,
    academy: Academy
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @ManyToOne
    @JoinColumn(name = "id")
    var dancer: Dancer = dancer
        protected set


    @ManyToOne
    @JoinColumn(name = "id")
    var academy: Academy = academy
        protected set
}