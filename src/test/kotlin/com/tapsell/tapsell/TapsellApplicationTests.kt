package com.tapsell.tapsell

import com.google.gson.Gson
import com.tapsell.tapsell.controller.Controller
import com.tapsell.tapsell.repository.AppStatisticsRepository
import com.tapsell.tapsell.responses.GetStatItem
import com.tapsell.tapsell.responses.GetStatResponse
import com.tapsell.tapsell.service.AppStatisticsService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(Controller::class)
class TapsellApplicationTests {

	@TestConfiguration
	class ControllerTestConfig {
		@Bean
		fun service() = mockk<AppStatisticsService>()
		@Bean
		fun repository() = mockk<AppStatisticsRepository>()
	}

	@Autowired
	private lateinit var mockMvc: MockMvc

	@Autowired
	private lateinit var service: AppStatisticsService

	@Autowired
	private lateinit var repository : AppStatisticsRepository

	@Test
	fun `getStats returns correct message`() {
		val expectedMessage = listOf(GetStatItem(18, 1395, 1595, 934, 898),
				GetStatItem(19,1395,3485,1508,937))

		every { service.getByDate(startDate="1395-05-01" , endDate="1395-05-11" , type=1) } returns expectedMessage

		val result = mockMvc.perform(get("/api/AppStatistics/getStats?startDate=1395-05-01&endDate=1395-05-11&type=1"))
				.andExpect(status().isOk)
				.andDo(print())
				.andReturn()

		var gson = Gson()
		assertEquals(gson.toJson(GetStatResponse(expectedMessage)), result.response.contentAsString)
		verify { service.getByDate(startDate="1395-05-01" , endDate="1395-05-11" , type=1) }
	}

}
