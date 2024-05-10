package com.example.Lab65;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


@Configuration
@PropertySource("classpath:config.properties")
public class PropertySourceDemo implements InitializingBean {

    @Value("${jdbc.id}")
    private String id;

    @Value("${jdbc.fullname}")
    private String name;

    @Value("${jdbc.factory}")
    private String factory;

    @Value("${jdbc.idea}")
    private String idea;
    @Autowired
    Environment env;

    @Override
    public void afterPropertiesSet() throws Exception {
        setDatabaseConfig();
    }

    private void setDatabaseConfig() {
        DataSourceConfig config = new DataSourceConfig();
        config.setId(env.getProperty("jdbc.id"));
        config.setName(env.getProperty("jdbc.fullname"));
        config.setFactory(env.getProperty("jdbc.factory"));
        config.setIdea(env.getProperty("jdbc.idea"));
        System.out.println(config.toString());
    }
}