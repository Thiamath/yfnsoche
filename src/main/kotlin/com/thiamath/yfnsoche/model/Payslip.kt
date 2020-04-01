package com.thiamath.yfnsoche.model

import java.math.BigDecimal
import java.time.LocalDate

const val PAYSLIP_STRING_LENGTH = 69

data class Payslip(
        val id: Long,
        val vatIdNumber: String,
        val date: LocalDate,
        val gross: BigDecimal,
        val nationalInsuranceRate: BigDecimal,
        val amountNationalInsuranceDeductions: BigDecimal,
        val taxRate: BigDecimal,
        val amountTaxes: BigDecimal,
        val net: BigDecimal
) {
    companion object {
        /**
         * This method is a convenient method to create a [Payslip] from a String.
         * It will assume that the given string is correctly formatted and will create BigDecimal
         * objects according to that. If the string format has not been checked, assume possible
         * [ArrayIndexOutOfBoundsException] or [NumberFormatException] to occur.
         * @param payslipString The payslip String. It will keep the following structure:
         * * 12 chars 👉 ID
         * * 9 chars 👉 Vat, IdNumber
         * * 8 chars 👉 Date (Format: YYYYMMDD)
         * * 8 chars 👉 Gross (6 integers + 2 decimals)
         * * 4 chars 👉 % National Insurance Rate (2 integers + 2 decimals)
         * * 8 chars 👉 Amount National Insurance deductions (6 integers + 2 decimals)
         * * 4 chars 👉 % Tax Rate (2 integers + 2 decimals)
         * * 8 chars 👉 Amount Taxes (6 integers + 2 decimals)
         * * 8 chars 👉 Net (6 integers + 2 decimals)
         */
        @JvmStatic
        fun fromString(payslipString: String) = Payslip(
                id = payslipString.substring(0, 12).toLong(),
                vatIdNumber = payslipString.substring(12, 21),
                date = LocalDate.of(payslipString.substring(21, 25).toInt(),
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
