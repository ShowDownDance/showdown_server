package com.showdown.showdown_web

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.type.CollectionLikeType
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.showdown.showdown_web.entity.Dancer
import com.showdown.showdown_web.service.scheduler.LessonDto
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.io.File
import java.io.IOException
import java.time.LocalDate

@SpringBootTest
class ShowdownWebApplicationTests {
	private val mapper: ObjectMapper = jacksonObjectMapper()
	private val lessonsType: CollectionLikeType = mapper.typeFactory.constructCollectionLikeType(List::class.java, LessonDto::class.java)
	@Test
	fun contextLoads() {
		val day: Int = LocalDate.now().dayOfMonth
//		val yesterday: Int = today - 1
		val yesterdayFile : File = File("/Users/devel_sj/showdown_web/items_19.json")
		val todayFile : File = File("/Users/devel_sj/showdown_web/items_20.json")

		try {
			val yesterdayData: List<LessonDto> = mapper.readValue(yesterdayFile, lessonsType)
			val todayData: List<LessonDto> = mapper.readValue(todayFile, lessonsType)

//			val savedDancers: MutableSet<DancerDto> = mutableSetOf<DancerDto>()
			val newData: MutableList<LessonDto> = mutableListOf()

			todayData.forEach {
				if (!yesterdayData.contains(it))
					newData.add(it)
			}
			println(newData.size)



		} catch (e: IOException) {
			println(e.message)
			throw IOException()
		}
	}

}
