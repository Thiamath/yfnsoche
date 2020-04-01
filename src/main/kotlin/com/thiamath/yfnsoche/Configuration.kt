package com.thiamath.yfnsoche

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("application.yfnsoche")
data class Configuration(
        /** Retreieval URI for the payslips files. */
        var retrievalUrl: String = "default"
)