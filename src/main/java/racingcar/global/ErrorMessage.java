package racingcar.global;

public enum ErrorMessage {
    OVER_SIZE_FIVE("자동차 이름은 5자를 초과할 수 없습니다."),
    CANNOT_EMPTY("자동차 이름은 공백이거나 빈 문자열일 수 없습니다."),
    ALLOW_ONLY_POSITIVE_INTEGER("시도 횟수는 양의 정수여야 합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
