package co.com.simulator.user;

import lombok.*;


@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@ToString
public class User {
    private String userId;
    private String name;
    private String documentType;
    private String documentNumber;
    private Account account;
}
