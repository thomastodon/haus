package app

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TemperatureRepository : JpaRepository<Temperature, Long> {

    fun save(temperature: Temperature)

    fun findById(id: Long?): Temperature

    @Query(value = "SELECT * FROM temperature ORDER BY time DESC LIMIT 1", nativeQuery = true)
    fun findLatest(): Temperature
}
