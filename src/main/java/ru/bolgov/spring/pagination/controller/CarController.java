package ru.bolgov.spring.pagination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.bolgov.spring.pagination.Exception.WrongPageFormatException;
import ru.bolgov.spring.pagination.Exception.WrongSizeFormatException;
import ru.bolgov.spring.pagination.service.CarService;

import java.util.Map;

@Controller
@RequestMapping("/view")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/cars")
    public String all(Model model, @RequestParam Map<String, String> headers) throws WrongSizeFormatException, WrongPageFormatException {
        int page = 0;
        String currentPage = "1";
        int limit = 10;
        if(headers.containsKey("limit")){
            try {
                limit =  Integer.parseInt(headers.get("limit"));
            }catch (Exception e){
                throw new WrongSizeFormatException();
            }
        }
        if(headers.containsKey("page")){
            try {
                page = (Integer.parseInt(headers.get("page")) * limit) - limit;
                currentPage = headers.get("page");
            }catch (Exception e){
                throw new WrongPageFormatException();
            }
        }
        model.addAttribute("cars", carService.findByPage(page, limit));

        long countPages = 1 + (carService.count() / limit);

        if(carService.count() % limit == 0){
            countPages--;
        }

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pages", countPages);
        model.addAttribute("limit", limit);


        return "cars";
    }
}
