package by.andersen.ex.service;

        import lombok.Getter;
        import lombok.Setter;
        import lombok.ToString;
        import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
public class MyBean {
    private String name;
    private String path;
    public MyBean(){

    }

    public String action(){
        return name + " " + path;
    }
}
