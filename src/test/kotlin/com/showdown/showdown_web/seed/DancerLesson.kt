package com.showdown.showdown_web.seed

import com.showdown.showdown_web.entity.Dancer
import com.showdown.showdown_web.entity.DancerLesson
import com.showdown.showdown_web.entity.Lesson
import com.showdown.showdown_web.utils.faker
import com.showdown.showdown_web.utils.newEntityId
import com.showdown.showdown_web.utils.setEntityId

// 질문하기
fun createDancerLesson(
    id: Long = faker.newEntityId(),
    dancer: Dancer,
    lesson: Lesson,
): DancerLesson   {
    val dancerLesson =  DancerLesson(
        dancer = dancer,
        lesson = lesson
    )
    lesson.lessonDancers.add(dancerLesson)
    dancer.dancerLessons.add(dancerLesson)


    return setEntityId(
        id,
        DancerLesson(
            dancer = dancer,
            lesson = lesson
        )
    )
}