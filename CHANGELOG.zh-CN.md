# Changelog

这个项目的所有显著变化都将被记录在这个文件中。

# [0.20.0] - 2019-12-16

## 后端

### 新增

- 新增webhook项目层配置功能
- 新增webhook平台层日志功能
- 新增项目层通知设置
- 新增初始化devops流水线通知、资源删除以及敏捷项问题通知设置

### 修改

- 重构表结构及消息设计，更新相应接口及代码逻辑
- 修改webhook的DingTalk/WeChat发送方式

# [0.19.0] - 2019-09-30

## 后端

### 新增

-  短信服务功能迁移到通知服务
- `bootstrap.yml` 添加健康检查详情显示
- `values.yaml` 添加初始化配置策略参数

### 修改

- 模糊查询列表接口统一改为 GET 请求
- 更新 Websocket 的使用，Websocket 接口忽略 JWT 检查
- 更新feign调用 `iam-service` 为 `base-service`
- 升级`choerodon-starter`依赖版本为`0.12.0.RELEASE`
- 更新跨服务初始化目录名 `/script/front` 为 `/script/meta`
- 升级`choerodon-framework-parent`依赖版本为`0.13.0.RELEASE`
### 修复

- 修复消息模板编码一致时，freemarker 模板替换问题

### 移除

- 迁移 Websocket 核心代码到`choerodon-starter`

# [0.18.0] -2019-06-21

## 后端

### 新增

- job添加hook删除策略

### 修改

- 调整菜单顺序

# [0.17.0] - 2019-05-24

## 前端

### 新增

- 增加notify相关的前端页面

### 修改

- 重构发送设置

## 后端

### 新增

- 新增短信通知方式
- 发送消息优先根据设置的自定义发送类型发送
- 初始化菜单使用excel通过sagaTask初始化
- 0.16.0升级到0.17.0，旧的菜单数据迁移修复

### 修改

- choerodon-starter-mybatis-mapper更换为choerodon-starter-mybatis依赖
- 修改choerodon-framework-parent依赖
- 修改gitlab.ci打包逻辑，支持前后端一起打包
- 修改dbtool版本为0.6.4

### 修复

- 修复消息中心-发送记录标题错误

# [0.16.0] - 2019-04-19

### 更新

- 修改base镜像为0.8.0

# [0.15.0] - 2019-03-19

### 更新

- 升级`spring boot`版本为`2.0.6`。
- 升级`spring cloud`版本为`Finchley.SR2`。

### 修改

- 修改ci文件

# [0.14.0] - 2019-02-19

### 新增

- 新增取消订阅功能
- 新增系统公告的悬浮功能相关接口

### 修复

- 修复移除webSocket关联异常

# [0.13.0] - 2019-01-08

### 新增

- 新增系统公告功能
- 新增在线人数统计

### 修改

- 升级`choerodon-starter`依赖更新至`0.9.0`

### 移除

- 去除undertow依赖

# [0.12.0] - 2018-12-12

### 新增

- 新增系统公告发送设置
- 新增事务实例失败模板
- 添加系统公告

### 修改

- 升级`choerodon-starter`依赖更新至`0.8.1`

### 移除

- 移除`zipkin`依赖及相关配置
- 移除`hystrix-stream`依赖
- 移除`kafka`依赖及相关配置

# [0.11.0] - 2018-11-13

### 新增

- 添加了redis的数据库设置
- 添加单元测试
- 站内信发送设置支持消息和通知两种格式
- 添加发送自定义消息到webSocket接口

### 修改
- `choerodon-starter`依赖更新至`0.7.0`
- 从kafka监听服务上下线的方式修改为`choerodon-starter-eureka-event`
- 更新基础镜像

# [0.10.0] - 2018-09-27

### 新增

- 添加发送邮件功能
- 增加站内信功能
- 支持oracle数据库
- 添加websocketgon功能
- 添加发送站内信定时任务

### 修改

- 更新license 
- 修改了ci文件
- 更新了基础镜像

# [0.9.0] - 2018-07-04
