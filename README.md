<p align="center">
    <img width="150" src="./docs/imgs/nice-fish.png">
</p>

<h1 align="center">NiceFish</h1>

<div align="left">
NiceFish（美人鱼） 是一个系列项目，目标是示范前后端分离的开发+部署模式。前端有3个版本：浏览器环境、移动端环境、Electron 环境；后端有2个版本：SpringBoot 版本和 SpringCloud 版本。
</div>

## 1.简述
此项目主要基于 spring-boot 和 Apache Shiro，为 NiceFish 的前端界面提供服务，对应的前端代码在这里： http://git.oschina.net/mumu-osc/NiceFish/ 。

**注意：本项目与具体的前端框架无关，所有接口都是 Restful 的，可以使用任意前端框架来接入。**

## 2.开发环境

- JDK 1.8
- 开发工具 IDEA
- 构建工具 Maven
- MySQL >=5 or MariaDB >=10
- Redis(Windows安装配置请参考：https://github.com/tporadowski/redis/releases ， 其它平台请参考 Redis 官网：https://redis.io)

## 3.用法

- git clone 本项目
- 用 IDEA 导入根目录下的 pom.xml 
- 在 MySQL 中执行 docs/nicefish_springboot.sql 脚本
- 修改 application.yml 配置文件，把 MySQL 和 Redis 改成你本地的用户名和端口
- 配置 Maven ，使用阿里云的源
- 启动 nicefish-cms 模块下的 NiceFishApplication.java

## 4.项目模块依赖关系

<img src="./docs/imgs/maven-modules.png">

- nicefish-core: 提供通用的工具
- nicefish-auth-shiro: 提供基于Shiro的通用认证和鉴权服务
- nicefish-staff-org: 提供经典的树形组织机构和员工管理
- nicefish-cms: 基于以上基础模块的内容管理应用

## 5.Swagger 接口

<img src="./docs/imgs/swagger.png">

- 项目起来之后访问 http://localhost:8080/swagger-ui.html

## 6.系列项目

|  名称   | 描述  |
|  ----  | ----  |
| NiceFish（美人鱼）  | 这是一个系列项目，目标是示范前后端分离的开发模式:前端浏览器、移动端、Electron 环境中的各种开发模式。后端有两个版本：SpringBoot 版本和 SpringCloud 版本，http://git.oschina.net/mumu-osc/NiceFish/ |
| nicefish-ionic  | 这是一个移动端的 demo，基于 ionic，此项目已支持 PWA。http://git.oschina.net/mumu-osc/nicefish-ionic |
| NiceBlogElectron  | 这是一个基于 Electron 的桌面端项目，把 NiceFish 用 Electron 打包成了一个桌面端运行的程序。这是由 ZTE 中兴通讯的前端道友提供的，我 fork 了一个，有几个 node 模块的版本号老要改，如果您正在研究如何利用 Electron 开发桌面端应用，请参考这个项目，https://github.com/damoqiongqiu/NiceBlogElectron|
| OpenWMS  | 用来示范管理后台型系统的最佳实践，https://gitee.com/mumu-osc/OpenWMS-Frontend|
| nicefish-springboot  | 用来示范前后端分离模式下，前端代码与后端服务的对接方式，已经完成了基线版本，并且在腾讯云上面做了实际的部署。代码仓库在这里： https://gitee.com/mumu-osc/nicefish-spring-boot ，以此为基础，你可以继续开发出适合自己业务场景的代码。|
| nicefish-springcloud  | 用来示范前后端分离模式下，前端代码与分布式后端服务的对接方式， https://gitee.com/mumu-osc/nicefish-spring-boot。|
| NiceFish-React  |  这是React 版本，基于React 18.0.0 ，使用 Antd、Inversify、 定制版 Bootstrap开发。  https://gitee.com/mumu-osc/NiceFish-React.git|

## 7.前端界面截图

<img src="./docs/imgs/1.png">

<img src="./docs/imgs/2.png">

<img src="./docs/imgs/3.png">

<img src="./docs/imgs/4.png">

## 8.开源许可证

MIT

（补充声明：您可以随意使用此项目，但是本人不对您使用此项目造成的任何损失承担责任。）