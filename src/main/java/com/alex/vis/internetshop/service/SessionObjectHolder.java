package com.alex.vis.internetshop.service;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
public class SessionObjectHolder {
    private long amountOfClicks = 0;

    public SessionObjectHolder() {
        System.out.println("Session object created");
    }

    public void addClick() {
        amountOfClicks++;
    }
}
