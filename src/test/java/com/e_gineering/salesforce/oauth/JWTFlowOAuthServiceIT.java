package com.e_gineering.salesforce.oauth;

/*-
 * #%L
 * salesforce-oauth
 * %%
 * Copyright (C) 2021 E-gineering, Inc.
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import com.nimbusds.jose.JOSEException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.text.ParseException;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertTrue;

class JWTFlowOAuthServiceIT {

  @Test
  void requestAccessToken() throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, ParseException, JOSEException {

    String privateKey = new String(Base64.getDecoder().decode(System.getProperty("salesforce.jwt.rsa.private-key")));
    String publicKey = new String(Base64.getDecoder().decode(System.getProperty("salesforce.jwt.rsa.public-key")));
    String password = System.getProperty("salesforce.jwt.rsa.password");
    String clientId = System.getProperty("salesforce.api.client-id");
    String clientSecret = System.getProperty("salesforce.api.client-secret");
    String baseUrl = System.getProperty("salesforce.api.base-url");
    String jwtAudience = System.getProperty("salesforce.jwt.audience");
    String subject = System.getProperty("salesforce.jwt.subject");
    var jwtFlowOAuthService = new JWTFlowOAuthService(privateKey, publicKey, password, clientId, clientSecret, baseUrl, jwtAudience, subject);

    final String jwt = jwtFlowOAuthService.generatedJWT();
    assertTrue(jwtFlowOAuthService.validateJWT(jwt));
  }
}
