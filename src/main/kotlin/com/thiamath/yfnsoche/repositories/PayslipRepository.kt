package com.thiamath.yfnsoche.repositories

import com.thiamath.yfnsoche.model.Payslip

interface PayslipRepository {
    /**
     * Retrieve the payslips of the year and month from the given date.
     * Any retrieved payslip that is wrong formatted will be removed from the list.
     */
    fun getMonthPayslips(year: Int, month: Int): List<Payslip>
}