package com.thiamath.yfnsoche.services

import com.thiamath.yfnsoche.model.Payslip
import com.thiamath.yfnsoche.repositories.PayslipRepository
import spock.lang.Specification

class PayslipServiceImplTest extends Specification {

    def '''If the tax is not modified, the payslips must be retrieved as is. If not, the payslips must be modified.'''() {
        setup:
        def payslipService = new PayslipServiceImpl(
                Stub(PayslipRepository) {
                    getMonthPayslips(year, month) >> payslipsFromRepository
                },
                Stub(TaxService) {
                    getTaxRate(year, month) >> modifiedTaxRate
                }
        )
        expect:
        payslipService.getMonthPayslips(year, month) == expectedPayslips

        where:
        year | month | payslipsFromRepository     | modifiedTaxRate || expectedPayslips
        2018 | 12    | 'Valid list of payslips'() | null            || 'Valid list of payslips'()
        2018 | 11    | 'Valid list of payslips'() | BigDecimal.ZERO || 'List of modified payslips'()
    }


    static def 'Valid list of payslips'() {
        return [
                Payslip.fromString('00000000000197084172E201812310024860005000001243012000002983200206337'),
                Payslip.fromString('00000000039745201136H201812310025280005000001264010000002528000214879'),
        ]
    }

    static def 'List of modified payslips'() {
        return [
                Payslip.fromString('00000000000197084172E201812310024860005000001243000000000000000236170'),
                Payslip.fromString('00000000039745201136H201812310025280005000001264000000000000000240160'),
        ]
    }
}
