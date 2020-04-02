package com.thiamath.yfnsoche.controllers

interface PayslipController {
    /**
     * Retrieve the payslips for a month and year simulating the company's payslip sistem.
     */
    fun getMonthPayslips(month: Int, year: Int): String
}