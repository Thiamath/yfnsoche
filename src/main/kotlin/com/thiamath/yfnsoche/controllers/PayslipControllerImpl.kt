package com.thiamath.yfnsoche.controllers

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class PayslipControllerImpl : PayslipController {

    /**
     * Retrieve month payslips simulating the legacy system (As stated in the exercise).
     */
    @GetMapping("payslips.{date}.txt")
    fun getMonthPayslips(@PathVariable date: String): ResponseEntity<String> {
        TODO("Not yet implemented")
    }

    override fun getMonthPayslips(month: Int, year: Int): String {
        TODO("Not yet implemented")
    }
}