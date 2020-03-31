package com.thiamath.yfnsoche.repositories

import com.thiamath.yfnsoche.model.Payslip
import java.util.*

interface PayslipsRepository {
    fun getMonthPayslips(date: GregorianCalendar): List<Payslip>
}