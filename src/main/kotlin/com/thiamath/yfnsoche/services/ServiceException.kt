package com.thiamath.yfnsoche.services

sealed class ServiceException(message: String, cause: Throwable? = null) : Exception(message, cause)

class UnexpectedServiceException(message: String, cause: Throwable? = null) : ServiceException(message, cause)
class NotFoundServiceException(message: String, cause: Throwable? = null) : ServiceException(message, cause)