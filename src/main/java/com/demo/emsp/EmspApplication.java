package com.demo.emsp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * @author muyoufang
 */
@SpringBootApplication
@EnableSwagger2
public class EmspApplication {

    private static final Logger logger = LoggerFactory.getLogger(EmspApplication.class);

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(EmspApplication.class, args);
        Environment env = application.getEnvironment();
        logger.info("\n-----------------------------------------------\n\t" +
                        "启动成功，访问地址:\n\t" +
                        "http://localhost:{}/swagger-ui.html\n\t" +
                        "---------------------------------------------",
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress());
    }

}
