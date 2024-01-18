package by.andersen.ex.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonBeanWrapper {

    private final Map<String, JsonBeanConfig> beans;

    public JsonBeanWrapper(Map<String, JsonBeanConfig> beans) {
        this.beans = beans;
    }

    public Map<String, JsonBeanConfig> getBeans() {
        return beans;
    }

    public static JsonBeanWrapper fromJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(json).get("beans");
            Map<String, JsonBeanConfig> beanConfigs = new HashMap<>();
            Iterator<Map.Entry<String, JsonNode>> fieldsIterator = root.fields();
            while (fieldsIterator.hasNext()) {
                Map.Entry<String, JsonNode> field = fieldsIterator.next();
                String beanName = field.getKey();
                JsonNode beanNode = field.getValue();
                String beanType = beanNode.get("beanType").asText();
                JsonNode fieldsNode = beanNode.get("fields");
                Map<String, Object> fields = objectMapper.convertValue(fieldsNode, Map.class);
                String initMethod = beanNode.get("initMethod").asText();
                String destroyMethod = beanNode.get("destroyMethod").asText();
                String scope = beanNode.get("scope").asText();

                JsonBeanConfig beanConfig = new JsonBeanConfig(beanType,fields, initMethod, destroyMethod, scope);
                beanConfigs.put(beanName, beanConfig);
            }

            return new JsonBeanWrapper(beanConfigs);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

    }
}
