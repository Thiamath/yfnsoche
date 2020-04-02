package com.thiamath.yfnsoche.services

import java.math.BigDecimal

interface TaxService {
    /**
     * Modify the tax rate for the given year and month.
     */
    fun modifyTaxRate(taxRate: BigDecimal, year: Int, month: Int): BigDecimal?
}