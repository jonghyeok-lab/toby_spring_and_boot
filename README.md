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

#### ApplicationContextAware
- ApplicationContextAware를 구현하면 이 인터페이스에 있는 setter를 사용해서
컨테이너가 빈을 초기화 하면서 빈 오브젝트를 주입

#### ImportSelector
- 동적으로 Configuration을 Import 가능하게 해준다.
  - 동적의 예시는 yml 이나 DB에서 값을 읽어와서 로딩이 가능.
- ImportSelector의 역할은 문자열 배열로 리턴한 이름의 클래스들을 스프링 컨테이너의 빈으로 등록하게 해준다.
- Enable~~ 은 구성 정보 관련 클래스들을 Import(등록)

- 동적으로 구성 정보를 사용할 클래스 파일을 외부에서 읽어오는 전체적인 그림
![img.png](img.png)

#### Configuration과 proxyBeanMethods

#### `@Conditional`
![img_1.png](img_1.png)

#### Conditional
- @ConditionalOnProperty -> yml의 프로퍼티가 존재하고 값이 false가 아니면 포함 대상.
- @ConditionalOnResources -> 지정된 리소스(파일)의 존재를 확인하는 조건
- Web Application Conditions: 웹 애플리케이션 여부를 확인, 모든 부트 프로젝트가 웹 기술을 사용하는 것은 아님(배치)
  - @ConditionalOnWebApplication
  - @ConditionalOnNotWebApplication
- @ConditionalOnExpression - 스프링 SpEL(스프링 표현식)의 처리 결과를 기준으로 판단, 매우 상세한 조건설정이 가능.

#### Spring JDBC 자동 구성
![img_2.png](img_2.png)
