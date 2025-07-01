package co.com.simulator.dbo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@Setter
@Getter
@AllArgsConstructor
@DynamoDbBean
@NoArgsConstructor
public class UserDBO {
    private String userId;
    private String name;
    private String documentType;
    private String documentNumber;
    private AccountDBO account;

    @DynamoDbPartitionKey
    public String getUserId() {
        return userId;
    }

    @DynamoDbSortKey
    public String getDocumentNumber() {
        return documentNumber;
    }
}
