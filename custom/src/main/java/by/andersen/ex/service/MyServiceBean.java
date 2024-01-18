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

public class MyServiceBean  {
    private String name;
    private String path;

    public MyServiceBean() {

    }
@JsonCreator
    public MyServiceBean(@JsonProperty("name") String name,
                         @JsonProperty("path") String path) {
        this.name = name;
        this.path = path;
    }

    public String action() {
        return "this is custom bean: name=" + name + " path=" + path ;
    }

    public void initMethod(){
        System.out.println("this is init!");
    }
    public void destroyMethod(){
        System.out.println("this is init!");
    }
}

