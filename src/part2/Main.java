package part2;

import java.io.*;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "cars.dat";
    private Car[] cars;

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        loadCarsFromFile();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Додати новий автомобіль");
            System.out.println("2. Вилучити автомобіль за ID");
            System.out.println("3. Показати всі автомобілі");
            System.out.println("4. Записати дані у файл");
            System.out.println("5. Зчитати дані з файлу");
            System.out.println("0. Вийти");

            System.out.print("Оберіть опцію: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addCar();
                    break;
                case 2:
                    deleteCarById();
                    break;
                case 3:
                    showAllCars();
                    break;
                case 4:
                    saveCarsToFile();
                    break;
                case 5:
                    loadCarsFromFile();
                    break;
                case 0:
                    System.out.println("Програма завершена.");
                    break;
                default:
                    System.out.println("Некоректний вибір. Спробуйте ще раз.");
            }

        } while (choice != 0);
    }

    private void addCar() {
            Scanner scanner = new Scanner(System.in);

            if (cars == null) {
                cars = new Car[0];
            }

            System.out.print("Введіть ID автомобіля: ");
            int id;
            try {
                id = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Некоректний формат ID. Введіть ціле число.");
                return;
            }

            // Перевірка, чи існує автомобіль з таким ID
            for (Car car : cars) {
                if (car != null && car.getId() == id) {
                    System.out.println("Автомобіль з вказаним ID вже існує.");
                    return;
                }
            }

        System.out.print("Введіть модель автомобіля: ");
        scanner.nextLine(); // Очистка буфера після введення числа
        String model = scanner.nextLine();

        System.out.print("Введіть рік випуску у форматі yyyy-mm-dd: ");
        LocalDate year = LocalDate.parse(scanner.nextLine());

        System.out.print("Введіть ціну автомобіля: ");
        double price = scanner.nextDouble();

        System.out.print("Введіть реєстраційний номер автомобіля: ");
        scanner.nextLine(); // Очистка буфера після введення числа
        String registrationNumber = scanner.nextLine();

        Car newCar = new Car(id, model, year, price, registrationNumber);

        // Збереження нового автомобіля в масиві
        cars = addCarToArray(newCar);

        System.out.println("Автомобіль додано успішно.");
    }

    private Car[] addCarToArray(Car newCar) {
        Car[] newCars = new Car[cars.length + 1];
        System.arraycopy(cars, 0, newCars, 0, cars.length);
        newCars[cars.length] = newCar;
        return newCars;
    }

    private void deleteCarById() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть ID автомобіля для вилучення: ");
        int idToDelete = scanner.nextInt();

        boolean carFound = false;

        for (int i = 0; i < cars.length; i++) {
            if (cars[i] != null && cars[i].getId() == idToDelete) {
                cars = removeCarFromArray(i);
                carFound = true;
                System.out.println("Автомобіль вилучено успішно.");
                break;
            }
        }

        if (!carFound) {
            System.out.println("Автомобіль з вказаним ID не знайдено.");
        }
    }

    private Car[] removeCarFromArray(int index) {
        Car[] newCars = new Car[cars.length - 1];
        System.arraycopy(cars, 0, newCars, 0, index);
        System.arraycopy(cars, index + 1, newCars, index, cars.length - index - 1);
        return newCars;
    }

    private void showAllCars() {
        for (Car car : cars) {
            if (car != null) {
                System.out.println(car);
            }
        }
    }

    private void saveCarsToFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            outputStream.writeObject(cars);
            System.out.println("Дані успішно записано у файл.");
        } catch (IOException e) {
            System.out.println("Помилка запису даних у файл: " + e.getMessage());
        }
    }

    private void loadCarsFromFile() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("Файл " + FILE_NAME + " не існує. Спочатку збережіть дані у файл.");
            return;
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            cars = (Car[]) inputStream.readObject();
            System.out.println("Дані успішно зчитано з файлу.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Помилка зчитування даних з файлу: " + e.getMessage());
        }
    }

}
