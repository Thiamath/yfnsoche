package com.thiamath.yfnsoche.controllers

import com.thiamath.yfnsoche.model.TaxUpdate
import java.math.BigDecimal

interface TaxController {
    fun modifyTaxRate(taxUpdate: TaxUpdate): BigDecimal?
}