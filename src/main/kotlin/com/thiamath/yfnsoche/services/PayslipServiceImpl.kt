package com.thiamath.yfnsoche.services

import com.thiamath.yfnsoche.model.Payslip
import com.thiamath.yfnsoche.repositories.NotFoundRepositoryException
import com.thiamath.yfnsoche.repositories.PayslipRepository
import com.thiamath.yfnsoche.repositories.RepositoryException
import com.thiamath.yfnsoche.repositories.UnexpectedRepositoryException
import org.springframework.stereotype.Service

@Service
class PayslipServiceImpl(
        private val payslipRepository: PayslipRepository
) : PayslipService {

    override fun getMonthPayslips(year: Int, month: Int): List<Payslip> =
            try {
                payslipRepository.getMonthPayslips(year, month)
            } catch (e: RepositoryException) {
                when (e) {
                    is UnexpectedRepositoryException -> throw UnexpectedServiceException("Unexpected error while retrieving payslips", e)
                    is NotFoundRepositoryException -> throw NotFoundServiceException("Payslips not found", e)
                }
            }
}