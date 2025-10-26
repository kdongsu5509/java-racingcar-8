package racingcar.model.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import racingcar.global.CustomException.NotExistException;
import racingcar.global.ErrorMessage;

class CarRepositoryTest {

    private final CarRepository carRepository = new CarRepository();

    @AfterEach
    void tearDown() {
        carRepository.clear();
    }

    @Test
    void save() {
        Car test = new Car("Test");

        int savedCarId = carRepository.save(test);
        assertEquals(0, savedCarId);
    }

    @Test
    void findById_success() {
        Car test = new Car("Test");
        int savedCarId = carRepository.save(test);

        Car foundCar = carRepository.findById(savedCarId);

        assertSame(test, foundCar, "저장된 자동차와 조회된 자동차가 동일해야 합니다.");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/fail/carNotExist.csv", numLinesToSkip = 1)
    void findById_notExist(String carNames, String requestId) {
        //given
        Arrays.stream(carNames.split(","))
                .forEach(carName -> carRepository.save(new Car(carName)));
        int key = Integer.parseInt(requestId);

        //when,then
        Assertions.assertThatThrownBy(() -> carRepository.findById(key))
                .isInstanceOf(NotExistException.class)
                .hasMessage(ErrorMessage.NOT_FOUND.getMessage());
    }

    @Test
    void findAll() {
        List<Car> testCars = List.of(
                new Car("Car1"),
                new Car("Car2"),
                new Car("Car3")
        );

        for (Car car : testCars) {
            carRepository.save(car);
        }

        List<Car> all = carRepository.findAll();
        assertEquals(testCars.size(), all.size(), "저장된 자동차 수와 조회된 자동차 수가 일치해야 합니다.");
    }
}