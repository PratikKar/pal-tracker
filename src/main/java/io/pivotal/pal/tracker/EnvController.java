package io.pivotal.pal.tracker;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {



    private String  port;
    private String memoryLimit;
    private String cfInstanceIndex;
    private String cfInstanceAddr;

    public EnvController(
            @Value("${PORT:NOT SET}") String port,
            @Value("${MEMORY_LIMIT :NOT SET }") String memoryLimit,
            @Value("${CF_INSTANCE_INDEX :NOT SET }") String cfInstanceIndex,
            @Value("${CF_INSTANCE_ADDR :NOT SET }") String cfInstanceAddr) {
        this.port = port;
        this.memoryLimit = memoryLimit;
        this.cfInstanceIndex = cfInstanceIndex;
        this.cfInstanceAddr = cfInstanceAddr;
    }

    @GetMapping("/env")
    public Map<String, String> getEnv() throws Exception {
        Map<String, String > map =new HashMap<String, String>();
        map.put("CF_INSTANCE_INDEX",this.getCfInstanceIndex());
        map.put("CF_INSTANCE_ADDR",this.getCfInstanceAddr());
        map.put("PORT",this.getPort());
        map.put("MEMORY_LIMIT",this.getMemoryLimit());
    return map;
    }


    public String getCfInstanceAddr() {
        return cfInstanceAddr;
    }

    public void setCfInstanceAddr(String cfInstanceAddr) {
        this.cfInstanceAddr = cfInstanceAddr;
    }


    public String  getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(String memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public String getCfInstanceIndex() {
        return cfInstanceIndex;
    }

    public void setCfInstanceIndex(String cfInstanceIndex) {
        this.cfInstanceIndex = cfInstanceIndex;
    }




}
