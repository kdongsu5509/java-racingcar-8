package racingcar.model.service;

import java.util.List;
import racingcar.model.repository.Car;
import racingcar.model.repository.CarRepository;

public class RacingService {

    private final CarRepository carRepository;

    public RacingService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void setupRacingCars(List<String> carNames) {
        for (String carName : carNames) {
            Car car = new Car(carName);
            carRepository.save(car);
        }
    }
}
