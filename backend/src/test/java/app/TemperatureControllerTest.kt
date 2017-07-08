package app

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.time.LocalDateTime

class TemperatureControllerTest {

    private val mockTemperatureService: TemperatureService = mock()
    private lateinit var temperatureController: TemperatureController
    private lateinit var mockMvc: MockMvc

    @Before
    fun setUp() {
        temperatureController = TemperatureController(mockTemperatureService)
        mockMvc = MockMvcBuilders.standaloneSetup(temperatureController).build()
    }

    @Test
    fun getTemperature() {
        val temperature = Temperature(LocalDateTime.of(1, 2, 3, 4, 5), 21.74)
        whenever(mockTemperatureService.getTemperature()).thenReturn(temperature)

        mockMvc.perform(get("/temperature"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(APPLICATION_JSON_UTF8))

        verify(mockTemperatureService).getTemperature()
    }
}