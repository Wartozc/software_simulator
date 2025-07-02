package co.com.simulator.dbo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@DynamoDbBean
public class AccountDBO {

    private String email;
    private String password;
}
