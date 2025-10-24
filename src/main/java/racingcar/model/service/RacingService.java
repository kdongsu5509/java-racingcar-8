package racingcar.model.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import racingcar.RacingCarStatus;
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

    public List<RacingCarStatus> race() {
        List<Car> currentCarStatus = carRepository.findAll();
        for (Car car : currentCarStatus) {
            moveCarRandomly(car);
        }

        return currentCarStatus.stream()
                .map(car -> new RacingCarStatus(car.getName(), car.getPosition()))
                .toList();
    }

    private void moveCarRandomly(Car car) {
        int randomNumber = Randoms.pickNumberInRange(0, 9);
        if (randomNumber >= 4) {
            car.go();
        }
    }
}
