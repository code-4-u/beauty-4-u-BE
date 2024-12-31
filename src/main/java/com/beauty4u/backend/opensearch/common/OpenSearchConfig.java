package com.beauty4u.backend.opensearch.common;

import com.amazonaws.auth.AWS4Signer;
import com.amazonaws.auth.AWSCredentialsProvider;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequestInterceptor;
import org.opensearch.client.RestClient;
import org.opensearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Duration;

@Configuration
// EnableOpenSearchRepositories가 아직 지원되지 않아 EnableElasticsearchRepositories를 사용
//@EnableElasticsearchRepositories(basePackages = "com.beauty4u.backend.opensearch.repository")
public class OpenSearchConfig {
    @Value("${spring.cloud.aws.opensearch.endpoint}")
    private String endpoint;

    @Value("${spring.cloud.aws.region}")
    private String region;

    private final AWSCredentialsProvider awsCredentialsProvider;

    @Autowired
    public OpenSearchConfig(AWSCredentialsProvider awsCredentialsProvider) {
        this.awsCredentialsProvider = awsCredentialsProvider;
    }


    @Bean("customOpenSearchClient")
    @Primary
    public RestHighLevelClient opensearchClient() {
        AWS4Signer signer = new AWS4Signer();
        String serviceName = "es";
        signer.setServiceName(serviceName);
        signer.setRegionName(region);

        HttpRequestInterceptor interceptor = new AWSRequestSigningApacheInterceptor(
                serviceName,
                signer,
                awsCredentialsProvider);

        return new RestHighLevelClient(
                RestClient.builder(new HttpHost(endpoint, 443, "https"))
                        .setHttpClientConfigCallback(httpClientBuilder ->
                                httpClientBuilder.addInterceptorLast(interceptor))
                        .setRequestConfigCallback(requestConfigBuilder ->
                                requestConfigBuilder
                                        .setConnectTimeout((int) Duration.ofSeconds(300).toMillis())
                                        .setSocketTimeout((int) Duration.ofSeconds(150).toMillis()))
        );
    }
}
