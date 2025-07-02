package co.com.simulator.dbo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class AccountDBO {
    @Id
    @Column(value = "account_id")
    private String accountId;
    @Column(value = "account_email")
    private String email;
    @Column(value = "account_password")
    private String password;
}
