package tqs.luispereira.homework1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CacheTests {
    AQModel model;
    Cache cache;

    @MockBean
    AQService service;

    @BeforeEach
    void setup(){
        model = new AQModel();
        model.setCityname("Mordor");
        cache = new Cache();
        cache.storeincache("Mordor", model);
    }

    @DisplayName("Storing an AQModel")
    @Test
    void storeTest() {
        AQModel model2 = new AQModel();
        model2.setCityname("Shire");
        when(service.getModel()).thenReturn(model2);
        cache.storeincache("Shire",service.getModel());
        assertTrue(cache.checkincache("Shire"));
    }

    @DisplayName("Check if in cache")
    @Test
    void checkTest() {
        assertTrue(cache.checkincache("Mordor"));
    }

    @DisplayName("Get from cache")
    @Test
    void getTest() {
        assertEquals("Mordor", cache.getincache("Mordor").getCityname());
    }

    @DisplayName("Incrementing number of requests")
    @Test
    void reqCountTest() {
        cache.incrRequests();
        cache.incrRequests();
        cache.incrRequests();
        assertEquals(3, cache.getRequests());

    }

    @DisplayName("Incrementing number of hits")
    @Test
    void hitCountTest(){
        cache.incrHits();
        cache.incrHits();
        cache.incrHits();
        assertEquals(3,cache.getHits());
    }

    @DisplayName("Incrementing number of misses")
    @Test
    void missCountTest(){
        cache.incrMisses();
        cache.incrMisses();
        cache.incrMisses();
        assertEquals(3, cache.getMisses());
    }
}
