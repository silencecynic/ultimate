package com.api.boot.modules.infrastructure.aop.router;


import com.zaxxer.hikari.HikariDataSource;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertyNameAliases;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;


import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <link>https://www.cnblogs.com/badtheway/p/9061065.html</link>
 */
public abstract class AbstractRoute implements Route , ImportBeanDefinitionRegistrar, EnvironmentAware {

  private final static ConfigurationPropertyNameAliases alias = new ConfigurationPropertyNameAliases();

  private Environment env;

  private Map<String,Object> targetDataSource;

  private Binder binder;

  static {
    alias.addAliases("url", "jdbc-url");
    alias.addAliases("username","user");
  }

  @Override
  public void setEnvironment (Environment environment) {
    this.env = environment;
    binder = Binder.get(env);
  }

  @Override
  @SuppressWarnings("unchecked")
  public void registerBeanDefinitions (AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
    Map<String,Object> config, properties, defaultConfig = binder.bind("spring.datasource", Map.class).get();
    targetDataSource = new HashMap<>(); //默认配置
    properties = defaultConfig;
    String property = env.getProperty("spring.datasource.type"); //默认数据源类型
    Class<? extends DataSource> targetClass = getDataType(property); //获取数据源类型
    DataSource consumerDatasource, defaultDatasource = bind(targetClass, properties); //绑定默认数据源参数
    List<Map> configs = binder.bind("spring.datasource.multi", Bindable.listOf(Map.class)).get(); //获取其他数据源配置
    for (int i = 0; i < configs.size(); i++) { //遍历生成其他数据源
      config = configs.get(i);
      targetClass = getDataType((String) config.get("type"));
      if ((boolean) config.getOrDefault("extend", Boolean.TRUE)) { //获取extend字段，未定义或为true则为继承状态
        properties = new HashMap(defaultConfig); //继承默认数据源配置
        properties.putAll(config); //添加数据源参数
      } else {
        properties = config; //不继承默认配置
      }
      consumerDatasource = bind(targetClass, properties); //绑定参数
      targetDataSource.put(config.get("key").toString(), consumerDatasource); //获取数据源的key，以便通过该key可以定位到数据源
    }
    GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition(); //bean定义类
    genericBeanDefinition.setBeanClass(RoutingDataSource.class); //设置bean的类型，此处MultiDataSource是继承AbstractRoutingDataSource的实现类
    MutablePropertyValues mutablePropertyValues = genericBeanDefinition.getPropertyValues(); //需要注入的参数，类似spring配置文件中的<property/>
    mutablePropertyValues.add("defaultTargetDataSource", defaultDatasource); //添加默认数据源，避免key不存在的情况没有数据源可用
    mutablePropertyValues.add("targetDataSources", targetDataSource); //添加其他数据源
    registry.registerBeanDefinition("datasource", genericBeanDefinition); //将该bean注册为datasource，不使用springboot自动生成的datasource
  }

  @SuppressWarnings("unchecked")
  private Class<? extends DataSource> getDataType(String name) {
    Class<? extends DataSource> type;
    try {
      if (StringUtils.hasLength(name)) { //字符串不为空则通过反射获取class对象
        type = (Class<? extends DataSource>) Class.forName(name);
      } else {
        type = HikariDataSource.class;  //默认为hikariCP数据源，与springboot默认数据源保持一致
      }
      return type;
    } catch (Exception e) {
      throw new IllegalArgumentException("can not resolve class with type: " + name); //无法通过反射获取class对象的情况则抛出异常，该情况一般是写错了，所以此次抛出一个runtimeexception
    }
  }

  private void bind(DataSource dataSource, Map<String,Object> properties) {
    ConfigurationPropertySource configurationPropertySource = new MapConfigurationPropertySource(properties);
    Binder binder = new Binder(configurationPropertySource.withAliases(alias));
    binder.bind(ConfigurationPropertyName.EMPTY, Bindable.ofInstance(dataSource));  //将参数绑定到对象
  }

  private <T extends DataSource> T bind(Class<T> targetClass, Map<String,Object> properties) {
    ConfigurationPropertySource configurationPropertySource = new MapConfigurationPropertySource(properties);
    Binder binder = new Binder(configurationPropertySource.withAliases(alias));
    return binder.bind(ConfigurationPropertyName.EMPTY, Bindable.of(targetClass)).get(); //通过类型绑定参数并获得实例对象
  }

  @SuppressWarnings("unchecked")
  private <T extends DataSource> T bind(Class<T> targetClass, String path) {
    Map<String,Object> properties = binder.bind(path, Map.class).get();
    return bind(targetClass, properties);
  }
}
