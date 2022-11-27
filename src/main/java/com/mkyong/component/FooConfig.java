package com.mkyong.component;


import lombok.Data;
import lombok.NonNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "my.foo")
@ConstructorBinding
public class FooConfig {
    @NonNull
    private final String bar;
}