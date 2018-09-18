package com.api.boot.modules.infrastructure.http.server;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class HttpRequest extends AbstractCertificateRequest {

    public Map<String,Object> foo() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        SSLContext context = build();
        return null;
    }

}
