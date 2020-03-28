package com.example.configserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
public class GreetingController {

    @Value("${my.greeting}")
    private String msgGreeting;

    @Value("${my.greeting1: default msgGreeting}")
    private String msgDefaultGreeting;

    @Value("static msgGreeting")
    private String msgStaticGreeting;

    @Value("${my.list.value}")
    private List<String> msgListGreeting;

    @Value("#{${my.db.values}}")
    private Map<String,String> msgMapGreeting;

/*    @GetMapping("/showGreeting")
    public String show(){
        return msgGreeting+" ," +msgStaticGreeting+" ," +msgDefaultGreeting+" ," +msgListGreeting+" ,"+ msgMapGreeting;
    }*/



    /*@GetMapping("/showGreeting")
    public String show(){
        return "Hello World";
    }*/

    @Autowired
    private DbSettings dbSettings;

    @GetMapping("/showGreeting")
    public String show(){

        return dbSettings.getConnection()+" "+dbSettings.getHost();
    }


    //should not use  Environment to fetch profiles or properties
    /*@Autowired
    private Environment env;

    @GetMapping("/showEnvDetails")
    public String showEnvDetails(){

        return env.toString();
    }*/
}

