package com.mkyong.component;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MyComponent {

    private Environment environment;

    public String value;

    public MyComponent() {
        log.info("MyComponent in constructor: [{}]", value); // (0) displays: Null
    }

    public void postConstruct() {
        log.info("MyComponent in postConstruct: [{}]", value); // (1) displays: Magic
    }

    public void afterPropertiesSet() {
        log.info("MyComponent in afterPropertiesSet: [{}]", value);  // (2) displays: Magic
    }   

    public void preDestroy() {
        log.info("MyComponent in preDestroy: [{}]", value); // (3) displays: Magic
    }
}