package com.example.soapclient;

import com.example.soapclient.wsdl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.stereotype.Component;

@Component
public class SoapClient {

    private static final String NAMESPACE_URI = "http://tempuri.org/";

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    public AddResponse add(int intA, int intB) {
        Add request = new Add();
        request.setIntA(intA);
        request.setIntB(intB);

        return (AddResponse) webServiceTemplate.marshalSendAndReceive(
                request,
                new SoapActionCallback(NAMESPACE_URI + "Add")
        );
    }

    public DivideResponse divide(int intA, int intB) {
        Divide request = new Divide();
        request.setIntA(intA);
        request.setIntB(intB);

        return (DivideResponse) webServiceTemplate.marshalSendAndReceive(
                request,
                new SoapActionCallback(NAMESPACE_URI + "Divide")
        );
    }

    public MultiplyResponse multiply(int intA, int intB) {
        Multiply request = new Multiply();
        request.setIntA(intA);
        request.setIntB(intB);

        return (MultiplyResponse) webServiceTemplate.marshalSendAndReceive(
                request,
                new SoapActionCallback(NAMESPACE_URI + "Multiply")
        );
    }

    public SubtractResponse subtract(int intA, int intB) {
        Subtract request = new Subtract();
        request.setIntA(intA);
        request.setIntB(intB);

        return (SubtractResponse) webServiceTemplate.marshalSendAndReceive(
                request,
                new SoapActionCallback(NAMESPACE_URI + "Subtract")
        );
    }
}
