package part2;

import java.time.LocalDate;
import java.util.Arrays;

public class Specifications {
    public static Car[] findCarsByModelName(Car[] cars, String model) {
        int j = 0;
        Car[] carsOfModel = new Car[cars.length];
        for (Car car : cars) {
            if (car.getModel().equals(model)) {
                carsOfModel[j] = car;
                j++;
            }
        }
        return Arrays.copyOf(carsOfModel, j);
    }

    public static Car[] vehicleOperation(Car[] cars, String model, int n) {
        int j = 0;
        LocalDate currentDate = LocalDate.now();
        Car[] oldCarsOfModel = new Car[cars.length];
        for (Car car : cars) {
            if (car.getModel().equals(model) && (currentDate.getYear() - car.getYear().getYear()) > n) {
                oldCarsOfModel[j++] = car;
            }
        }
        return Arrays.copyOf(oldCarsOfModel, j);
    }

    public static Car[] carsPrice(Car[] cars, double minPrice, LocalDate year) {
        int j = 0;
        Car[] carsOfYearAndPrice = new Car[cars.length];
        for (Car car : cars) {
            if (car.getYear().isEqual(year) && car.getPrice() > minPrice) {
                carsOfYearAndPrice[j++] = car;
            }
        }
        return Arrays.copyOf(carsOfYearAndPrice, j);
    }
}
