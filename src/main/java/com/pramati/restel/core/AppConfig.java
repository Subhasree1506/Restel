package com.pramati.restel.core;

import com.pramati.restel.core.managers.ExcelParseManager;
import com.pramati.restel.core.managers.RequestManager;
import com.pramati.restel.core.model.comparator.ResponseComparator;
import com.pramati.restel.testng.MatcherFactory;
import com.pramati.restel.testng.TestCaseExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Responsible for the spring configuration of the app.
 *
 * @author kannanr
 */
@Configuration
@ComponentScan(basePackages = {"com.pramati.restel"})
@PropertySource(
    value = {"classpath:application.properties"},
    ignoreResourceNotFound = false)
public class AppConfig {

  @Bean
  RequestManager reqManager(@Value("") String url) {
    return new RequestManager(url);
  }

  @Bean
  @Scope(value = "prototype")
  TestCaseExecutor testExecutor(String executionInstanceName) {
    return new TestCaseExecutor(executionInstanceName);
  }

  /**
   * Instantiates Factory bean. This will take care of the magic required to initialize the {@link
   * ResponseComparator} instances.
   *
   * @return {@link MatcherFactory} instance.
   */
  @Bean
  public ServiceLocatorFactoryBean factoryBean() {
    ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
    factoryBean.setServiceLocatorInterface(MatcherFactory.class);
    return factoryBean;
  }

  @Bean
  public static PropertySourcesPlaceholderConfigurer properties() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  @Bean
  ExcelParseManager excelParseManager(@Value("${app.excelFile}") String excelFilePath) {
    return new ExcelParseManager(excelFilePath);
  }
}
