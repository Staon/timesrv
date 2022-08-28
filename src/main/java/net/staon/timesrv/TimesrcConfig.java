package net.staon.timesrv;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class TimesrcConfig extends ResourceConfig {
    public TimesrcConfig() {
        register(TimeSource.class);
    }
}
