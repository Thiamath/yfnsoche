package com.thiamath.yfnsoche.services

import com.thiamath.yfnsoche.model.Payslip

interface PayslipService {
    /**
     * Retrieve the payslips for a month and year simulating the company's payslip system.
     */
    @Throws(ServiceException::class)
    fun getMonthPayslips(year: Int, month: Int): List<Payslip>
}