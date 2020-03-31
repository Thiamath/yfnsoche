package com.thiamath.yfnsoche.repositories

import com.thiamath.yfnsoche.model.Payslip
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class PayslipsRepositoryWeb : PayslipsRepository {
    override fun getMonthPayslips(date: GregorianCalendar): List<Payslip> {
        TODO("Not yet implemented")
    }
}