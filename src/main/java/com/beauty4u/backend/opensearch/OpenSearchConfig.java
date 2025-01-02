package com.beauty4u.backend.opensearch;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.conn.NoopIOSessionStrategy;
import org.apache.http.nio.conn.SchemeIOSessionStrategy;
import org.apache.http.nio.conn.ssl.SSLIOSessionStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.opensearch.client.RestClient;
import org.opensearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;

@Slf4j
@Configuration
public class OpenSearchConfig {

    @Value("${aws.opensearch.credentials.username}")
    private String username;

    @Value("${aws.opensearch.credentials.password}")
    private String password;

    @Value("${aws.opensearch.endpoint}")
    private String opensearchEndpoint;

    @Bean
    public RestHighLevelClient opensearchClient() {

        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(username, password));

        return new RestHighLevelClient(
                RestClient.builder(HttpHost.create(opensearchEndpoint))
                        .setHttpClientConfigCallback(httpClientBuilder -> {
                            try {
                                SSLContext sslContext = new SSLContextBuilder()
                                        .loadTrustMaterial(null, (x509Certificates, s) -> true)
                                        .build();

                                Registry<SchemeIOSessionStrategy> registry = RegistryBuilder.<SchemeIOSessionStrategy>create()
                                        .register("https", new SSLIOSessionStrategy(sslContext, NoopHostnameVerifier.INSTANCE))
                                        .register("http", NoopIOSessionStrategy.INSTANCE)
                                        .build();

                                PoolingNHttpClientConnectionManager cm = new PoolingNHttpClientConnectionManager(
                                        new DefaultConnectingIOReactor(IOReactorConfig.DEFAULT),
                                        registry
                                );

                                return httpClientBuilder
                                        .setDefaultCredentialsProvider(credentialsProvider)
                                        .setConnectionManager(cm);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        })
        );
    }
}