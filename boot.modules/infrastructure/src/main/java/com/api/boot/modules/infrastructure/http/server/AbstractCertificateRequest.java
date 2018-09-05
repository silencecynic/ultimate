package com.api.boot.modules.infrastructure.http.server;




import org.apache.hc.core5.ssl.SSLContextBuilder;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public abstract class AbstractCertificateRequest implements Certificate {

    @Override
    public SSLContext create() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext context = SSLContext.getInstance("TLS");
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        context.init(null,new TrustManager[] {trustManager},null);

        return context;
    }

    @Override
    public SSLContext build() throws NoSuchAlgorithmException, KeyStoreException,KeyManagementException {
        return new SSLContextBuilder().loadTrustMaterial(null,(ignoreCertificate, authType) -> true).build();
    }


    @Override
    public SSLContext load() throws IOException,KeyStoreException,KeyManagementException, UnrecoverableKeyException ,NoSuchAlgorithmException{
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        SSLContext context = new SSLContextBuilder().loadKeyMaterial(keyStore, null).build();
        return null;
    }


}
