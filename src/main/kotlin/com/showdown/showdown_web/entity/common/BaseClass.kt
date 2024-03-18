package com.showdown.showdown_web.entity.common

import com.showdown.showdown_web.entity.Dancer
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.proxy.HibernateProxy

@MappedSuperclass
abstract class BaseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0


    final override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (other == null) return false

        val oEffectiveClass = getEffectiveClass(other)
        val thisEffectiveClass = getEffectiveClass(this)
        if (thisEffectiveClass  != oEffectiveClass) return false

        other as Dancer

        return id != 0L && id == other.id
    }

    final override fun hashCode(): Int = getEffectiveClass(this).hashCode()


    private fun getEffectiveClass(obj: Any?): Class<*> =
        if (obj is HibernateProxy)
            (this as HibernateProxy).hibernateLazyInitializer.persistentClass
        else
            this.javaClass
}