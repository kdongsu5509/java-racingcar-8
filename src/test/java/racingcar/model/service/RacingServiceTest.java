package racingcar.model.service;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import racingcar.model.repository.Car;
import racingcar.model.repository.CarRepository;

class RacingServiceTest {

    @Test
    void setUpRacingCars_success() {
        //given
        List<String> testCars = List.of("car1", "car2", "car3");
        CarRepository carRepository = new CarRepository();
        RacingService racingService = new RacingService(carRepository);

        //when
        racingService.setupRacingCars(testCars);

        //then
        List<Car> savedCars = carRepository.findAll();
        Assertions.assertEquals(3, savedCars.size());
    }
}