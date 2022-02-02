package ru.bolgov.spring.pagination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bolgov.spring.pagination.Exception.TooMuchCarTryToAddException;
import ru.bolgov.spring.pagination.Exception.WrongPageFormatException;
import ru.bolgov.spring.pagination.Exception.WrongSizeFormatException;
import ru.bolgov.spring.pagination.model.Car;
import ru.bolgov.spring.pagination.service.CarService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class CarRestController {

    @Autowired
    private CarService carService;

    @GetMapping("all")
    public List<Car> getCar(){

        return carService.findAll();
    }

    @GetMapping("/random")
    public List<Car> createRandomCar(@RequestParam Map<String, String> headers) throws WrongSizeFormatException {
        int cars = 1;
        if(headers.containsKey("cars")){
            try {
                cars =  Integer.parseInt(headers.get("cars"));
                if(cars <= 0){
                    throw new WrongSizeFormatException();
                }
                if(cars > 500){
                    throw new TooMuchCarTryToAddException("Can't add over 500 cars");
                }
            }
            catch (TooMuchCarTryToAddException c){
                throw new TooMuchCarTryToAddException("Can't add over 500 cars");
            }
            catch (Exception e){
                throw new WrongSizeFormatException();
            }
        }
        List<Car> carList = new ArrayList<>();
        for(int i = 0; i <= cars; i++) {
            Car car = carService.generateRandomCar();
            carList.add(car);
        }
        carService.saveAll(carList);
        return carList;
    }

    @GetMapping("/cars")
    public List<Car> findCarsByPages(@RequestParam Map<String, String> headers) throws WrongPageFormatException, WrongSizeFormatException {
        int page = 1;
        int size = 10;

        if(headers.containsKey("limit")){
            try {
                size =  Integer.parseInt(headers.get("limit"));
            }catch (Exception e){
                throw new WrongSizeFormatException();
            }
        }
        if(headers.containsKey("page")){
            try {
                page = Integer.parseInt(headers.get("page")) * size;
            }catch (Exception e){
                throw new WrongPageFormatException();
            }
        }
        return carService.findByPage(page, size);
    }
}
