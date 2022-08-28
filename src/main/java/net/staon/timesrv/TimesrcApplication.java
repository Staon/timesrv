package net.staon.timesrv;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TimesrcApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        new TimesrcApplication()
            .configure(new SpringApplicationBuilder(TimesrcApplication.class))
            .run(args);
    }
}
