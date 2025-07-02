package co.com.simulator.exception;

import co.com.simulator.exception.message.BusinessExceptionMessage;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final BusinessExceptionMessage businessExceptionMessage;

    public BusinessException(BusinessExceptionMessage businessExceptionMessage) {
        super(businessExceptionMessage.getDescription());
        this.businessExceptionMessage = businessExceptionMessage;
    }
}
