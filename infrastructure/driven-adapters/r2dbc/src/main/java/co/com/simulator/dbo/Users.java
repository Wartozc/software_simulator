package co.com.simulator.dbo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter
@Table("users")
public class Users {
    @Column("user_id")
    private String userId;
    @Column("user_name")
    private String name;
    @Column("user_document_type")
    private String documentType;
    @Column("user_document_number")
    private String documentNumber;
    @Column("user_account_id")
    private String accountId;

}
