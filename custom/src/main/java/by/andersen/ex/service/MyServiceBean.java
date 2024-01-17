package by.andersen.ex.service;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString

public class MyServiceBeanImpl implements MyServiceBean {
    private String name;
    private String path;

    public MyServiceBeanImpl() {

    }
@JsonCreator
    public MyServiceBeanImpl(@JsonProperty("name") String name,
                             @JsonProperty("path") String path) {
        this.name = name;
        this.path = path;
    }

    public String action() {
        return "this is custom bean: name=" + name + " path=" + path ;
    }
}

