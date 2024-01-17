package by.andersen.ex.util.v5;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class JsonBeanConfig5 {
    private String beanType;
    private Map<String, Object> fields;
    private String initMethod;
    private String destroyMethod;
    private String scope;

    public JsonBeanConfig5(String beanType, Map<String, Object> fields, String initMethod, String destroyMethod, String scope) {
        this.beanType = beanType;
        this.fields = fields;
        this.scope = scope;
        this.initMethod = initMethod;
        this.destroyMethod = destroyMethod;
    }


}
