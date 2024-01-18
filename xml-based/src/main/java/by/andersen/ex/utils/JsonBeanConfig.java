package by.andersen.ex.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class JsonBeanConfig {
    private String beanType;
    private Map<String, Object> fields;
    private String initMethod;
    private String destroyMethod;
    private String scope;

    public JsonBeanConfig() {
    }

    public JsonBeanConfig(String beanType, Map<String, Object> fields, String initMethod, String destroyMethod, String scope) {
        this.beanType = beanType;
        this.fields = fields;
        this.scope = scope;
        this.initMethod = initMethod;
        this.destroyMethod = destroyMethod;
    }
}
