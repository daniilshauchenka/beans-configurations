package by.andersen.ex.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class BeanExporter {
    private final ApplicationContext applicationContext;

    @Autowired
    public BeanExporter(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
//
//    public String toJson(String beanName) throws JsonProcessingException {
//        ConfigurableListableBeanFactory factory = ((ConfigurableListableBeanFactory) applicationContext.getAutowireCapableBeanFactory());
//        BeanDefinition beanDefinition = factory.getBeanDefinition(beanName);
//
//        Map<String, Object> beanProperties = new HashMap<>();
//        beanProperties.put("beanType", beanDefinition.getBeanClassName());
//
//        for (PropertyValue propertyName : beanDefinition.getPropertyValues().getPropertyValueList()) {
//            Object propertyValue = null;
//            try {
//                propertyValue = factory.getBean(beanName).getClass().getMethod("get" + StringUtils.capitalize(propertyName.getName())).invoke(factory.getBean(beanName));
//                beanProperties.put(propertyName.getName(), propertyValue);
//            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//                e.printStackTrace();
//            }
//        }
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        Map<String, Object> jsonStructure = new HashMap<>();
//        jsonStructure.put("beans", Map.of(beanName, beanProperties));
//
//        return objectMapper.writeValueAsString(jsonStructure);
//    }

    public JsonBeanConfig toJsonBeanConfig(String beanName) throws JsonProcessingException {
        ConfigurableListableBeanFactory factory = ((ConfigurableListableBeanFactory) applicationContext.getAutowireCapableBeanFactory());
        BeanDefinition beanDefinition = factory.getBeanDefinition(beanName);

        JsonBeanConfig jsonBeanConfig = new JsonBeanConfig();
        jsonBeanConfig.setBeanType(beanDefinition.getBeanClassName());
        Map<String, Object> beanProperties = new HashMap<>();
        beanProperties.put("beanType", beanDefinition.getBeanClassName());

        if (beanDefinition instanceof AbstractBeanDefinition) {
            AbstractBeanDefinition abstractBeanDefinition = (AbstractBeanDefinition) beanDefinition;

            // Получаем информацию о свойствах бина
            for (PropertyValue propertyValue : abstractBeanDefinition.getPropertyValues().getPropertyValues()) {
                beanProperties.put(propertyValue.getName(), propertyValue.getValue());
            }

            // Получаем информацию о scope, destroyMethod и initMethod
            beanProperties.put("scope", abstractBeanDefinition.getScope());
            beanProperties.put("destroyMethod", abstractBeanDefinition.getDestroyMethodName());
            beanProperties.put("initMethod", abstractBeanDefinition.getInitMethodName());
        }


        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonStructure = Map.of("beans", Map.of(beanName, beanProperties));

        //return objectMapper.writeValueAsString(jsonStructure);
        return null;
    }

}
