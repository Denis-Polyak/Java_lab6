import cars.Car;
import file.FileProcessor;
import logic.Method;
import menu.Menu;
import menu.MenuItem;

import java.util.*;
import java.util.concurrent.Callable;
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
    public void run() {
        Scanner scanner = new Scanner(System.in);
        Method method = new Method();
        FileProcessor fileProcessor = new FileProcessor();
        List<Car> list1 = new ArrayList<>();
        list1 = FileProcessor.fillCarsArray();
        List<Car> list = new ArrayList<>();
        List<Car> finalList2 = list1;
        list.addAll(list1);
        list = fileProcessor.ReadFile(list);
        List<Car> finalList = list;
        List<Car> finalList1 = list;
        List<MenuItem> menuItems = Arrays.asList(
                new MenuItem("Вийти з програми", () -> {
                    System.exit(0);
                }),
                new MenuItem("Додати Авто", () -> {

                    String carMark = null;
                    Callable<String> markInput = () -> {
                        System.out.println("Введіть Марку Авто: ");
                        String mark = scanner.nextLine();
                        if (!method.inputValidate(mark)) {
                            System.out.println("Введенно неприпустимі символи");
                            return null;
                        }
                        return mark;
                    };
                        try {
                            do {
                                carMark = markInput.call();
                            }

                            while (carMark == null);
                        }
                        catch (Exception err) {
                            err.printStackTrace();
                        }

                    String carModel = null;
                    Callable<String> modelInput = () -> {
                        System.out.println("Введіть Модель Авто: ");
                        String model = scanner.nextLine();
                        if (!method.inputValidate(model)) {
                            System.out.println("Введенно неприпустимі символи");
                            return null;
                        }
                        return model;
                    };
                    try {
                        do {
                            carModel =modelInput.call();
                        }

                        while (carModel == null);
                    }
                    catch (Exception err) {
                        err.printStackTrace();
                    }

                    int carYear = 0;
                    Callable<Integer> yearInput = () -> {
                        System.out.println("Введіть Якого Року Випущене Авто: ");
                        int year;
                        try {
                            year = scanner.nextInt();
                            scanner.nextLine();
                        }
                        catch (InputMismatchException err) {
                            System.out.println("Введенно неприпустимі символи");
                            scanner.next();
                            return -1;
                        }
                        return year;
                    };
                    try {
                        do {
                            carYear = yearInput.call();
                        }
                        while (carYear == -1);
                    }
                    catch (Exception err) {
                        err.printStackTrace();
                    }

                    int carPrice = 0;
                    Callable<Integer> priceInput = () -> {
                        System.out.println("Введіть Ціну Авто: ");
                        int price;
                        try {
                            price = scanner.nextInt();
                            scanner.nextLine();
                        }
                        catch (InputMismatchException err) {
                            System.out.println("Введенно неприпустимі символи");
                            scanner.next();
                            return -1;
                        }
                        return price;
                    };
                    try {
                        do {
                            carPrice = priceInput.call();
                        }
                        while (carPrice == -1);
                    }
                    catch (Exception err) {
                        err.printStackTrace();
                    }

                    String carNumber = null;
                    Callable<String> numberInput = () -> {
                        System.out.println("Введіть Нормер Авто: ");
                        String number = scanner.nextLine();
                        if (!method.inputValidate(number)) {
                            System.out.println("Введенно неприпустимі символи");
                            return null;
                        }
                        return number;
                    };
                    try {
                        do {
                            carNumber = numberInput.call();
                        }

                        while (carNumber == null);
                    }
                    catch (Exception err) {
                        err.printStackTrace();
                    }
                    method.addCar(finalList, carMark, carModel, carYear, carPrice, carNumber );
                    fileProcessor.WriteFile(finalList);
                }),
              new MenuItem("Видалити Авто", () -> {
                    method.showAllCars(finalList);
                    System.out.println("Введіть id авто для видалення");
                    int carToDelete = scanner.nextInt();
                    scanner.nextLine();
                    method.removeCar(carToDelete, finalList);
                    fileProcessor.WriteFile(finalList);
                }),
                new MenuItem("Список всіх Авто", () -> {
                    method.showAllCars(finalList);

                }),



             new MenuItem("Показати Автомобілі Вказаної Марки;", () -> {
                        System.out.println("Введіть Марку Авто");
                        String markC = scanner.nextLine();
                        System.out.println(method.searchMark(markC, finalList));
               }),
             new MenuItem("Показати Автомобілі Вказаної Марки Які Працюють Більше Років Вказаних Вами;", () -> {
                                 System.out.println("Введіть Марку Авто");
                                 String markC = scanner.nextLine();
                                 System.out.println("Введіть Кількість Років");
                                 int yearY = scanner.nextInt();
                                 System.out.println(method.searchMarkAndYear(markC,finalList,yearY));
               }),
        new MenuItem("Показати Автомобілі Вказаного Року Випуску та Ціну Більше Вказаної;", () -> {
        System.out.println("Введіть Рік Випуску Авто");
        int yearY = scanner.nextInt();
        System.out.println("Введіть Ціну Авто");
        int priceP = scanner.nextInt();
        System.out.println(method.searchYearAndPrice(finalList, yearY, priceP));
        }),
                new MenuItem("Показати список автомобілів в порядку спадання ціни. Якщо ціна однакова, то в порядку зростання року випуску;", () -> {

                    System.out.println(method.sort(finalList1));

                }),
                new MenuItem("Показати список моделей автомобілів, зареєстрованих у програмі;", () -> {

                    method.showAllCars(finalList2);
                }),
                new MenuItem("Для кожної моделі вивести список автомобілів.", () -> {
                     Car.markSort(finalList1);
                })
        );
        Menu menu = new Menu(menuItems);
        menu.run();
        }





}
