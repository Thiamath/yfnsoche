package com.thiamath.yfnsoche.controllers

interface PayslipController {
    /**
     * Retrieve the payslips for a month and year simulating the company's payslip system.
     */
    fun getMonthPayslips(date: String): String
}