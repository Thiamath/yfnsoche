package com.thiamath.yfnsoche.controllers

import com.thiamath.yfnsoche.model.Payslip
import com.thiamath.yfnsoche.services.NotFoundServiceException
import com.thiamath.yfnsoche.services.PayslipService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class PayslipControllerImpl(
        private val payslipService: PayslipService
) : PayslipController {
    private val log = LoggerFactory.getLogger(PayslipControllerImpl::class.java)

    /**
     * Retrieve month payslips simulating the legacy system (As stated in the exercise).
     */
    @GetMapping("payslips.{date}.txt")
    fun getMonthPayslipsMapping(@PathVariable date: String): ResponseEntity<String> {
        if (date.length != 6 || date.toIntOrNull() == null || date.substring(4, 6).toInt() !in 1..12) {
            //The return must be 404 NOT FOUND instead of 400 BAD REQUEST to simulate the legacy system behavior
            // which is a file download. A bad formatted date in the URL would mean that the file does not exist.
            log.error("Error when parsing date {} using formay yyyyMM", date)
            return ResponseEntity.notFound().build()
        }
        return try {
            ResponseEntity.ok(getMonthPayslips(date))
        } catch (e: NotFoundServiceException) {
            log.warn("Payslips file not found for date {}", date, e)
            ResponseEntity.notFound().build()
        } catch (e: Exception) {
            log.error("Error while retrieving payslips for date {}", date, e)
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.localizedMessage)
        }
    }

    override fun getMonthPayslips(date: String) =
            payslipService.getMonthPayslips(date.substring(0, 4).toInt(), date.substring(4, 6).toInt())
                    .asSequence()
                    .map(Payslip::toPayslipString)
                    .joinToString("\n")
}