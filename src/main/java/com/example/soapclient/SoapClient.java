package com.example.soapclient;

import com.example.soapclient.wsdl.Add;
import com.example.soapclient.wsdl.AddResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;

@Component
public class SoapClient {

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    public AddResponse add(int intA, int intB) {
        Add request = new Add();
        request.setIntA(intA);
        request.setIntB(intB);

        AddResponse response = (AddResponse) webServiceTemplate.marshalSendAndReceive(
                "http://www.dneonline.com/calculator.asmx",
                request,
                new SoapActionCallback("http://tempuri.org/Add")
        );

        // Assert the response
        assert response != null : "Response is null";
        assert response.getAddResult() == (intA + intB) : "The addition result is incorrect";

        System.out.println("Response received: " + response.getAddResult());

        return response;
    }
}
