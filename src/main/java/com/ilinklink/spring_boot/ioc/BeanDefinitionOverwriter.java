package com.ilinklink.spring_boot.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * Register
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2021/1/20  14:55
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
@Slf4j
@Component
public class BeanDefinitionOverwriter implements BeanDefinitionRegistryPostProcessor {

    @SneakyThrows
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

        String [] beanDefinitionNames=beanDefinitionRegistry.getBeanDefinitionNames();

        //遍历拿到，模拟先删后加。
        for (String name:beanDefinitionNames){
            if(name.equals("iocTestService")){
                log.error("BeanDefinitionOverwriter","遍历得到指定的类定义");
                beanDefinitionRegistry.removeBeanDefinition("iocTestService");
            }
        }


        //下面这个类定义，其实是个无效的类定义，因为它是一个接口，没有实例的接口。我们可以通过类实例化的后置处理器去构建bean的实例，这样就会直接使用我们提供的实例（动态代理），而不是框架的反射
        BeanDefinitionBuilder b=BeanDefinitionBuilder.rootBeanDefinition(Class.forName("com.ilinklink.spring_boot.ioc.IocTestService").getClass());
        beanDefinitionRegistry.registerBeanDefinition("iocTestService",b.getBeanDefinition());


        beanDefinitionNames=beanDefinitionRegistry.getBeanDefinitionNames();
        log.info("BeanDefinitionOverwriter","注入灵魂!!!!");

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }


}
