package com.thiamath.yfnsoche.services

import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class TaxServiceImpl : TaxService {
    override fun modifyTaxRate(taxRate: BigDecimal, year: Int, month: Int): BigDecimal {
        TODO("Not yet implemented")
    }
}