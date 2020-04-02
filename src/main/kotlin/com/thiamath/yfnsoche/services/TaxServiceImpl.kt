package com.thiamath.yfnsoche.services

import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.concurrent.ConcurrentHashMap

@Service
class TaxServiceImpl : TaxService {

    private val taxModificationMap = ConcurrentHashMap<String, BigDecimal>()

    override fun modifyTaxRate(taxRate: BigDecimal, year: Int, month: Int) =
            taxModificationMap.put(generateKey(year, month), taxRate)

    override fun getTaxRate(year: Int, month: Int) = taxModificationMap[generateKey(year, month)]

    private fun generateKey(year: Int, month: Int) = "$year$month"

}
