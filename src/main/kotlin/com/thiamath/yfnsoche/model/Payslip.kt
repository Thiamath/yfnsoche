package com.thiamath.yfnsoche.model

import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
    fun toPayslipString() = "$id".padStart(12, '0') +
            vatIdNumber +
            date.format(DateTimeFormatter.ofPattern("yyyyMMdd")) +
            gross.movePointRight(AMOUNT_DECIMALS).toPlainString().padStart(8, '0') +
            nationalInsuranceRate.movePointRight(AMOUNT_DECIMALS).toPlainString().padStart(4, '0') +
            amountNationalInsuranceDeductions.movePointRight(AMOUNT_DECIMALS).toPlainString().padStart(8, '0') +
            taxRate.movePointRight(AMOUNT_DECIMALS).toPlainString().padStart(4, '0') +
            amountTaxes.movePointRight(AMOUNT_DECIMALS).toPlainString().padStart(8, '0') +
            net.movePointRight(AMOUNT_DECIMALS).toPlainString().padStart(8, '0')


    companion object {
        const val PAYSLIP_STRING_LENGTH = 69
        const val AMOUNT_DECIMALS = 2

        /**
         * This method is a convenient method to create a [Payslip] from a String.
         * It will assume that the given string is correctly formatted and will create BigDecimal
         * objects according to that. If the string format has not been checked, assume possible
         * [ArrayIndexOutOfBoundsException] or [NumberFormatException] to occur.
         * @param payslipString The payslip String. It will keep the following structure:
         * * 12 chars -> ID
         * * 9 chars -> Vat, IdNumber
         * * 8 chars -> Date (Format: YYYYMMDD)
         * * 8 chars -> Gross (6 integers + 2 decimals)
         * * 4 chars -> % National Insurance Rate (2 integers + 2 decimals)
         * * 8 chars -> Amount National Insurance deductions (6 integers + 2 decimals)
         * * 4 chars -> % Tax Rate (2 integers + 2 decimals)
         * * 8 chars -> Amount Taxes (6 integers + 2 decimals)
         * * 8 chars -> Net (6 integers + 2 decimals)
         */
        @JvmStatic
        fun fromString(payslipString: String) = Payslip(
                id = payslipString.substring(0, 12).toLong(),
                vatIdNumber = payslipString.substring(12, 21),
                date = LocalDate.of(payslipString.substring(21, 25).toInt(),
                        payslipString.substring(25, 27).toInt(),
                        payslipString.substring(27, 29).toInt()),
                gross = BigDecimal.valueOf(payslipString.substring(29, 37).toLong(), AMOUNT_DECIMALS),
                nationalInsuranceRate = BigDecimal.valueOf(payslipString.substring(37, 41).toLong(), AMOUNT_DECIMALS),
                amountNationalInsuranceDeductions = BigDecimal.valueOf(payslipString.substring(41, 49).toLong(), AMOUNT_DECIMALS),
                taxRate = BigDecimal.valueOf(payslipString.substring(49, 53).toLong(), AMOUNT_DECIMALS),
                amountTaxes = BigDecimal.valueOf(payslipString.substring(53, 61).toLong(), AMOUNT_DECIMALS),
                net = BigDecimal.valueOf(payslipString.substring(61, 69).toLong(), AMOUNT_DECIMALS)
        )
    }
}
