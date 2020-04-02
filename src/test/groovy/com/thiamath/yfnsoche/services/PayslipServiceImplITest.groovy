package com.thiamath.yfnsoche.services

import com.thiamath.yfnsoche.model.Payslip
import com.thiamath.yfnsoche.repositories.PayslipRepository
import spock.lang.Specification

class PayslipServiceImplITest extends Specification {

    def 'When retrieving payslips after modify the tax rate, the payslips are modified accordingly'() {
        setup:
        def taxService = new TaxServiceImpl()
        def payslipService = new PayslipServiceImpl(
                Stub(PayslipRepository) {
                    getMonthPayslips(year, month) >> retrievedPayslips
                },
                taxService
        )
        and:
        taxService.modifyTaxRate(taxModification, year, month)
        expect:
        payslipService.getMonthPayslips(year, month) == modifiedPayslips

        where:
        year | month | retrievedPayslips          | taxModification || modifiedPayslips
        2018 | 12    | 'Valid list of payslips'() | BigDecimal.ZERO || 'List of modified payslips'()
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

//                               gross    NIR  Nat ded  TaxR Tax amou Net
// 00000000000197084172E20181231 00248600 0500 00012430 1200 00029832 00206337
