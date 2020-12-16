# SpringBoot RESTFul Api接口的示例工程
编写该工程，采用目前流行的SpringBoot框架，进行开发的练习，并根据该项目进行单元测试、接口测试的编写，并集成Jacoco、SonarQube、Jenkins，进行代码覆盖的分析，代码规范，CI、CD的流程搭建。

[官方网站](https://spring.io/projects/spring-boot/)

## Spring Boot特性
- 使用Spring项目引导页面可以在几秒构建一个项目；
- 方便对外输出各种形式的服务，如REST API、WebSocket、Web、Streaming、Tasks；
- 非常简洁的安全策略集成；
- 支持关系数据库和非关系数据库；
- 支持运行期内嵌容器，如Tomcat、Jetty；
- 强大的开发包，支持热启动；
- 自动管理依赖；
- 自带应用监控；
- 支持各种IDE，如IntelliJ IDEA、NetBeans。

## 为什么学习Spring Boot？
Spring Boot本身并不提供Spring 框架的核心特性以及扩展功能，只是用于快速、敏捷地开发新一代基于Spring框架的应用程序。同时它集成了大量常用的第三方库配置（如 Redis、MongoDB、JPA、RabbitMQ、Quartz 等），Spring Boot应用中这些第三方库几乎可以零配置进行开箱即用，大部分的Spring Boot应用都只需要非常少量的配置代码，开发者能够更加专注于业务逻辑。

使用Spring Boot开发项目，有以下几方面优势：
- Spring Boot使开发变得简单，提供了丰富的解决方案，快速集成各种解决方案提升开发效率。
- Spring Boot使配置变得简单，提供了丰富的Starters，集成主流开源产品往往只需要简单的配置即可。
- Spring Boot使部署变得简单，其本身内嵌启动容器，仅仅需要一个命令即可启动项目，结合 Jenkins、Docker自动化运维非常容易实现。
- Spring Boot使监控变得简单，自带监控组件，使用Actuator轻松监控服务各项状态。

从软件发展的角度来讲，越简单的开发模式越流行，简单的开发模式解放出更多生产力，让开发人员可以避免将精力耗费在各种配置、语法所设置的门槛上，从而更专注于业务。这点上，Spring Boot已尽可能地简化了应用开发的门槛。

Spring Boot整体的设计思想是：**约定优于配置**。依赖此设计思路，Spring Boot进行了大刀阔斧的改革，让开发、测试、部署更为便捷。众多的Starters成就了Spring Boot的发展，让使用Spring Boot开发项目变得更加简单。

# RESTFUL
RESTful是目前最流行的一种互联网软件架构。REST（Representational State Transfer，表述性状态转移）一词是由Roy Thomas Fielding在他2000年博士论文中提出的，定义了他对互联网软件的架构原则，如果一个架构符合REST原则，则称它为RESTful架构。

RESTful架构一个核心概念是“资源”（Resource）。从 RESTful的角度看，网络里的任何东西都是资源，它可以是一段文本、一张图片、一首歌曲、一种服务等，每个资源都对应一个特定的 URI（统一资源定位符），并用它进行标示，访问这个 URI 就可以获得这个资源。

资源可以有多种表现形式，也就是资源的“表述”（Representation），比如一张图片可以使用 JPEG 格式也可以使用 PNG 格式。URI 只是代表了资源的实体，并不能代表它的表现形式。

互联网中，客户端和服务端之间的互动传递的就只是资源的表述，我们上网的过程，就是调用资源的 URI，获取它不同表现形式的过程。这种互动只能使用无状态协议 HTTP，也就是说，服务端必须保存所有的状态，客户端可以使用 HTTP 的几个基本操作，包括 **GET（获取）、POST（创建）、PUT（更新）与 DELETE（删除）**，使得服务端上的资源发生“状态转化”（State Transfer），也就是所谓的“表述性状态转移”。

# Spring Boot 对 RESTful 的支持
Spring Boot全面支持开发RESTful程序，通过不同的注解来支持前端的请求，除了经常使用的注解外，Spring Boot还提了一些组合注解。这些注解来帮助简化常用的HTTP方法的映射，并更好地表达被注解方法的语义。
- @GetMapping，处理 Get 请求
- @PostMapping，处理 Post 请求
- @PutMapping，用于更新资源
- @DeleteMapping，处理删除请求
- @PatchMapping，用于更新部分资源

其实这些组合注解就是我们使用的 @RequestMapping 的简写版本，下面是 Java 类中的使用示例：
```
@GetMapping(value="/xxx")
等价于
@RequestMapping(value = "/xxx",method = RequestMethod.GET)

@PostMapping(value="/xxx")
等价于
@RequestMapping(value = "/xxx",method = RequestMethod.POST)

@PutMapping(value="/xxx")
等价于
@RequestMapping(value = "/xxx",method = RequestMethod.PUT)

@DeleteMapping(value="/xxx")
等价于
@RequestMapping(value = "/xxx",method = RequestMethod.DELETE)

@PatchMapping(value="/xxx")
等价于
@RequestMapping(value = "/xxx",method = RequestMethod.PATCH)
```
通过以上可以看出 RESTful 在请求的类型中就指定了对资源的操控。

# SpringBoot开发示例

## 接口的说明
按照RESTful的思想我们来设计一组对用户操作的RESTful API：

请求 | 地址 | 说明
---|--- | ---
GET | /user | 查询所有用户信息
GET | /user/{userId} | 查询指定的用户信息
GET | /user/{userId}/friend/{friendId} | 查询指定用户的好友信息
POST | /user | 保存用户信息
PUT | /user/{userId} | 更新用户信息
DELETE | /user/{userId} | 删除用户信息

put方法主要是用来更新整个资源的，而patch方法主要表示更新部分字段。

## API接口统一JSON格式返回
1. 定义API接口返回的JSON格式
    ```json
       {
           "code": "00000",
           "message": "ok",
           "data": {}
       }
    ```
    - code = '00000' 表示业务正常
    - message 业务描述信息
    - data 业务数据，当没有业务数据时，data不返回
           
2. 定义API接口状态码枚举值
    - 定义的类ApiCodeEnum：
3. 定义API返回体类
    - 定义的类ApiResponse:

简单实现了统一JSON格式，在编写Controller接口的时候返回ApiResponse<Object>即可

## JWT身份验证
JWT全称是：Json Web Token，是一种基于JSON的开发标准，为了再网络应用环境间相互传递，广泛应用于身份验证。JWT设计紧凑而安全，特别适合网站的单点登录场景。

## 业务代码开发

## SpringBoot基于Junit5单元测试
目前MVC分层的结构进行测试，在编写单元测试时，需要对每层进行测试

Spring Boot 2.2.0 版本开始引入JUnit 5作为单元测试默认库，在Spring Boot 2.2.0版本之前，`spring-boot-starter-test`包含了JUnit 4的依赖，Spring Boot 2.2.0 版本之后替换成了Junit Jupiter。

RunWith配置
- Junit4：`@RunWith(SpringRunner.class)`
- JUnit5：`@ExtendWith(SpringExtension.class)`

@Before、@BeforeClass、@After、@AfterClass 被替换
- @BeforeEach替换@Before
- @BeforeAll替换@BeforeClass
- @AfterEach替换@After
- @AfterAll替换@AfterClass

### Dao层的单元测试
Dao层是对于数据库进行操作，如果在测试时连接数据库，对于数据的操作会有影响，因此可引入H2内存数据库，进行业务数据库的屏蔽，由于本项目是简单的示例，不对数据库进行操作，该方式会在其他练习工程中演示.

### SpringMockMVC进行Controller单元测试
在Spring Boot体系中，Spring给出了一个简单的解决方案，使用MockMVC进行Web测试，MockMVC内置了很多工具类和方法，可以模拟post、get请求，并且判断返回的结果是否正确等，也可以利用print()打印执行结果。


## SpringBoot集成SwaggerUI
### knife4j的使用
[knife4j官方网站](https://doc.xiaominfo.com/guide/useful.html#java开发)

启动服务，访问地址：http://ip:port/doc.html

## 使用TraceId实现日志跟踪(全流程的日志跟踪)
1. 建立一个过滤器，在过滤器中给线程设置TraceId
2. 将日志配置文件进行修改，把TraceId打印到日志中