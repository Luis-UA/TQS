package tqs.luispereira.homework1;

import java.util.concurrent.TimeUnit;

public class Cache {
    private TtlHashMap<String, AQModel> storedmodels = new TtlHashMap<>(TimeUnit.SECONDS, 40);

    private int requests = 0;
    private int hits = 0;
    private int misses = 0;

    public void storeincache(String name,AQModel model){
        storedmodels.put(name, model);
    }

    public boolean checkincache(String name){
        return storedmodels.containsKey(name);
    }

    public AQModel getincache(String name){
        return storedmodels.get(name);
    }

    public int getRequests() {
        return requests;
    }

    public void incrRequests() {
        this.requests += 1;
    }

    public int getHits() {
        return hits;
    }

    public void incrHits() {
        this.hits += 1;
    }

    public int getMisses() {
        return misses;
    }

    public void incrMisses() {
        this.misses += 1;
    }
}
