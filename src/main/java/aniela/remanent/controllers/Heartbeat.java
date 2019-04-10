package aniela.remanent.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Heartbeat {

    @RequestMapping("/heartbeat")
    public String welcome() {

        return "OK";
    }

}
