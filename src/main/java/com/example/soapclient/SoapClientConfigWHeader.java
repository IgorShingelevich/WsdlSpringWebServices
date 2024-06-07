package com.example.soapclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.client.support.interceptor.ClientInterceptorAdapter;

import javax.xml.namespace.QName;
import java.util.Iterator;

@Configuration
public class SoapClientConfigWHeader {

    private static final String HEADER_NAMESPACE = "http://tempuri.org/";
    private static final String HEADER_NAME = "username";
    private static final String HEADER_VALUE = "myUserName";

    private static final Logger logger = LoggerFactory.getLogger(SoapClientConfigWHeader.class);

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
        webServiceTemplate.setDefaultUri("http://www.dneonline.com/calculator.asmx");
        webServiceTemplate.setInterceptors(new ClientInterceptor[]{new ClientInterceptorAdapter() {
            @Override
            public boolean handleRequest(MessageContext messageContext) {
                addHeader((SoapMessage) messageContext.getRequest());
                return true;
            }
        }});
        return webServiceTemplate;
    }

    private void addHeader(SoapMessage soapMessage) {
        SoapHeaderElement headerElement = soapMessage.getSoapHeader().addHeaderElement(new QName(HEADER_NAMESPACE, HEADER_NAME));
        headerElement.setText(HEADER_VALUE);
        logger.info("Added SOAP header: {}:{}", HEADER_NAME, HEADER_VALUE);
        logHeaders(soapMessage);
    }

    private void logHeaders(SoapMessage soapMessage) {
        Iterator<?> headerElements = soapMessage.getSoapHeader().examineAllHeaderElements();
        while (headerElements.hasNext()) {
            SoapHeaderElement headerElement = (SoapHeaderElement) headerElements.next();
            logger.info("SOAP Header - {}: {}", headerElement.getName(), headerElement.getText());
        }
    }
}
