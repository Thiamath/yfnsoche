package com.thiamath.yfnsoche.controllers

import com.thiamath.yfnsoche.model.TaxUpdate
import com.thiamath.yfnsoche.services.TaxService
import spock.lang.Specification

class TaxControllerImplTest extends Specification {

    def 'The tax controller calls the Taxservice'() {
        setup:
        def taxService = Mock(TaxService)
        def taxController = new TaxControllerImpl(
                taxService
        )
        when: 'the tax controller receives the petition to modify the tax'
        taxController.modifyTaxRate(new TaxUpdate(BigDecimal.ZERO, 2018, 1))
        then: ' the tax service is effectively called'
        1 * taxService.modifyTaxRate(_, _, _)
    }
}
