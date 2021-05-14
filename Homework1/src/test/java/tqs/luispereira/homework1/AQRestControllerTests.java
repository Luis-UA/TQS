package tqs.luispereira.homework1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.when;

@WebMvcTest
class AQRestControllerTests {

    @Autowired
    private MockMvc mockmvc;

    @MockBean
    AQService service;

    AQModel model;

    @BeforeEach
    void setup(){
        model = new AQModel();
        model.setCityname("oporto");
    }

    @DisplayName("Valid City, not in cache")
    @Test
    void getTest_NotinCache() throws Exception {
        when(service.checkincache(Mockito.anyString())).thenReturn(false);
        when(service.fillModel(Mockito.anyString())).thenReturn(true);
        when(service.getModel()).thenReturn(model);

        //
        mockmvc.perform(get("/api/info/oporto"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("time")))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("cityname")))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("latitude")))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("longitude")))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("dominantpol")))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("pollutants")))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("aqi")));

    }

    @DisplayName("Valid City, in cache")
    @Test
    void getTest_inCache() throws Exception {
        when(service.checkincache(Mockito.anyString())).thenReturn(true);
        when(service.getincache(Mockito.anyString())).thenReturn(model);

        mockmvc.perform(get("/api/info/oporto"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("time")))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("cityname")))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("latitude")))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("longitude")))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("dominantpol")))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("pollutants")))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("aqi")));
    }

    @DisplayName("Invalid City")
    @Test
    void getTest_Invalid() throws Exception {
        when(service.checkincache(Mockito.anyString())).thenReturn(false);
        when(service.fillModel(Mockito.anyString())).thenReturn(false);

        mockmvc.perform(get("/api/info/Braquitolandia"))
                .andExpect(status().isNotFound());

    }



}
