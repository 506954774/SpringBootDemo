package com.ilinklink.spring_boot;

import com.chuck.framework.redis_distributed_lock.EnableRedisDistributedLock;
import com.ilinklink.spring_boot.upload.FastDFSClient;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Locale;

@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
@EnableSwagger2
@EnableScheduling
@EnableRedisDistributedLock
public class Application {


    private static final Logger logger = Logger.getLogger(Application.class);


    @Value("${dfs.client.conf}")
    private String dfsClientPath;
    @Value(value = "${spring.messages.basename}")
    private String i18nBasename;
    @Value("${dfs.client.conf}")
    private String dfsClient;

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        // 默认语言
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        // 参数名
        lci.setParamName("lang");
        return lci;
    }

    @Bean(name = "messageSource")
    public ResourceBundleMessageSource messageResource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(i18nBasename);
        return messageSource;
    }

    public static void main(String[] args) {

        logger.info("**********************************************************************");
        logger.info("启动服务...");

        SpringApplication.run(Application.class, args);
        logger.info("启动服务完成。");
        logger.info("**********************************************************************");
        logger.info("**********************************************************************");
        logger.info("**********************************************************************");
        logger.info("**********************************************************************");
        logger.info("**********************************************************************");
        logger.info("**********************************************************************");
        logger.info("**********************************************************************");
        logger.info("**********************************************************************");
        logger.info("**********************************************************************");
        logger.info("**********************************************************************");
        logger.info("**********************************************************************");
        logger.info("**********************************************************************");
        logger.info("**********************************************************************");
        logger.info("**********************************************************************");
        logger.info("**********************************************************************");
        logger.info("**********************************************************************");
        logger.info("**********************************************************************");
    }


    @Bean
    FastDFSClient fastDFSClient() {
        logger.info(ServerConstants.LOGGER_PREFIX + "FastDFSClient配置文件[" + dfsClientPath + "].");
        try {
            return new FastDFSClient(dfsClientPath);
        } catch (Exception e) {
            logger.error(ServerConstants.LOGGER_PREFIX + "FastDFSClient实例化失败！", e);
            return null;
        }
    }


    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).build();
    }
}
