package ee.janek24back;

import lombok.Getter;

@Getter
public enum Status {
    ACTIVE("A"),
    DELETED("D");

    private final String code;

    Status(String code) {
        this.code = code;
    }

}