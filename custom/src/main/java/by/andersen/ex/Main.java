package by.andersen.ex;

import by.andersen.ex.service.MyServiceBean;
import by.andersen.ex.util.JsonBeanDefinitionRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@Import(MyServiceBeanLoader.class)
//@Import(JSONBeanDefinitionRegistrar.class)
@Import(JsonBeanDefinitionRegistrar.class)
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
//
//        String[] beanNames = context.getBeanDefinitionNames();
//        MyServiceBean first = context.getBean(MyServiceBean.class);
//        System.out.println(first);
//        for (String name:beanNames) {
//         //   System.out.println(name);
//        }
//
//        MyServiceBean retrievedBean = context.getBean(MyServiceBean.class);
//        System.out.println(retrievedBean.action());
    }

}
