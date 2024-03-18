package com.showdown.showdown_web.entity

import jakarta.persistence.*
import org.hibernate.proxy.HibernateProxy

@Entity
@Table(name = "DANCER")
class Dancer(
    name: String,
    crew: String?,
    dancerAcademies: List<DancerAcademy>
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0

    var name: String = name
        protected set

    var crew: String? = crew
        protected set

    @OneToMany(mappedBy = "dancer")
    var dancerAcademies: List<DancerAcademy> = dancerAcademies
        protected set


    final override fun equals(other: Any?): Boolean {
        if (this == other) return true

        if (other == null) return false

        val oEffectiveClass = getEffectiveClass(other)
        val thisEffectiveClass = getEffectiveClass(this)
        if (thisEffectiveClass  != oEffectiveClass) return false

        other as Dancer

        return id != 0L && id == other.id
    }

    final override fun hashCode(): Int {
        return super.hashCode()
    }

    private fun getEffectiveClass(obj: Any?): Class<*> =
        if (obj is HibernateProxy) (this as HibernateProxy).hibernateLazyInitializer.persistentClass else this.javaClass
}