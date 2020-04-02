package com.thiamath.yfnsoche.controllers

import com.thiamath.yfnsoche.model.TaxUpdate
import com.thiamath.yfnsoche.services.TaxService
import org.springframework.stereotype.Controller

@Controller
class TaxControllerImpl(
        private val taxService: TaxService
) : TaxController {
    override fun modifyTaxRate(taxUpdate: TaxUpdate): Boolean {
        TODO("Not yet implemented")
    }
}