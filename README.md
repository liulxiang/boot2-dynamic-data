# Spring Boot 多数据源脚手架

基于Spring Boot 2.7.18，集成MyBatis-Plus、Druid、Dynamic-Datasource实现的多数据源脚手架项目。

## 技术栈

- Spring Boot 2.7.18
- MyBatis-Plus 3.5.3.1
- Druid 1.2.15
- Dynamic-Datasource 3.5.1
- MySQL

## 功能特性

1. 支持多数据源切换
2. 集成Druid数据库连接池和监控
3. 使用MyBatis-Plus简化数据库操作
4. 提供RESTful API示例
5. 支持多数据源开关控制

## 数据源配置

- **master**: 主数据源，用于写操作
- **slave**: 从数据源，用于读操作

## 多数据源开关

在`application.yml`中可以通过以下配置控制是否启用多数据源：

```yaml
spring:
  datasource:
    dynamic:
      # 是否启用多数据源，默认启用
      enabled: true
```

当设置为`false`时，将禁用多数据源功能，所有操作都使用默认数据源。

## 快速开始

1. 克隆项目
2. 修改`application.yml`中的数据库连接配置
3. 执行`schema.sql`创建数据库表
4. 运行项目：`mvn spring-boot:run`

## API接口

- `POST /user/add` - 添加用户（启用多数据源时使用master数据源）
- `GET /user/get/{id}` - 获取用户（启用多数据源时使用slave数据源）
- `GET /user/list` - 获取所有用户（启用多数据源时使用slave数据源）

## Druid监控

访问地址：http://localhost:8080/druid/
默认账号密码：admin/admin

## 数据源切换注解

- `@DS("master")` - 使用主数据源
- `@DS("slave")` - 使用从数据源