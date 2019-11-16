package com.vega.springit.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("springit")
public class SpringitProperties {

    /**
     * This is my welcome message.. (properties demo)
     */
    private String welcomeMsg = "DefaultMessage: 'Hello World'";

    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    public void setWelcomeMsg(String welcomeMsg) {
        this.welcomeMsg = welcomeMsg;
    }
}
