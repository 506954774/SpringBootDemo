package com.ilinklink.spring_boot.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

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

        BeanDefinition proxyDefinitionClass=new BeanDefinition() {
            @Override
            public void setParentName(String s) {

            }

            @Override
            public String getParentName() {
                return null;
            }

            @Override
            public void setBeanClassName(String s) {

            }

            @Override
            public String getBeanClassName() {
                return null;
            }

            @Override
            public void setScope(String s) {

            }

            @Override
            public String getScope() {
                return null;
            }

            @Override
            public void setLazyInit(boolean b) {

            }

            @Override
            public boolean isLazyInit() {
                return false;
            }

            @Override
            public void setDependsOn(String... strings) {

            }

            @Override
            public String[] getDependsOn() {
                return new String[0];
            }

            @Override
            public void setAutowireCandidate(boolean b) {

            }

            @Override
            public boolean isAutowireCandidate() {
                return true;
            }

            @Override
            public void setPrimary(boolean b) {

            }

            @Override
            public boolean isPrimary() {
                return false;
            }

            @Override
            public void setFactoryBeanName(String s) {

            }

            @Override
            public String getFactoryBeanName() {
               // return IocTestServiceBeanFactory.class.getName();
                return null;
            }

            @Override
            public void setFactoryMethodName(String s) {

            }

            @Override
            public String getFactoryMethodName() {
                //return "newInstance";
                return null;
            }

            @Override
            public ConstructorArgumentValues getConstructorArgumentValues() {
               /* ConstructorArgumentValues result=new ConstructorArgumentValues();
                result.addIndexedArgumentValue(0,String.class);
                return result;*/
                return null;

            }

            @Override
            public MutablePropertyValues getPropertyValues() {
                return null;
            }

            @Override
            public void setInitMethodName(String s) {

            }

            @Override
            public String getInitMethodName() {
                return null;
            }

            @Override
            public void setDestroyMethodName(String s) {

            }

            @Override
            public String getDestroyMethodName() {
                return null;
            }

            @Override
            public void setRole(int i) {

            }

            @Override
            public int getRole() {
                return 0;
            }

            @Override
            public void setDescription(String s) {

            }

            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public ResolvableType getResolvableType() {
                return null;
            }

            @Override
            public boolean isSingleton() {
                return true;
            }

            @Override
            public boolean isPrototype() {
                return false;
            }

            @Override
            public boolean isAbstract() {
                return true;
            }

            @Override
            public String getResourceDescription() {
                return null;
            }

            @Override
            public BeanDefinition getOriginatingBeanDefinition() {
                return null;
            }

            @Override
            public void setAttribute(String s, Object o) {

            }

            @Override
            public Object getAttribute(String s) {
                return null;
            }

            @Override
            public Object removeAttribute(String s) {
                return null;
            }

            @Override
            public boolean hasAttribute(String s) {
                return false;
            }

            @Override
            public String[] attributeNames() {
                return new String[0];
            }
        };

        //添加到bean定义集合里，mybatis的mapper就是接口，使用此方式，添加到类定义集合里
        beanDefinitionRegistry.registerBeanDefinition("iocTestService",proxyDefinitionClass);


        BeanDefinitionBuilder b=BeanDefinitionBuilder.rootBeanDefinition(IocTestServiceImpl.class);
        //beanDefinitionRegistry.registerBeanDefinition("iocTestService",b.getBeanDefinition());


        beanDefinitionNames=beanDefinitionRegistry.getBeanDefinitionNames();

        log.info("BeanDefinitionOverwriter","注入灵魂");

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }


}
