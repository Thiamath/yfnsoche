package com.thiamath.yfnsoche.model

import java.math.BigDecimal
import java.util.*

data class Payslip(
        val id: Long,
        val vatIdNumber: String,
        val date: Calendar,
        val gross: BigDecimal,
        val nationalInsuranceRate: BigDecimal,
        val amountNationalInsuranceDeductions: BigDecimal,
        val taxRate: BigDecimal,
        val amountTaxes: BigDecimal,
        val net: BigDecimal
) {
    companion object {
        @JvmStatic
        fun fromString(payslipString: String) = Payslip(
                id = payslipString.substring(0, 12).toLong(),
                vatIdNumber = payslipString.substring(12, 21),
                date = GregorianCalendar(payslipString.substring(21, 25).toInt(),
                        payslipString.substring(25, 27).toInt(),
                        payslipString.substring(27, 29).toInt()),
                gross = BigDecimal.valueOf(payslipString.substring(29, 35).toLong(),
                        payslipString.substring(35, 37).toInt()),
                nationalInsuranceRate = BigDecimal.valueOf(payslipString.substring(37, 39).toLong(),
                        payslipString.substring(39, 41).toInt()),
                amountNationalInsuranceDeductions = BigDecimal.valueOf(payslipString.substring(41, 47).toLong(),
                        payslipString.substring(47, 49).toInt()),
                taxRate = BigDecimal.valueOf(payslipString.substring(49, 51).toLong(),
                        payslipString.substring(51, 53).toInt()),
                amountTaxes = BigDecimal.valueOf(payslipString.substring(53, 59).toLong(),
                        payslipString.substring(59, 61).toInt()),
                net = BigDecimal.valueOf(payslipString.substring(61, 67).toLong(),
                        payslipString.substring(67, 69).toInt())
        )
    }
}