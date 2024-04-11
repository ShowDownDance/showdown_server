package com.showdown.showdown_web.api

import com.showdown.showdown_web.api.dto.ApiResponse
import com.showdown.showdown_web.api.dto.ApiResponse.Companion.success
import com.showdown.showdown_web.api.dto.LessonResponse
import com.showdown.showdown_web.entity.Academy.AcademyName
import com.showdown.showdown_web.service.lesson.LessonService
import com.showdown.showdown_web.service.scheduler.SchedulerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class LessonController @Autowired constructor(
    private val schedulerService: SchedulerService,
    private val lessonService: LessonService
) {

    @PostMapping("/crawled-data")
    fun saveData() {
        schedulerService.saveCrawledData()
    }

    @GetMapping("/class")
    fun getLessons(
        @RequestParam type: RequestType,
        @RequestParam(required = false) target: String?
    ) : ApiResponse<List<LessonResponse>> {
        val lessons =  when (type) {
            RequestType.DATE -> lessonService.getLessonsTypeWithDate(date = target)
            RequestType.ACADEMY -> lessonService.getLessonsTypeWithAcademy(
                academyName = AcademyName.convertStringToAcademyName(target)
            )
        }
        return success(lessons)
    }
}