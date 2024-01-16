package by.andersen.ex.service;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
public class MyServiceBean {
    private String name;
    private String path;

    private String someField;
    @Autowired
    public MyServiceBean() {

    }

    public String action() {
        return "this is custom bean: name=" + name + " path=" + path + " someField=" + someField;
    }
}

