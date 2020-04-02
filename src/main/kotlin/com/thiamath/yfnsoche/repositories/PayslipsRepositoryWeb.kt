package com.thiamath.yfnsoche.repositories

import com.thiamath.yfnsoche.Configuration
import com.thiamath.yfnsoche.model.Payslip
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import java.time.LocalDate
import java.util.Optional

@Repository
class PayslipsRepositoryWeb(
        val configuration: Configuration,
        val restTemplate: RestTemplate
) : PayslipsRepository {
    private val log = LoggerFactory.getLogger(PayslipsRepositoryWeb::class.java)

    /**
     * Retrieve the payslips of the year and month from the given date.
     * Any retrieved payslip that is wrong formatted will be removed from the list
     * and logged accordingly.
     *
     * @param date A [LocalDate] with the year and month to be queried.
     * @return A [List] of [Payslip] with the retrieved payslips.
     */
    override fun getMonthPayslips(date: LocalDate): List<Payslip> {
        return Optional.ofNullable(restTemplate.getForObject(configuration.retrievalUrl, String::class.java, "date", "${date.year}${date.month}"))
                .orElseGet { "" }
                .split("\n")
                .mapNotNull {
                    try {
                        Payslip.fromString(it)
                    } catch (e: Exception) {
                        log.error("The payslip string {} for the date {} contains errors", it, date)
                        null
                    }
                }
    }
}