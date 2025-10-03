package com.jeniatyt.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("chat")
class ChatProperties(val supportChatId: String)
