package com.thiamath.yfnsoche.repositories

import com.thiamath.yfnsoche.Configuration
import com.thiamath.yfnsoche.model.Payslip
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import java.time.LocalDate

class PayslipsRepositoryWebTest extends Specification {

    def 'The repository must retrieve payslips from the web endpoint'() {
        setup:
        PayslipsRepositoryWeb payslipsRepo = new PayslipsRepositoryWeb(
                Stub(Configuration),
                Stub(RestTemplate) {
                    getForObject(_ as String, _ as Class<String>, _) >> payslipString
                }
        )
        def testDate = LocalDate.of(year, month, 1)
        when: 'asking for the payslips to the repository'
        def payslips = payslipsRepo.getMonthPayslips(testDate)
        then: 'the retrieved payslips must be valid'
        payslips[0] == Payslip.fromString(payslipString)
        where:
        year | month || payslipString
        2018 | 12    || '00000000147874920733G201812310017630001000000176319000003349700141040'
    }

    def 'If the retrieved payslips are wrong formatted, those will be removed'() {
        setup:
        PayslipsRepositoryWeb payslipsRepo = new PayslipsRepositoryWeb(
                Stub(Configuration),
                Stub(RestTemplate) {
                    getForObject(_ as String, _ as Class<String>, _) >> payslipString
                }
        )
        def testDate = LocalDate.of(year, month, 1)
        when: 'asking for the payslips to the repository'
        def payslips = payslipsRepo.getMonthPayslips(testDate)
        then: 'the retrieved payslips must be empty'
        payslips.empty
        where:
        year | month || payslipString
        2018 | 12    || 'not_a_payslip_string'
    }
}
