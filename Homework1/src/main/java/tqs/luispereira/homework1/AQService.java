package tqs.luispereira.homework1;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class AQService {
    private AQModel model;

    private Cache cache = new Cache();

    private RestTemplate restTemplate = new RestTemplate();

    public Cache getCache() {
        return cache;
    }

    public boolean checkincache(String city){
        return cache.checkincache(city);
    }

    public AQModel getincache(String city){
        return cache.getincache(city);
    }

    public void storeincache(String city, AQModel model){
        cache.storeincache(city,model);
    }

    public boolean fillModel(String Cityname) {
        try{
            cache.incrRequests();
            if(cache.checkincache(Cityname)){
                model = cache.getincache(Cityname);
                System.out.println("Got from cache "+ Cityname);
                cache.incrHits();
                return true;
            }else{
                model = new AQModel();
                var location = restTemplate.getForObject("https://api.waqi.info/feed/"+Cityname+"/?token=a84086b6af46dde4bd406ac2cf7117d987cf2479", String.class);

                JSONObject obj = new JSONObject(location);
                obj = obj.getJSONObject("data");
                model.setCityname(obj.getJSONObject("city").getString("name"));
                model.setLatitude(obj.getJSONObject("city").getJSONArray("geo").getBigDecimal(0));
                model.setLongitude(obj.getJSONObject("city").getJSONArray("geo").getBigDecimal(1));
                model.setTime(getDate(obj.getJSONObject("time").getString("s")));
                HashMap<String,Double> polutents = new HashMap<>();
                Iterator<String> i = obj.getJSONObject("iaqi").keys();
                while (i.hasNext()){
                    String key = i.next();
                    polutents.put(key, obj.getJSONObject("iaqi").getJSONObject(key).getDouble("v"));
                }
                model.setPolutents(polutents);
                model.setAqi(obj.getDouble("aqi"));
                model.setDominantpol(obj.getString("dominentpol"));
                cache.storeincache(Cityname, model);
                cache.incrHits();
                return true;
            }
        }catch(Exception e){
            System.out.println(e);
            cache.incrMisses();
            return false;
        }

    }

    public AQModel getModel(){
        return model;
    }

    public static Date getDate(String s) {
        int year = Integer.parseInt(s.substring(0,4));
        int month = Integer.parseInt(s.substring(5,7));
        int day = Integer.parseInt(s.substring(8,10));
        int hour = Integer.parseInt(s.substring(11,13));
        int minute = Integer.parseInt(s.substring(14,16));
        int second = Integer.parseInt(s.substring(17,19));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /*

     */
}
