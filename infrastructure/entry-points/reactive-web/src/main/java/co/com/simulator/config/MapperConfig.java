package co.com.simulator.config;

import co.com.simulator.ModelMapperSimulator;
import co.com.simulator.dto.UserDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapperSimulator<UserDTO> getMapper() {
        return new ModelMapperSimulator<>(UserDTO.class);
    }
}
