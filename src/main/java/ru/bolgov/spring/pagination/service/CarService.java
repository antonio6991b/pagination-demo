package ru.bolgov.spring.pagination.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bolgov.spring.pagination.Exception.NoSuchCarException;
import ru.bolgov.spring.pagination.model.Car;
import ru.bolgov.spring.pagination.repo.CarRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;


    private final List<String> names = Arrays.asList(
            "Ralph", "Randy", "Raymond", "Richard", "Robert", "Roger", "Ronald", "Roy",
            "Russell", "Ryan", "Felix", "Fernando", "Floyd", "Francis", "Francisco", "Frank",
            "Franklin", "Fred", "Freddie", "Frederick", "Earl", "Eddie", "Edward", "Edwin",
            "Elijah", "Eric", "Ernest", "Ethan", "Eugene", "Evan", "Parker", "Patrick", "Paul",
            "Pedro", "Perry", "Peter", "Peyton", "Philip", "Phillip", "Preston", "Jack", "Jacob",
            "James", "Jason", "Jeffrey", "John", "Jonathan", "Joseph", "Joshua", "Justin", "Zachariah",
            "Zachary", "Zachery", "Zackary", "Zackery", "Zander", "Zane", "Zayden", "Zechariah", "Zion",
            "Samuel", "Scott", "Sean", "Seth", "Shane", "Shawn", "Stanley", "Stephen", "Steve", "Steven",
            "Ubaldo", "Ulises", "Ulysses", "Umar", "Urban", "Uriah", "Uriel", "Urijah", "Uziel", "Nathan",
            "Nathaniel", "Neal", "Neil", "Nelson", "Nicholas", "Nicolas", "Noah", "Nolan", "Norman", "Yaakov",
            "Yahir", "Yair", "Yandel", "Yehuda", "Yisroel", "Yitzchok", "Yosef", "Yousef", "Yusuf", "Odell",
            "Oliver", "Ollie", "Omar", "Orlando", "Orville", "Oscar", "Otis", "Otto", "Owen", "Xaiver",
            "Xander", "Xavi", "Xavian", "Xavien", "Xavier", "Xavion", "Xavior", "Xzavier", "Xzavion",
            "Larry", "Lawrence", "Lee", "Leonard", "Leroy", "Logan", "Louis", "Lucas", "Luis", "Luke",
            "Manuel", "Marcus", "Mark", "Martin", "Marvin", "Mason", "Matthew", "Melvin", "Michael",
            "Mike", "Wade", "Wallace", "Walter", "Warren", "Wayne", "Wesley", "Willard", "William",
            "Willie", "Wyatt", "Theodore", "Thomas", "Timothy", "Todd", "Tommy", "Tony", "Travis"
    );

    private final List<String> models = Arrays.asList("BMW", "MERSEDES", "OPEL", "NISSAN", "MITSUBISHI", "HOVER", "LADA", "HYNDAI",
            "HONDA", "CHEVROLET", "RENAULT", "PEUGEOT", "FORD", "GEELY", "TOYOTA", "LINKOLN", "UAZ");



    public void saveCar(Car car){
        carRepository.save(car);
    }

    public Car findById(long id) throws NoSuchCarException {
        return carRepository.findById(id).orElseThrow(NoSuchCarException::new);
    }

    public List<Car> findAll(){
        List<Car> cars = carRepository.findAll();

        return cars;
    }

    public Car generateRandomCar(){
        Car car = new Car();
        car.setCarName(models.get((int) (Math.random() * models.size())));
        car.setOwner(names.get((int) (Math.random() * names.size())));
        car.setMileage((int) (Math.random() * 300000));
        return car;
    }

    public List<Car> findByPage(int page, int limit){
        return carRepository.findByPage(page, limit);
    }

    public long count(){
        return carRepository.count();
    }

    public void saveAll(List<Car> cars){
        carRepository.saveAll(cars);
    }

}
