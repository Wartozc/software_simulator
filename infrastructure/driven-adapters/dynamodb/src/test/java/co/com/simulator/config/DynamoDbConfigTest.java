package co.com.simulator.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

@ExtendWith(MockitoExtension.class)
class DynamoDbConfigTest {

    private DynamoDbConfig dynamoDbConfig;
    private DynamoDbAsyncClient asyncClient;

    @BeforeEach
    void setUp() {
        this.dynamoDbConfig = new DynamoDbConfig();
        this.asyncClient = this.dynamoDbConfig.getClient();
    }


    @Test
    void shouldCreateAnAsyncClient() {
        Assertions.assertNotNull(this.asyncClient);
    }

    @Test
    void shouldCreateAnAsyncEnhancedClient() {
        Assertions.assertNotNull(dynamoDbConfig.getEnhancedAsyncClient(this.asyncClient));
    }
}