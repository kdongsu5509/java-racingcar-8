package racingcar.model.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.global.CustomException.CarException;
import racingcar.global.ErrorMessage;

class CarTest {

    private final String GOOD_CAR_NAME = "car";

    @Test
    void carCreation_success() {
        // when
        Car car = new Car(GOOD_CAR_NAME);

        // then
        assertEquals(GOOD_CAR_NAME, car.getName());
        assertEquals(0, car.getPosition());
    }

    @ParameterizedTest(name = "{0} : 이름이 길면 Car 생성 실패")
    @ValueSource(strings = {"sixsix", "toolongName", "asdfasvnpoasdfasdl"})
    void carCreation_fail1(String input) {
        Assertions.assertThatThrownBy(() -> new Car(input))
                .isInstanceOf(CarException.class)
                .hasMessageContaining(ErrorMessage.OVER_SIZE_FIVE.getMessage());
    }

    @ParameterizedTest(name = "[index] 공백 및 null 이면 Car 객체 생성 실패")
    @NullAndEmptySource
    void carCreation_fail2(String input) {
        Assertions.assertThatThrownBy(() -> new Car(input))
                .isInstanceOf(CarException.class)
                .hasMessageContaining(ErrorMessage.CANNOT_EMPTY.getMessage());
    }

    @Test
    @DisplayName("go 메서드를 호출하면 자동차의 위치가 1 증가한다")
    void carGo_increasesPosition() {
        // given
        Car car = new Car(GOOD_CAR_NAME);

        // when
        car.go();

        // then
        assertEquals(1, car.getPosition());
    }
}