package com.thiamath.yfnsoche.repositories

sealed class RepositoryException(message: String, cause: Throwable? = null) : Exception(message, cause)

class UnexpectedRepositoryException(message: String, cause: Throwable? = null) : RepositoryException(message, cause)
class NotFoundRepositoryException(message: String, cause: Throwable? = null) : RepositoryException(message, cause)