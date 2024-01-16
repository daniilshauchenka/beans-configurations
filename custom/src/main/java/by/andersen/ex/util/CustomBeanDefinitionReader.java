package by.andersen.ex.util;

import by.andersen.ex.service.MyServiceBean;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.parsing.Location;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.Resource;

public class CustomBeanDefinitionReader extends XmlBeanDefinitionReader {
    public CustomBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public void registerBeansFromRawData(String data){
        BeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClassName(MyServiceBean.class.getName());
        beanDefinition.getPropertyValues().add("someField", data);
        this.getRegistry().registerBeanDefinition("myServiceBean", beanDefinition);
    }

    @Override
    public int loadBeanDefinitions(String... locations) throws BeanDefinitionStoreException {
        registerBeansFromRawData("valueee");
        return 1;
    }

    public int entry(){
        return loadBeanDefinitions(new String[]{});
    }

}
