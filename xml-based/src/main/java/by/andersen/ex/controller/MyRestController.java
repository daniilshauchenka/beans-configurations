package by.andersen.ex.controller;

import by.andersen.ex.service.MyBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class MyRestController {

    @GetMapping("/test")
    public String test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        return ((MyBean) context.getBean("xmlBean")).action();
    }
}
