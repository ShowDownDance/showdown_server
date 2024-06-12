package com.showdown.showdown_web.utils

import com.showdown.showdown_web.entity.common.BaseClass
import jakarta.persistence.Id
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.superclasses
import kotlin.reflect.jvm.javaField

fun <T: BaseClass, U> setEntityId(entityId: U, entity: T): T {
    val kClass = entity.javaClass.kotlin

    val idSuperClass = kClass.superclasses.find {
        it.simpleName.equals("BaseClass")
    }   ?: throw IllegalStateException("주어진 엔티티에 @Id 필드가 존재하지 않습니다")

    val idField = idSuperClass.declaredMemberProperties.find {
        it.javaField?.getAnnotation(Id::class.java) != null
    } ?: throw IllegalStateException("주어진 엔티티에 @Id 필드가 존재하지 않습니다")
    idField.javaField?.trySetAccessible()
    idField.javaField?.set(entity, entityId)
    return entity
}