package by.andersen.ex.controller;

import by.andersen.ex.service.MyBean;
import by.andersen.ex.utils.BeanExporter;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class MyRestController {

    private final MyBean myBean;

    private final BeanExporter beanExporter;

    @Autowired
    public MyRestController(MyBean myBean, BeanExporter beanExporter) {
        this.myBean = myBean;
        this.beanExporter = beanExporter;
    }

    @GetMapping("/test")
    public String test() {
//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        return ((MyBean) context.getBean("xmlBean")).action();
        return myBean.toString();
    }

    @GetMapping("/json")
    public String getJson() throws JsonProcessingException {
        System.out.println("json");
     //   return beanExporter.toJson("xmlBean");

            return   "{ \"beans\": { \"myXMLBean\": " +
                "{ \"beanType\": \"MyServiceBean\", " +
                "\"fields\": { \"name\": \"xml app\", \"path\": \"/xmlllllllll\" }, " +
                "\"initMethod\": \"initMethod\", " +
                "\"destroyMethod\": \"destroyMethod\", " +
                "\"scope\": \"singleton\" } } }";
    }
}
