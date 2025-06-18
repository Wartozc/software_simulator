package co.com.simulator.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "api.paths")
public class ApiPaths {
    private String listUsers;
    private String createUser;
    private String updateUser;
    private String deleteUser;
    private String login;
}
