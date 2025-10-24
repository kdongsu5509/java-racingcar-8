package racingcar.model.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarTest {

    @Test
    @DisplayName("Car 객체 생성 시 이름이 정상적으로 할당되고 위치는 0으로 초기화된다.")
    void carCreation_success() {
        // given
        String carName = "TestCar";

        // when
        Car car = new Car(carName);

        // then
        assertEquals(carName, car.getName());
        assertEquals(0, car.getPosition());
    }

    @Test
    @DisplayName("go 메서드를 호출하면 자동차의 위치가 1 증가한다")
    void carGo_increasesPosition() {
        // given
        Car car = new Car("TestCar");

        // when
        car.go();

        // then
        assertEquals(1, car.getPosition());
    }
}