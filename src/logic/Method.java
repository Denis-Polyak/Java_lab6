package logic;

import cars.Car;

import java.util.ArrayList;
import java.util.List;
public class Method {
    public void addCar(List<Car> list, String mark, String model, int year, int price, String number) {
        Car car = new Car(mark, model, year, price, number);
        list.add(car);
    }
    public void removeCar(int carToDelete, List<Car> list) {
        if (list.isEmpty()) {
            return;
        }
        boolean remove = list.removeIf(car -> car.getId() == carToDelete);
        if (!remove) {
            return;
        }
    }

    public void showAllCars(List<Car> list) {
        if (list.isEmpty()) {
            return;
        }
        list.forEach(System.out::println);
    }

    public List<Car> searchMark(String mark, List<Car> list) {
        if (list.isEmpty()) {
            return null;
        }
        List<Car> tempList = new ArrayList<>();
        list.forEach(car -> {
            if (car.getMark().equals(mark))
                tempList.add(car);
        });
        return tempList;
    }

    public List<Car> searchMarkAndYear(String mark, List<Car> list, int year) {
        if (list.isEmpty()) {
            return null;
        }
        List<Car> tempList = new ArrayList<>();
        list.forEach(car -> {
            int now  = 2022;
            int minus = now - car.getYear();
            if (car.getMark().equals(mark)) {
                if(minus > year) {
                    tempList.add(car);
                }
            }
        });
        return tempList;
    }

    public List<Car> searchYearAndPrice(List<Car> list, int enterY, int enterP) {
        if (list.isEmpty()) {
            return null;
        }
        List<Car> tempList = new ArrayList<>();
        list.forEach(car -> {
            if (enterY == car.getYear()) {
                if(car.getPrice() > enterP)
                    tempList.add(car);
            }
        });
        return tempList;
    }

    public boolean inputValidate(String input) {
        if (input.contains(" ") || input.contains("\n") ||
                input.contains("\t") || input.isEmpty()) {
            return false;
        }
        return true;
    }

    public List<Car> sort(List<Car> list){
        list.sort(null);
        for(Car car : list ){
            System.out.println(car);
        }

        return null;
    }

    public static <T> List<T> convertToList(T[] arr)
    {
    
        List<T> list = new ArrayList<>();
        
        for (T i: arr) {
            list.add(i);
        }
        return list;
    }



}
