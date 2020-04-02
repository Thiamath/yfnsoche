package com.thiamath.yfnsoche.repositories

import com.thiamath.yfnsoche.Configuration
import com.thiamath.yfnsoche.model.Payslip
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import java.util.Optional

@Repository
class PayslipRepositoryWeb(
        private val configuration: Configuration,
        private val restTemplate: RestTemplate
) : PayslipRepository {
    private val log = LoggerFactory.getLogger(PayslipRepositoryWeb::class.java)

    /**
     * Retrieve the payslips of the year and month from the given date.
     * Any retrieved payslip that is wrong formatted will be removed from the list
     * and logged accordingly.
     *
     * @param year The year to query.
     * @param month The month to query.
     * @return A [List] of [Payslip] with the retrieved payslips.
     */
    override fun getMonthPayslips(year: Int, month: Int): List<Payslip> =
            try {
                Optional.ofNullable(restTemplate.getForObject(configuration.retrievalUrl, String::class.java, "$year$month"))
                        .orElseGet { "" }
                        .split("\n")
                        .mapNotNull {
                            try {
                                Payslip.fromString(it)
                            } catch (e: Exception) {
                                log.error("The payslip string {} for the year {} and month contains errors", it, year, month)
                                null
                            }
                        }
            } catch (e: RestClientException) {
                if (e is HttpClientErrorException.NotFound) {
                    throw NotFoundRepositoryException("File not found", e)
                }
                throw UnexpectedRepositoryException("Error when retrieving payslips from server", e)
            }
}