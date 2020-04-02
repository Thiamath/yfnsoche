package com.thiamath.yfnsoche.controllers

import com.thiamath.yfnsoche.model.Payslip
import org.springframework.http.HttpStatus
import spock.lang.Specification

class PayslipControllerImplTest extends Specification {

    def 'The Payslip retrieval must return a valid string'() {
        setup:
        def payslipController = new PayslipControllerImpl()
        when: 'the controlled is asked for a valid file'
        def response = payslipController.getMonthPayslips(date)
        then: 'it answers with a legacy formatted string'
        response.hasBody()
        for (line in response.body.split("\n")) {
            line.length() == Payslip.PAYSLIP_STRING_LENGTH
        }
        where:
        date     | _
        '201812' | _
    }

    def 'The Payslip retrieval must check that the request is valid'() {
        setup:
        def payslipController = new PayslipControllerImpl()
        when: 'the controlled is asked for a not valid or unexistent file'
        def response = payslipController.getMonthPayslips(date)
        then: 'it answers with a not found response (the file does not exist)'
        response.statusCode == HttpStatus.NOT_FOUND
        where:
        date      | _
        '2018'    | _
        '2020001' | _
        '202014'  | _
        'YYYYMM'  | _
        '000000'  | _
        ''        | _
    }
}
