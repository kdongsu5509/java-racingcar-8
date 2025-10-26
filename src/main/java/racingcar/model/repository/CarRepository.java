package racingcar.model.repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import racingcar.global.CustomException.NotExistException;
import racingcar.global.ErrorMessage;

public class CarRepository {

    private int index = 0;

    private final Map<Integer, Car> racingCars = new ConcurrentHashMap<>();

    public int save(Car car) {
        racingCars.put(index, car);
        return index++;
    }

    public Car findById(Integer id) {
        if (racingCars.containsKey(id)) {
            return racingCars.get(id);
        } else {
            throw new NotExistException(ErrorMessage.NOT_FOUND.getMessage());
        }
    }

    public List<Car> findAll() {
        return racingCars.values()
                .stream()
                .toList();
    }

    public void clear() {
        racingCars.clear();
        index = 0;
    }
}
