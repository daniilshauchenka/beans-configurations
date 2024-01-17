package by.andersen.ex.util;

import by.andersen.ex.service.MyServiceBean;
import by.andersen.ex.service.MyServiceBeanImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MyServiceBeanLoader implements ImportBeanDefinitionRegistrar {

    private static final String URL = "http://localhost:8082/api/test3";
    private static final ObjectMapper mapper = new ObjectMapper();

    public MyServiceBean loadBeansListFromAPI(String URL) throws IOException {
        System.out.println("loadBeansListFromAPI");
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        CloseableHttpResponse response = httpClient.execute(new HttpGet(URL));
        MyServiceBean bean = mapper.readValue(response.getEntity().getContent(), MyServiceBeanImpl.class);
        System.out.println("bean got " + bean);
        return bean;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        System.out.println("registerBeanDefinitions");
        MyServiceBean beanToRegister = null;
        try {
            beanToRegister = loadBeansListFromAPI(URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (beanToRegister != null) {
            registerBean(registry, beanToRegister.getClass().getName(), MyServiceBeanImpl.class);
        } else {
            System.out.println("bean is null");;
        }

    }

    private void registerBean(BeanDefinitionRegistry registry, String beanName, Class<?> beanClass) {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClassName(beanClass.getName());
        beanDefinition.setAutowireCandidate(true);
        beanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
        registry.registerBeanDefinition(beanName, beanDefinition);
    }
}
