package ex.controller;


import ex.service.MyServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    private final MyServiceBean myServiceBean;

    public MyRestController(MyServiceBean myServiceBean) {
        this.myServiceBean = myServiceBean;
    }

    @GetMapping("/test")
    public String test() throws Exception {
        return myServiceBean.action();
    }
}
