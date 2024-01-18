package ex.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import ex.service.MyServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    private final MyServiceBean myServiceBean;
    private ObjectMapper mapper;

    public MyRestController(MyServiceBean myServiceBean) {
        this.myServiceBean = myServiceBean;
        this.mapper = new ObjectMapper();
    }

    @GetMapping("/test")
    public String test() throws Exception {
        return myServiceBean.action();
    }

    @GetMapping("/test2")
    public List<MyServiceBean> test2() throws Exception {
        List<MyServiceBean> list = new ArrayList<MyServiceBean>();
        list.add(myServiceBean);
        list.add(myServiceBean);
        return list;
    }

    @GetMapping("/test3")
    public MyServiceBean test3() throws Exception {
        return myServiceBean;
    }
}
