package co.com.simulator;

import co.com.simulator.message.TechnicalExceptionMessage;
import lombok.Getter;

@Getter
public class TechnicalException extends RuntimeException {

    private final TechnicalExceptionMessage technicalExceptionMessage;

    public TechnicalException(TechnicalExceptionMessage technicalExceptionMessage, Throwable cause) {
        super(technicalExceptionMessage.getDescription(), cause);
        this.technicalExceptionMessage = technicalExceptionMessage;
    }
}
