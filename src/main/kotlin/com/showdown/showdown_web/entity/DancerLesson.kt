package com.showdown.showdown_web.entity

import com.showdown.showdown_web.entity.common.BaseClass
import jakarta.persistence.*

@Entity
@Table(name = "dancer_lesson")
class DancerLesson(
    dancer: Dancer,
    lesson: Lesson
) : BaseClass() {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dancer_id")
    var dancer: Dancer = dancer
        protected set


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    var lesson: Lesson = lesson
        protected set
}