package co.com.simulator;

import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public class Email {
    private final String email;

    private static final Pattern PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

    public Email(String email) {
        if (!PATTERN.matcher(email).matches()) throw new RuntimeException("Correo Invalido");
        this.email = email;
    }
}
