package com.api.boot.modules.infrastructure.http.server;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

public interface Certificate {

    SSLContext create() throws NoSuchAlgorithmException, KeyManagementException;

    SSLContext build() throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException;

    SSLContext load() throws  IOException,KeyStoreException,KeyManagementException, UnrecoverableKeyException,NoSuchAlgorithmException;


}
