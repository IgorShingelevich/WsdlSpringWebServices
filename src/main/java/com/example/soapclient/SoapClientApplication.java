package com.example.soapclient;

import com.example.soapclient.wsdl.AddResponse;
import com.example.soapclient.wsdl.DivideResponse;
import com.example.soapclient.wsdl.MultiplyResponse;
import com.example.soapclient.wsdl.SubtractResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SoapClientApplication {

    private static final Logger logger = LoggerFactory.getLogger(SoapClientApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SoapClientApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            SoapClient client = ctx.getBean(SoapClient.class);

            AddResponse addResponse = client.add(2, 3);
            logger.info("Add Result: {}", addResponse.getAddResult());

            DivideResponse divideResponse = client.divide(10, 2);
            logger.info("Divide Result: {}", divideResponse.getDivideResult());

            MultiplyResponse multiplyResponse = client.multiply(3, 4);
            logger.info("Multiply Result: {}", multiplyResponse.getMultiplyResult());

            SubtractResponse subtractResponse = client.subtract(9, 5);
            logger.info("Subtract Result: {}", subtractResponse.getSubtractResult());
        };
    }
}
