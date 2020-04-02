package com.thiamath.yfnsoche.services

import spock.lang.Specification

class TaxServiceImplTest extends Specification {

    def 'The tax is effectively modified'() {
        setup:
        def taxService = new TaxServiceImpl()
        expect: 'the taxes to be modified (the previous value was null)'
        taxService.modifyTaxRate(firstChange, year, month) is null
        and: 'the taxes to be modified again (the previous value was firstChange)'
        taxService.modifyTaxRate(secondChange, year, month) is firstChange
        where:
        year | month | firstChange    | secondChange
        2018 | 12    | BigDecimal.TEN | BigDecimal.ONE
    }
}
