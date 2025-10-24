package racingcar.model.repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CarRepository {

    private int index = 0;

    private final Map<Integer, Car> racingCars = new ConcurrentHashMap<>();

    public int save(Car car) {
        racingCars.put(index, car);
        return index++;
    }

    public Car findById(Integer id) {
        return racingCars.get(id);
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
