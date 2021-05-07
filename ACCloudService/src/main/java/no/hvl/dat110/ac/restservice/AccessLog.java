package no.hvl.dat110.ac.restservice;

import com.google.gson.Gson;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AccessLog {

    private final static Gson gson = new Gson();

    // atomic integer used to obtain identifiers for each access entry
    private final AtomicInteger cid;
    protected ConcurrentHashMap<Integer, AccessEntry> log;

    public AccessLog() {
        this.log = new ConcurrentHashMap<>();
        cid = new AtomicInteger(0);
    }

    // add an access entry to the log for the provided message and return assigned id
    public int add(String message) {

        int id = cid.addAndGet(1);
        log.put(id, new AccessEntry(id, message));
        return id;
    }

    // retrieve a specific access entry from the log
    public AccessEntry get(int id) {

        return log.get(id);
    }

    // clear the access entry log
    public void clear() {
        log.clear();
    }

    // return JSON representation of the access log
    public String toJson() {
        return gson.toJson(log.values());
    }
}
