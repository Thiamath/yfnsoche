package com.thiamath.yfnsoche.model

import java.math.BigDecimal

data class TaxUpdate(
        val taxValue: BigDecimal?,
        val year: Int,
        val month: Int
)