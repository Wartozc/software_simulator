package co.com.simulator.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TechnicalExceptionMessage {
    UN_EXPECTED_EXCEPTION("TESS0001", "An un-expected exception was presented");

    private final String code;
    private final String description;
}
