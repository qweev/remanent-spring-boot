package aniela.remanent;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Testowy {

    @RequestMapping("/a")
    public String welcome() {

        return "welcome";
    }

}
