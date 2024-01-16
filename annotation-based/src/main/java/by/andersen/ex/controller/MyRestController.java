package by.andersen.ex.controller;
import by.andersen.ex.service.MyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api")
class MyRestController {
    @Autowired
    MyBean bean;

    @GetMapping("/test")
    public String test(){
        return bean.action();
    }
}