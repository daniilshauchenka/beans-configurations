package by.andersen.ex.util.v3;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class JSONBeanDefinitionReader implements BeanDefinitionReader {

    private final BeanDefinitionRegistry registry;
    private final ObjectMapper mapper = new ObjectMapper();

    public JSONBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }


    @Override
    public int loadBeanDefinitions(Resource resource) throws BeanDefinitionStoreException {
        
        return 0;
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
    public int loadBeanDefinitions(Resource... resources) throws BeanDefinitionStoreException {
        return 0;
    }

    @Override
    public int loadBeanDefinitions(String location) throws BeanDefinitionStoreException {
        return 0;
    }

    @Override
    public int loadBeanDefinitions(String... locations) throws BeanDefinitionStoreException {
        return 0;
    }
}
