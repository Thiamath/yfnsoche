package com.thiamath.yfnsoche.controllers

import com.thiamath.yfnsoche.model.TaxUpdate
import com.thiamath.yfnsoche.services.TaxService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("tax")
class TaxControllerImpl(
        private val taxService: TaxService
) : TaxController {

    @PutMapping
    @ResponseBody
    fun modifyTaxRateMapping(@RequestBody taxUpdate: TaxUpdate): ResponseEntity<Any> =
            try {
                modifyTaxRate(taxUpdate)
                ResponseEntity.ok().build()
            } catch (e: Exception) {
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
            }

    override fun modifyTaxRate(taxUpdate: TaxUpdate) =
            taxService.modifyTaxRate(taxUpdate.taxValue, taxUpdate.year, taxUpdate.month)
}
