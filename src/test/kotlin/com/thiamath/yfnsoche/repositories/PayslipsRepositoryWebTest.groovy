package com.thiamath.yfnsoche.repositories

import com.thiamath.yfnsoche.model.Payslip
import spock.lang.Specification

class PayslipsRepositoryWebTest extends Specification {

    static def payslipsRepo = new PayslipsRepositoryWeb()

    def 'The repository must retrieve payslips from the web endpoint'() {
        given: 'a date to test'
        def testDate = new GregorianCalendar(year, month, 1)
        when: 'asking for the payslips to the repository'
        def payslips = payslipsRepo.getMonthPayslips(testDate)
        then: 'the retrieved payslips must be valid'
        payslips[0] == Payslip.fromString(payslipString)
        where:
        year | month || payslipString
        2018 | 12    || '0000000197084172E201801310026000015380004000007690002000000200000'
    }
}
