package ex.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Bean;

@Getter
@Setter
@ToString
public class MyServiceBean {
    private String name;
    private String path;

    public MyServiceBean() {

    }

    public void myInit(){
        System.out.println("my init method");
    }

    public String action() {

        return "this is groovy bean: " + name + " " + path;
    }
}
