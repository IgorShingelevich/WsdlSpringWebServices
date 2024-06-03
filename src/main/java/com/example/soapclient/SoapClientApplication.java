package com.example.soapclient;

import com.example.soapclient.wsdl.AddResponse;
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
            //1 configure client
            //2 construct the  request
            // 3 add headers to request .addHeader(Key, Value) method
            // 4 send request
            // 5 assert response
            SoapClient client = ctx.getBean(SoapClient.class);
            AddResponse response = client.add(2, 3);
            System.out.println("Result: " + response.getAddResult());
        };
    }
}
