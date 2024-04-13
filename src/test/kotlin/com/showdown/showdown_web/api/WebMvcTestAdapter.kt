package com.showdown.showdown_web.api

import com.showdown.showdown_web.service.lesson.LessonService
import com.showdown.showdown_web.service.scheduler.SchedulerService
import io.kotest.core.spec.style.FreeSpec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.mockk.clearMocks
import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@WebMvcTest(
    controllers = [
        LessonController::class
    ]
)
open class WebMvcTestAdapter(body: FreeSpec.() -> Unit = {}): WebMvcTestSpec(body) {

    @MockkBean
    protected lateinit var lessonService: LessonService

    @MockkBean
    protected lateinit var schedulerService: SchedulerService

    override suspend fun afterAny(testCase: TestCase, result: TestResult) {
        super.afterTest(testCase, result)
        clearMocks(
            lessonService,
            schedulerService
        )
    }
}