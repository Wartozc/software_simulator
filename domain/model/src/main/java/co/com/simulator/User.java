package co.com.simulator;

import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class User {
    private final String userId;
    private final String name;
    private final String documentType;
    private final String documentNumber;
    private final Email email;
    private final String password;
}
