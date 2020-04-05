package com.example.javademo.DesignPattern.DIContainerTest;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: com.example.javademo.DesignPattern.DIContainerTest
 * Description:
 * JcChen on 2020.04.05.22:35
 */
public class BeanDefinition {
  private String id;
  private String className;
  private List<ConstructorArg> constructorArgs = new ArrayList<>();
  private Scope scope = Scope.SINGLETON;
  private boolean lazyInit = false; //是否支持懒加载，默认不支持，对象在应用启动时就已经创建好

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public List<ConstructorArg> getConstructorArgs() {
    return constructorArgs;
  }

  public void setConstructorArgs(List<ConstructorArg> constructorArgs) {
    this.constructorArgs = constructorArgs;
  }

  public Scope getScope() {
    return scope;
  }

  public void setScope(Scope scope) {
    this.scope = scope;
  }

  public boolean isLazyInit() {
    return lazyInit;
  }

  public void setLazyInit(boolean lazyInit) {
    this.lazyInit = lazyInit;
  }

  public boolean isSingleton() {
    return scope.equals(Scope.SINGLETON);
  }

  public static enum Scope {
    SINGLETON,  // 返回新创建的对象
    PROTOTYPE   // 返回单例对象
  }

  public static class ConstructorArg {
    private boolean isRef; //参数是否有依赖对象
    private Class type;
    private Object arg;

    public boolean getIsRef() {
      return isRef;
    }

    public void setRef(boolean ref) {
      isRef = ref;
    }

    public Class getType() {
      return type;
    }

    public void setType(Class type) {
      this.type = type;
    }

    public Object getArg() {
      return arg;
    }

    public void setArg(Object arg) {
      this.arg = arg;
    }
  }
}

  /*配置文件beans.xml：
<beans>
<bean id="rateLimiter" class="com.xzg.RateLimiter">
<constructor-arg ref="redisCounter"/>
</bean>

<bean id="redisCounter" class="com.xzg.redisCounter">
<constructor-arg type="String" value="127.0.0.1">
<constructor-arg type="int" value=1234>
</bean>
</beans>*/
