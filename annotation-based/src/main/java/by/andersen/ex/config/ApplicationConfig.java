package by.andersen.ex.config;
import by.andersen.ex.service.MyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
        public MyBean myBean(){
            MyBean bean = new MyBean();
            bean.setName("annotationBean");
            bean.setPath("annotationPath");
            return bean;
    }
}
