package com.thiamath.yfnsoche.services

import com.thiamath.yfnsoche.model.Payslip
import com.thiamath.yfnsoche.repositories.NotFoundRepositoryException
import com.thiamath.yfnsoche.repositories.PayslipRepository
import com.thiamath.yfnsoche.repositories.RepositoryException
import com.thiamath.yfnsoche.repositories.UnexpectedRepositoryException
import org.springframework.stereotype.Service
import java.math.RoundingMode

@Service
class PayslipServiceImpl(
        private val payslipRepository: PayslipRepository,
        private val taxService: TaxService
) : PayslipService {

    override fun getMonthPayslips(year: Int, month: Int): List<Payslip> =
            try {
                val payslips = payslipRepository.getMonthPayslips(year, month)
                val modifiedTaxRate = taxService.getTaxRate(year, month)
                if (modifiedTaxRate != null) {
                    payslips.map {
                        val newAmountTaxes = it.gross * modifiedTaxRate.movePointLeft(2)
                        it.copy(
                                taxRate = (modifiedTaxRate).setScale(2, RoundingMode.DOWN),
                                amountTaxes = newAmountTaxes.setScale(2, RoundingMode.DOWN),
                                net = (it.gross - it.amountNationalInsuranceDeductions - newAmountTaxes).setScale(2, RoundingMode.DOWN)
                        )
                    }
                } else {
                    payslips
                }
            } catch (e: RepositoryException) {
                when (e) {
                    is UnexpectedRepositoryException -> throw UnexpectedServiceException("Unexpected error while retrieving payslips", e)
                    is NotFoundRepositoryException -> throw NotFoundServiceException("Payslips not found", e)
                }
            }
}