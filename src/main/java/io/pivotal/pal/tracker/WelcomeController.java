package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {


    public WelcomeController(@Value("${WELCOME_MESSAGE}") String welcomeMessage){
        this.welcomeMessage=welcomeMessage;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }


    private String welcomeMessage;

    @GetMapping("/")
    public String sayHello() {
        return welcomeMessage;
    }
}
