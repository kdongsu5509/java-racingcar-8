package racingcar.model.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
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

        return convertToRacingCarStatusList(currentCarStatus);
    }

    public List<String> findRaceWinner() {
        List<Car> currentCarStatus = carRepository.findAll();

        return findRaceWinnerName(
                currentCarStatus, findMaxCarPosition(currentCarStatus)
        );
    }

    private static int findMaxCarPosition(List<Car> currentCarStatus) {
        int maxCarPosition = currentCarStatus.getFirst().getPosition();
        for (Car car : currentCarStatus) {
            if (car.getPosition() > maxCarPosition) {
                maxCarPosition = car.getPosition();
            }
        }
        return maxCarPosition;
    }

    private static List<String> findRaceWinnerName(List<Car> currentCarStatus, int maxCarPosition) {
        List<String> raceWinner = new ArrayList<>();
        //3. 가장 멀리 간 차의 이름을 찾는다.
        for (Car car : currentCarStatus) {
            if (car.getPosition() == maxCarPosition) {
                raceWinner.add(car.getName());
            }
        }
        return raceWinner;
    }

    private static List<RacingCarStatus> convertToRacingCarStatusList(List<Car> currentCarStatus) {
        return currentCarStatus.stream()
                .map(car ->
                        new RacingCarStatus(
                                car.getName(), car.getPosition()
                        ))
                .toList();
    }

    private void moveCarRandomly(Car car) {
        int randomNumber = Randoms.pickNumberInRange(0, 9);
        if (randomNumber >= 4) {
            car.go();
        }
    }
}
