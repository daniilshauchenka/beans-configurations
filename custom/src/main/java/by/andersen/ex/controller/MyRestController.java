package by.andersen.ex.controller;

import by.andersen.ex.service.MyServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
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
    public MyRestController(MyServiceBean myServiceBean, ApplicationContext  applicationContext) {
        this.myServiceBean = myServiceBean;
        this.applicationContext = applicationContext;
    }

    @GetMapping("/test")
    public String test() throws Exception {
        System.out.println("test");


        String [] beanNames = applicationContext.getBeanDefinitionNames();

        for (String name:beanNames) {
           System.out.println(name);
        }
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) applicationContext.getAutowireCapableBeanFactory();
        BeanDefinition def = registry.getBeanDefinition("myXMLBean");

        return "class: "+def.getBeanClassName() + " | scope: " +def.getScope() + " | init: " + def.getInitMethodName() + " | destroy: " + def.getDestroyMethodName() ;
    }



    @GetMapping("/getBean")
    public MyServiceBean getBean() throws Exception {
        System.out.println("getBean");

        return myServiceBean;
    }

}
