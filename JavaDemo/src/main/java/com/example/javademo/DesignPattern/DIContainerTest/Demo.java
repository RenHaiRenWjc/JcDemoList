package com.example.javademo.DesignPattern.DIContainerTest;

/**
 * ClassName: com.example.javademo.DesignPattern.DIContainerTest
 * Description:
 * JcChen on 2020.04.05.21:27
 */
public class Demo {
  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
    RateLimiter rateLimiter = (RateLimiter) applicationContext.getBean("rateLimiter");
    rateLimiter.test();
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
}
