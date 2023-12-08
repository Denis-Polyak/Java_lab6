package file;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cars.Car;
import logic.Method;

import java.io.File;
import java.io.IOException;
import java.util.List;
public class FileProcessor {
    private final String fileName = "car";
    private final String fileExtension = ".json";
    private final String directory = "E:/";
    private final String filePath = directory + fileName +
            fileExtension;
    public void CreateFile() {
        File file = new File(filePath);
        try {
            file.createNewFile();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void WriteFile(List<Car> list) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(filePath), list);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public List<Car> ReadFile(List<Car> list) {
        File file = new File(filePath);
        if (file.length() == 0) {
            return list;
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            list = objectMapper.readValue(new File(filePath), new
                    TypeReference<>() {
                    });
        }
        catch (IOException err){
            err.printStackTrace();
        }
        return list;
    }

    public static List<Car> fillCarsArray() {
        Car[] cars = new Car[]{
                new Car("Audi", "S1", 2018, 20000,"BE4411CH"),
                new Car("BMW", "M5", 2018, 50000,"BE1258BA"),
                new Car("Ford", "Mustang", 2020, 30000,"BE3713AX"),
                new Car("Honda", "CR-V", 2009, 25000,"BE8156ME"),
                new Car("Hyundai","Sonata",2015,15000,"BE5479BA"),
                new Car("Kia", "Optima", 2013, 13000,"BE6224CH"),
                new Car("BMW","X5",2020,60000,"BE1938ME")
        };
        List<Car> list = Method.convertToList(cars);

        return list;
    }

}