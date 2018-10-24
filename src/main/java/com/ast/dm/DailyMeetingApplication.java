package com.ast.dm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DailyMeetingApplication extends SpringBootServletInitializer {
    /* Sobrecargar 'configure' es necesario para que la app funcione como un jar ejecutable y un war deployable */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DailyMeetingApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(DailyMeetingApplication.class, args);
    }
}
