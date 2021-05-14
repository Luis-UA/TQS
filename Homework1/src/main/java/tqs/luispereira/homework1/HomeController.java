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
        return index;
    }

    @GetMapping("/info/{city}")
    public ModelAndView getdata(@PathVariable("city") String city){
        AQModel m;
        if(!service.fillModel(city)) {
            System.out.println("OOOOF");
            return index;
        }else {
            m = service.getModel();
            System.out.println("Not from cache "+ city);
        }
        index.addObject("CityName", m.getCityname());
        index.addObject("Lat", m.getLatitude());
        index.addObject("Long", m.getLongitude());
        index.addObject("ForecastDate", m.getTime());
        index.addObject("Aqi",m.getAqi());
        index.addObject("dominantpol",m.getDominantpol());
        index.addObject("pollutants",m.getPolutents());
        return index;
    }
}
