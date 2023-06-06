package es.cesguiro.movies.config.impl;

import es.cesguiro.movies.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ConfigSpringImpl implements Config {

    @Autowired
    private Environment enviroment;

    @Override
    public String getUrlConnection() {

        return enviroment.getProperty("app.datasource.url");
    }

    @Override
    public String getDBUser() {
        return enviroment.getProperty("app.datasource.username");
    }

    @Override
    public String getDBPassword() {
        return enviroment.getProperty("app.datasource.password");
    }
}
