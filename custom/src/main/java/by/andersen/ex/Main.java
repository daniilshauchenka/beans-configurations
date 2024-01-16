package by.andersen.ex;

import by.andersen.ex.service.MyServiceBean;
import by.andersen.ex.util.CustomBeanDefinitionReader;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);




        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        CustomBeanDefinitionReader reader = new CustomBeanDefinitionReader((DefaultListableBeanFactory) context.getBeanFactory());
        reader.entry();
        context.refresh();
        String[] beanDefintions = context.getBeanDefinitionNames();
        MyServiceBean bean = context.getBean(MyServiceBean.class);

        System.out.println(bean);






    }

}
