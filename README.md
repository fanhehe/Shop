# Shop

## 说明

商店系统，包括：

	- backend: 商店页面后端接入点
	- frontend: 商店页面前端站点
	- order: 订单服务
	- point: 积分服务
	- storage: 库存服务
	- user: 用户信息服务
	- message: 消息服务

## 要求

- Java 8

## 使用方式

区分环境的两个运行命令：

`mvn spring-boot:run -Dspring-boot.run.profiles=dev`

`mvn spring-boot:run -Dspring-boot.run.profiles=prod`

## Change Logs

- 1.0.0 init
