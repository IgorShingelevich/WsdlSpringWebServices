package com.example.soapclient;

import com.example.soapclient.wsdl.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SoapClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoapClientApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            SoapClient client = ctx.getBean(SoapClient.class);

            AddResponse addResponse = client.add(2, 3);
            System.out.println("Add Result: " + addResponse.getAddResult());

            DivideResponse divideResponse = client.divide(10, 2);
            System.out.println("Divide Result: " + divideResponse.getDivideResult());

            MultiplyResponse multiplyResponse = client.multiply(3, 4);
            System.out.println("Multiply Result: " + multiplyResponse.getMultiplyResult());

            SubtractResponse subtractResponse = client.subtract(9, 5);
            System.out.println("Subtract Result: " + subtractResponse.getSubtractResult());
        };
    }
}
