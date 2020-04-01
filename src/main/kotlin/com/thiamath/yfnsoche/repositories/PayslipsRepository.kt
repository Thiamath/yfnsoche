package com.thiamath.yfnsoche.repositories

import com.thiamath.yfnsoche.model.Payslip
import java.time.LocalDate

interface PayslipsRepository {
    fun getMonthPayslips(date: LocalDate): List<Payslip>
}