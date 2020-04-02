package com.thiamath.yfnsoche.repositories

import com.thiamath.yfnsoche.model.Payslip
import java.time.LocalDate

interface PayslipsRepository {
    /**
     * Retrieve the payslips of the year and month from the given date.
     * Any retrieved payslip that is wrong formatted will be removed from the list.
     */
    fun getMonthPayslips(date: LocalDate): List<Payslip>
}