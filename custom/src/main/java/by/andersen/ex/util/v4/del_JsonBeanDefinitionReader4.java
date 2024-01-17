package by.andersen.ex.util.v4;

import by.andersen.ex.service.MyServiceBean;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.support.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.util.Map;

public class del_JsonBeanDefinitionReader4 implements BeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    public del_JsonBeanDefinitionReader4(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    @Override
    public int loadBeanDefinitions(String location) throws BeanDefinitionStoreException {
        String jsonConfiguration = "{ \"beans\": { \"myBean\": " +
                "{ \"fields\": { \"name\": \"TestName\", \"path\": \"/test\" }, " +
                "\"initMethod\": \"initMethod\", " +
                "\"destroyMethod\": \"destroyMethod\", " +
                "\"scope\": \"singleton\" } } }";
        JsonBeanWrapper beanWrapper = JsonBeanWrapper.fromJson(jsonConfiguration);
        for (Map.Entry<String, JsonBeanConfig> entry : beanWrapper.getBeans().entrySet()) {
            registerBean(entry.getKey(), entry.getValue());
        }
        return 0;
    }

    private void registerBean(String beanName, JsonBeanConfig beanConfig) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(MyServiceBean.class);

        for (Map.Entry<String, Object> fieldEntry : beanConfig.getFields().entrySet()) {
            beanDefinitionBuilder.addPropertyValue(fieldEntry.getKey(), fieldEntry.getValue());
        }

        if (beanConfig.getInitMethod() != null) {
            beanDefinitionBuilder.setInitMethodName(beanConfig.getInitMethod());
        }

        if (beanConfig.getDestroyMethod() != null) {
            beanDefinitionBuilder.setDestroyMethodName(beanConfig.getDestroyMethod());
        }

        if (beanConfig.getScope() != null) {
            beanDefinitionBuilder.setScope(beanConfig.getScope());
        }

        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        registry.registerBeanDefinition(beanName, beanDefinition);
    }


    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return null;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return null;
    }

    @Override
    public BeanNameGenerator getBeanNameGenerator() {
        return null;
    }

    @Override
    public int loadBeanDefinitions(Resource resource) throws BeanDefinitionStoreException {
        return 0;
    }

    @Override
    public int loadBeanDefinitions(Resource... resources) throws BeanDefinitionStoreException {
        return 0;
    }


    @Override
    public int loadBeanDefinitions(String... locations) throws BeanDefinitionStoreException {
        return 0;
    }
}
