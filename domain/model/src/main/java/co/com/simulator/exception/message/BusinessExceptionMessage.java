package co.com.simulator.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BusinessExceptionMessage {
    UN_EXPECTED_ERROR("BESS0001", "Un un-expected exception was presented");

    private final String code;
    private final String description;
}
