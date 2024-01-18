package by.andersen.ex.util.v5;

import by.andersen.ex.service.MyServiceBean;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonBeanDefinitionRegistrar5 implements ImportBeanDefinitionRegistrar {
    public JsonBeanDefinitionRegistrar5() {
    }

    private static Map<String, Class> beanTypes = new HashMap<>();

    static {
        beanTypes.put("MyServiceBean", MyServiceBean.class);
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //connect to API and get beans data
        String jsonConfiguration = "{ \"beans\": { \"myBean\": " +
                "{ \"beanType\": \"MyServiceBean\", " +
                "\"fields\": { \"name\": \"TestName78282828\", \"path\": \"/test412421\" }, " +
                "\"initMethod\": \"initMethod\", " +
                "\"destroyMethod\": \"destroyMethod\", " +
                "\"scope\": \"singleton\" } } }";

        jsonConfiguration = getJsonBeans();
        //parse and reg
        Map<String, JsonBeanConfig5> beansToRegister = JsonBeanWrapper5.fromJson(jsonConfiguration).getBeans();
        for (Map.Entry<String, JsonBeanConfig5> entry : beansToRegister.entrySet()) {
            registerBean(entry.getKey(), entry.getValue(), registry);
        }
    }


    private void registerBean(String beanName, JsonBeanConfig5 beanConfig, BeanDefinitionRegistry registry) {
        Class beanType = beanTypes.get(beanConfig.getBeanType());
        if (beanType == null) {
            throw new IllegalArgumentException("Unknown bean type: " + beanConfig.getBeanType());
        }

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(beanType);


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


    private String getJsonBeans() {
        System.out.println("loadBeansListFromAPI");
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        String URL = "http://localhost:8080/api/json";
        try (CloseableHttpResponse response = httpClient.execute(new HttpGet(URL))) {
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(response.getEntity());
            } else {
                System.err.println("HTTP request failed with status code: " + response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
