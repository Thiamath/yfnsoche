package com.thiamath.yfnsoche.controllers

import com.thiamath.yfnsoche.model.TaxUpdate

interface TaxController {
    fun modifyTaxRate(taxUpdate: TaxUpdate): Boolean
}