package com.learn.pact.producer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProducerController {

    @RequestMapping(value="hello-world", method=RequestMethod.GET)
    public @ResponseBody PactModel sayHello() {
        return new PactModel(1,"producer");
    }
}
