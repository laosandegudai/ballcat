# Changelog

更新日志

## [Unreleased]

- 全局数据校验支持

- OSS starter 修改使用 AWS S3

  

## [0.0.6]

### Warning

- 更新了 UserDetail，缓存反序列化将会出现问题，更新版本前需要清除对应缓存
- 返回体结构修改，属性 msg 修改为 message，可能影响前端信息展示，接入系统的第三方的响应数据接收，升级前需提前沟通

### Added

- feat: 新增组织机构(部门)，用户与组织机构为一对一的关系
- feat: 数据权限，利用 mybatis 拦截器对 sql 进行拦截约束，约束规则支持自定义，适用于大部分数据权限控制。
- feat: 角色新增 scopeType，暂时支持全部，本人，本部门，本人及子部门等几种范围类型

### Changed

- fix: 修复没有提供默认 profile，导致用户不指定 profile 时，全局异常处理无法正常初始化的问题

- refactor: lovBody 和 lovSearch 关联属性由 lovId 更改为  keyword

- refactor: UserDetails 属性重构，抽象出用户资源(userResources)属性，默认将用户的角色和权限作为资源存入userResources，并提供 UserResourceCoordinator 用户资源协调者，方便根据业务扩展用户资源（便于数据权限管理）

- refactor: kafka 消费者配置提供

- refactor: 返回体结构修改，属性 msg 修改为 message

- refactor: xss 和 monitor auth 过滤器提供开关，并调整了配置前缀

- refactor: 用户角色，角色权限的关联，由 role_id 修改为使用 role_code

  

## [0.0.5]

### Added

- Lov 模块
- 字典相关
  - feat: DictItemVO 新增 id 属性
  - feat: 字典项新增 attributes 属性，用于定制额外的非必须属性，如颜色等供前端使用

### Changed

- refactor: ApplicationContextUtil 更名为 SpringUtil

- refactor: LogUtil#isMultipart 去除只判断 POST 请求的限制

- 全局异常&异常通知

  - fix: 修复异常通知 message 为 null 时导致的异常

  - fix: 修复类校验失败时，无法正常返回错误信息的
  - feat: 异常通知添加 hostname 和 ip 信息
  - fix: 捕获空指针异常时，会导致异常通知空指针的问题
  - fix: 异常通知 cpu 占用过高问题
  - feat: 添加忽略指定异常类的配置
  - refactor: 优化钉钉通知的http请求方式
  - style: 通知信息中的英文冒号转为中文冒号
  - refactor: 除未知异常外，取消全局异常捕获时的异常打印，如需详细堆栈可以在异常处理类中进行处理

- feat: AbstractQueueThread#preProcessor 修改为 public，便于子类重写

- fix: 修复包装 RequestBody 导致，表单数据无法正常读取的bug

- fix: 修复在前台页面新建权限时无法指定主键 Id 的异常

- feat: extend-mybatis-plus 中批量插入方法，将生产的主键回填到实体中

- refactor: 登陆日志和操作日志分离

- fix: 修复用户登陆后将密文密码返回前台的安全隐患问题

- style: 代码生成器样式微调

### Dependency

- Bump spring-boot from 2.3.1 to 2.3.4
- Bump mybatis-plus from 3.3.2 to 3.4.0
- Bump hutool from 5.3.10 to 5.4.1
- Bump  spring-java-format from 0.0.22 to 0.0.25



## [0.0.4]

### Added

- 新增 kafka stater 模块
- 新增 mybatis-extends 扩展，添加批量插入方法
- accesslog 提供 responseWrapper，方便记录响应数据
- swagger stater 新增 additionalModelPackage 属性，用于扫描一些额外的 swaggerModel
- 异常捕获新增对 MethodArgumentTypeMismatchException 的处理
- 新增 Security 是否开启禁止 iframe 嵌入的配置控制

### Changed

- AbstractQueueThread 提高默认的批处理大小
- 代码生成器移除加载动态数据源时指定的 driverClassName
- 移除 admin-core 默认引入的 swagger 依赖，现在用户可以在自己的项目中选择引入
- 移除 JacksonConfig#ObjectMapper 的 @Primary 注解，便于用户自定义
- 数据字典优化，支持批量请求，接口地址调整
- 调整前后端传输密码使用的 AES Padding mode 为 PKCS5Padding
- 修复因 TokenStore 与 cachePropertiesHolder 加载顺序导致的启动异常
- 修复 codegen 无法选择 master 之外的数据源进行代码生成的bug
- 当使用 DingTalk 异常通知时，会 @所有人
- 字典添加值类型字段，便于前端回显，以及后续校验控制

### Dependency

- swagger up to 1.5.21
- dynamic-datasource up to 3.2.0
- spring-boot-admin up to 2.2.4
- easyexcel up to 2.2.6



## [0.0.3] - 2020-07-06

 ### Added

- 重构代码生成器

  - 前端使用 ant-design-vue 重构，支持单体应用以及前后端分离两种部署方式
  - 多数据源支持，动态添加删除，生成时选择对应数据源进行代码生成
  - 代码生成结构调整，支持自定义代码生成结构
  - 支持在线模板编辑
  - 支持自定义模板属性，定制模板

- 提供 dingTalk-starter，简化 dingTalk 接入

- 提供 kafka 扩展，以及kafka流式处理扩展包

- 记录登陆登出日志

- 自动填充（逻辑删除标识），新建时增加对字段名为 `deleted` 且 类型为`Long`的属性填充，填充值为 0

- 超级管理员配置，可根据userid 或 username 指定当前项目的超级管理员（即最多可配置两个超级管理员），超级管理员默认拥有所有权限，不可删除，仅自己可以修改自己的信息
  如下配置：指定了 userId 为 1 或者 username 为 admin 的用户为超级管理员

    ```yaml
    ballcat:
        admin:
            rule: 
                userId: 1  
                username: admin
    ```

### Changed

- 访问日志忽略路径修改为可配置

- 角色新增类型属性，对于系统类型角色，不允许删除

- 更新逻辑删除不能使用 unique key 的问题，逻辑删除使用时间戳，未删除为0，删除则为删除的时间戳，实体类字段同一使用Long，数据库使用bigint。  

  - 配置文件添加如下配置：

    ```yaml
    mybatis-plus:
      global-config:
        db-config:
          logic-delete-value: "NOW()" # 逻辑已删除值(使用当前时间标识)
          logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)
    ```

### Dependency

- mybatis-plus 版本升级至 3.3.2
- spring-boot 版本升级至 2.3.1.RELEASE
- spring-security-oauth2 升级至 2.3.8.RELEASE  





## [0.0.2] 

### Added

- 更新系统配置表，重命名为 sys_config, 并调整包位置，并入sys模块下。

- 模块重构，调整部分common下的模块，修改放入starter

- 合并 simple-cache，后续直接引入`ballcat-spring-boot-starter-redis`模块，即可开启全局key前缀，以及缓存注解功能

- traceId跟踪，引入`ballcat-spring-boot-starter-log`，会自动为每个请求的日志上下文中注入TraceId

- operation_log、admin_access_log表新增字段 trace_id，类型char(24).

- logback-spring.xml 彩色日志模板中，加入`%clr([%X{traceId}]){faint}`，文件日志模板加入`[%X{traceId}]`，用于打印traceId

- 移除api_access_log表，以及相关代码

- 更新日志，追加追踪ID，操作类型

- core项目更新为自动装配，可以删除项目Application中的
  `@ServletComponentScan("com.hccake.ballcat.admin.oauth.filter")` 注解。

  `@MapperScan("com.hccake.ballcat.**.mapper")`
  `@ComponentScan("com.hccake.ballcat.admin")`
  注解上的这两个包扫描也可去掉。