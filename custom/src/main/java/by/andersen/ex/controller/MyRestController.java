package by.andersen.ex.controller;

import by.andersen.ex.service.MyServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyRestController {

    private final MyServiceBean myServiceBean;
    private final ApplicationContext applicationContext;


    @Autowired
    public MyRestController(MyServiceBean myServiceBean, ApplicationContext applicationContext) {
        this.myServiceBean = myServiceBean;
        this.applicationContext = applicationContext;
    }

    @GetMapping("/test")
    public String test() throws Exception {
        String [] beanNames = applicationContext.getBeanDefinitionNames();

        return myServiceBean.action();
    }
}
