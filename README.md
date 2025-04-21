# Toby-s-Spring-Spring-Boot
토비의 스프링 / 스프링부트 


### 애플리케이션 빈 vs 컨테이너 인프라스트럭쳐 빈
#### 애플리케이션 빈
- 개발자가 어떤 빈을 사용하겠다 라고 명시적으로 구성 정보를 제공
  - 애플리케이션 로직 빈(사용자 구성 정보) -> ComponentScan 활용
  - ex) Controller
  - 애플리케이션 인프라스트럭쳐 빈(자동 구성 정보) -> AutoConfiguration 활용
  - ex) DataSource, JdbcTransactionManager
  - ex) TomcatServletWebServerFactory, DispatcherServlet

#### 컨테이너 인프라스트럭쳐 빈
- 스프링 컨테이너 자신이거나, 스프링이 확장하면서 추가해온 것들
- ApplicationContext/BeanFactory, Environment, 빈후처리기, DefaultAdvisorAutoProxyCreator