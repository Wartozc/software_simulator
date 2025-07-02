package co.com.simulator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

import java.net.URI;

@Configuration
public class DynamoDbConfig {

    @Bean
    public DynamoDbAsyncClient getClient() {
        return DynamoDbAsyncClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials
                        .create("test", "test")))
                .endpointOverride(URI.create("http://localhost:8010"))
                .region(Region.US_EAST_1)
                .build();
    }

    @Bean
    public DynamoDbEnhancedAsyncClient getEnhancedAsyncClient(DynamoDbAsyncClient asyncClient) {
        return DynamoDbEnhancedAsyncClient.builder().dynamoDbClient(asyncClient).build();
    }
}
