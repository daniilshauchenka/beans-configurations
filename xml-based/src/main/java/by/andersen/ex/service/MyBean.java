package by.andersen.ex.service;

import lombok.ToString;
import org.springframework.stereotype.Component;


@ToString
public class MyBean {
    private String name;
    private String path;
    public MyBean(){
        System.out.println("mpty");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("set name "  +name);
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        System.out.println("set path "  +path);
        this.path = path;
    }

    public void init(){
        System.out.println("init!!!");
    }

    public void destroy(){
        System.out.println("destroy!!!");
    }

    public String action(){
        return this.name + " " + this.path;
    }
}
