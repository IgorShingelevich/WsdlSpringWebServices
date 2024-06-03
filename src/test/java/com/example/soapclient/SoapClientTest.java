package com.example.soapclient;

import com.example.soapclient.wsdl.AddResponse;
import com.example.soapclient.wsdl.DivideResponse;
import com.example.soapclient.wsdl.MultiplyResponse;
import com.example.soapclient.wsdl.SubtractResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SoapClientTest {

    @Autowired
    private SoapClient soapClient;

    @Test
    @DisplayName("Test Add Operation: Should return the sum of two numbers")
    void testAdd() {
        AddResponse result = soapClient.add(2, 3);
        assertThat(result.getAddResult()).isEqualTo(5);
    }

    @Test
    @DisplayName("Test Divide Operation: Should return the quotient of two numbers")
    void testDivide() {
        DivideResponse result = soapClient.divide(10, 2);
        assertThat(result.getDivideResult()).isEqualTo(5);
    }

    @Test
    @DisplayName("Test Multiply Operation: Should return the product of two numbers")
    void testMultiply() {
        MultiplyResponse result = soapClient.multiply(3, 4);
        assertThat(result.getMultiplyResult()).isEqualTo(12);
    }

    @Test
    @DisplayName("Test Subtract Operation: Should return the difference between two numbers")
    void testSubtract() {
        SubtractResponse result = soapClient.subtract(9, 5);
        assertThat(result.getSubtractResult()).isEqualTo(4);
    }
}
