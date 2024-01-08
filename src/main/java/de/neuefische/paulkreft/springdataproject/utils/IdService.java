package de.neuefische.paulkreft.springdataproject.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

// The stereotype annotation @Component specifies to Spring that this class is a bean, that means, Spring will
// Scan our application for classes annotated with @Component
// Instantiate them and inject any specified dependencies into them
// Inject them wherever needed

// Other, more specific stereotype annotations include: @Controller, @Service or @Repository
// some of them have specific additional functions, some act merely as an alias for @Component
@Component
public class IdService {
    public String randomId() {
        return UUID.randomUUID().toString();
    }
}
