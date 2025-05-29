package co.com.simulator;

import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public class Account {
    private final String email;
    private final String password;

    private static final Pattern PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

    public Account(String email, String password) {
        if (!PATTERN.matcher(email).matches()) throw new RuntimeException("Correo Invalido");
        this.email = email;
        this.password = password;
    }
}
