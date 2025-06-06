package co.com.simulator.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class ExceptionDTO {
    private String domain;
    private String cause;
    private String description;
    private String code;
}
