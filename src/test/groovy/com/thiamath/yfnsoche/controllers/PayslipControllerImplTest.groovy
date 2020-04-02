package com.thiamath.yfnsoche.controllers

import com.thiamath.yfnsoche.model.Payslip
import com.thiamath.yfnsoche.services.NotFoundServiceException
import com.thiamath.yfnsoche.services.PayslipService
import com.thiamath.yfnsoche.services.UnexpectedServiceException
import org.springframework.http.HttpStatus
import spock.lang.Specification
import spock.lang.Unroll

class PayslipControllerImplTest extends Specification {

    def 'The Payslip retrieval must return a valid string'() {
        setup:
        def payslipController = new PayslipControllerImpl(
                Stub(PayslipService) {
                    getMonthPayslips(_ as int, _ as int) >> serviceResponse
                }
        )
        when: 'the controlled is asked for a valid file'
        def response = payslipController.getMonthPayslipsMapping(date)
        then: 'it answers with a legacy formatted string'
        response.hasBody()
        for (line in response.body.split("\n")) {
            line.length() == Payslip.PAYSLIP_STRING_LENGTH
        }
        where:
        date     || serviceResponse
        '201812' || 'Valid list of payslips'()
    }

    @Unroll
    def 'The Payslip retrieval must check that the request is valid using #date'() {
        setup:
        def payslipController = new PayslipControllerImpl(
                Stub(PayslipService)
        )
        when: 'the controlled is asked for a not valid or unexistent file'
        def response = payslipController.getMonthPayslipsMapping(date)
        then: 'it answers with a not found response (the file does not exist)'
        response.statusCode == responseCode
        where:
        date      || responseCode
        '2018'    || HttpStatus.NOT_FOUND
        '2020001' || HttpStatus.NOT_FOUND
        '202014'  || HttpStatus.NOT_FOUND
        'YYYYMM'  || HttpStatus.NOT_FOUND
        '000000'  || HttpStatus.NOT_FOUND
        ''        || HttpStatus.NOT_FOUND
        '201812'  || HttpStatus.OK
    }

    def 'The Payslip retrieval must answer with valid status codes'() {
        setup:
        def payslipController = new PayslipControllerImpl(
                Stub(PayslipService) {
                    getMonthPayslips(year, month) >> {
                        if (stubResponse instanceof Exception)
                            throw stubResponse
                        stubResponse
                    }
                }
        )
        when: 'the controlled is asked for a not valid or unexistent file'
        def response = payslipController.getMonthPayslipsMapping(String.format("%04d%02d", year, month))
        then: 'it answers with a not found response (the file does not exist)'
        response.statusCode == responseCode
        where:
        year | month || stubResponse                             | responseCode
        2018 | 12    || 'Valid list of payslips'()               | HttpStatus.OK
        2120 | 01    || new NotFoundServiceException("", null)   | HttpStatus.NOT_FOUND
        2120 | 02    || new UnexpectedServiceException("", null) | HttpStatus.INTERNAL_SERVER_ERROR
    }

    static def 'Valid list of payslips'() {
        return [
                Payslip.fromString('00000000000197084172E201812310024860005000001243012000002983200206337'),
                Payslip.fromString('00000000039745201136H201812310025280005000001264010000002528000214879'),
                Payslip.fromString('00000000052396433625F201812310022170004000000886816000003547200177360'),
                Payslip.fromString('00000000252358914324P201812310015060004000000602411000001656600128010'),
                Payslip.fromString('00000000020924093908M201812310014020001000000140213000001822600120572'),
                Payslip.fromString('00000000031519633248B201812310010570001000000105712000001268400091959'),
                Payslip.fromString('00000000042760016472L201812310026130005000001306513000003396900214266'),
                Payslip.fromString('00000000042892428750F201812310019300001000000193016000003088000160190'),
                Payslip.fromString('00000000146823749703H201812310018630003000000558919000003539700145314'),
                Payslip.fromString('00000000147874920733G201812310017630001000000176319000003349700141040'),
        ]
    }
}
