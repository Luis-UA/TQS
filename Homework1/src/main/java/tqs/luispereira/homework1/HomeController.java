package tqs.luispereira.homework1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private ModelAndView index = new ModelAndView("home");

    @Autowired
    private AQService service = new AQService();

    @GetMapping("/")
    public ModelAndView ind(){
        return new ModelAndView("home");
    }

    @GetMapping("/info/{city}")
    public ModelAndView getdata(@PathVariable("city") String city){
        if(service.fillModel(city)) {
            index.addObject("CityName", service.getModel().getCityname());
            index.addObject("Lat", "Coordinates: " + service.getModel().getLatitude());
            index.addObject("Long", ", " + service.getModel().getLongitude());
            index.addObject("ForecastDate", "Last Updated: " +service.getModel().getTime());
            index.addObject("Aqi", "Air Quality Index: "+ service.getModel().getAqi());
            index.addObject("dominantpol", service.getModel().getDominantpol());
            index.addObject("pollutants", service.getModel().getPolutents());
        }else{
            index = new ModelAndView("home");
            index.addObject("CityName", "City Not Found!");
        }
        return index;
    }
}
