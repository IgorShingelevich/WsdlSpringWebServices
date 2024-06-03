package com.example.soapclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

import javax.xml.namespace.QName;

@Configuration
public class SoapClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.example.soapclient.wsdl");
        return marshaller;
    }

    @Bean
    public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller marshaller) {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setMarshaller(marshaller);
        webServiceTemplate.setUnmarshaller(marshaller);
        webServiceTemplate.setInterceptors(new ClientInterceptor[]{securityInterceptor()});
        return webServiceTemplate;
    }

    @Bean
    public ClientInterceptor securityInterceptor() {
        return new ClientInterceptor() {
            @Override
            public boolean handleRequest(MessageContext messageContext) {
                SaajSoapMessage soapMessage = (SaajSoapMessage) messageContext.getRequest();
                try {
                    SoapHeaderElement headerElement = soapMessage.getSoapHeader().addHeaderElement(new QName("http://example.com", "Authorization"));
                    headerElement.setText("Bearer YOUR_TOKEN");

                    // Assert that the header is added
                    assert "Bearer YOUR_TOKEN".equals(headerElement.getText()) : "Authorization header is missing or incorrect";

                    System.out.println("Request header added: " + headerElement.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }

            @Override
            public boolean handleResponse(MessageContext messageContext) {
                // You can add assertion for response here if needed
                System.out.println("Response received");
                return true;
            }

            @Override
            public boolean handleFault(MessageContext messageContext) {
                System.out.println("Fault received");
                return true;
            }

            @Override
            public void afterCompletion(MessageContext messageContext, Exception ex) {
                // No implementation required
            }
        };
    }
}
