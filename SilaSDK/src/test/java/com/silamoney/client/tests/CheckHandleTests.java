/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.testsutils.DefaultConfigurations;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.MockServerRule;

/**
 *
 * @author loren
 */
public class CheckHandleTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host,
            DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Rule
    public MockServerRule mockServerRule = new MockServerRule(this);

    @Before
    public void setUp() {
        MockServer.checkHandleServer();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void Response200Success() {
        System.out.println("Test init");
        try {
            ApiResponse response = api.CheckHandle("user.silamoney.eth");
            
            System.out.println(response.data);
        } catch (Exception ex) {
            Logger.getLogger(CheckHandleTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
