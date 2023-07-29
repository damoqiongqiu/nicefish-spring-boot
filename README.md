<p align="center">
    <img width="150" src="https://gitee.com/mumu-osc/nicefish-spring-boot/raw/master/docs/imgs/nice-fish.png">
</p>

<h1 align="center">NiceFish</h1>

<p align="left">
NiceFish（美人鱼） 是一个系列项目，目标是示范前后端分离的开发+部署模式。前端有3个版本：浏览器环境、移动端环境、Electron 环境；后端有2个版本：SpringBoot 版本和 SpringCloud 版本。
</p>

<p>
🚀🚀🚀请不要吝惜你的⭐️ Star ⭐️，星星越多，动力越足。🚀🚀🚀
</p>

## 1.简述

此项目主要基于 spring-boot 和 Apache Shiro，为 NiceFish 的前端界面提供服务，对应的前端代码在这里： http://git.oschina.net/mumu-osc/NiceFish/ 。

**注意：本项目与具体的前端框架无关，所有接口都是 Restful 的，可以使用任意前端框架来接入。**

## 2.开发环境

-   JDK 1.8
-   开发工具 IDEA
-   构建工具 Maven
-   MySQL >=5 or MariaDB >=10

## 3.用法

-   git clone 本项目
-   用 IDEA 导入根目录下的 pom.xml
-   在 MySQL 中执行 docs/nicefish-spring-boot.sql 脚本（包含测试数据)
-   修改 application.yml 配置文件，把 MySQL 改成你自己的配置
-   修改 application.yml 配置文件，把上传文件的目录 uploadPath 改成你本地的目录
-   配置 Maven ，使用阿里云的源
-   启动 nicefish-cms 模块下的 NiceFishApplication.java
-   Swagger 接口文档地址：http://localhost:8080/swagger-ui.html
-   druid 监控地址：http://localhost:8080/druid/sql.html

## 4.项目模块依赖关系

<img src="https://gitee.com/mumu-osc/nicefish-spring-boot/raw/master/docs/imgs/maven-modules.png">

-   nicefish-core: 提供通用的工具
-   nicefish-shiro-rbac: 提供基于 Shiro 的通用认证和鉴权服务，RBAC 型。
-   nicefish-cms: 基于以上基础模块的 CMS 应用

## 5.Swagger 接口

<img src="https://gitee.com/mumu-osc/nicefish-spring-boot/raw/master/docs/imgs/swagger.png">

-   项目起来之后访问 http://localhost:8080/swagger-ui.html

## 6.物理模型

<img src="https://gitee.com/mumu-osc/nicefish-spring-boot/raw/master/docs/imgs/pdm.png">

物理模型 pdm 文件在 docs 目录下，可以使用 PowerDesigner 打开，目前针对 MySQL 数据库。

## 7.系列项目

<h4>NiceFish 的客户端项目：</h4>

| 名称                                                             | 描述                                                                                                                                                                                                                                            | Stars                                                                                                                                                                   |
| ---------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| [NiceFish（美人鱼）](http://git.oschina.net/mumu-osc/NiceFish/)  | 这是 Angular 版本的前端界面，基于最新的 Angular 版本，使用 PrimeNG 组件库。                                                                                                                                                                     | <a href='https://gitee.com/mumu-osc/NiceFish/stargazers'><img src='https://gitee.com/mumu-osc/NiceFish/badge/star.svg?theme=gvp' alt='star'></img></a>                  |
| [NiceFish-React](https://gitee.com/mumu-osc/NiceFish-React)      | 这是 React 版本的前端界面，基于 React 18.0.0 ，使用 PrimeReact， 定制版 Bootstrap 开发。纯 JSX ，没有使用 TypeScript 。                                                                                                                         | <a href='https://gitee.com/mumu-osc/NiceFish-React/stargazers'><img src='https://gitee.com/mumu-osc/NiceFish-React/badge/star.svg?theme=dark' alt='star'></img></a>     |
| [nicefish-ionic](http://git.oschina.net/mumu-osc/nicefish-ionic) | 这是一个移动端的 demo，基于 ionic，此项目已支持 PWA。                                                                                                                                                                                           | <a href='https://gitee.com/mumu-osc/nicefish-ionic/stargazers'><img src='https://gitee.com/mumu-osc/nicefish-ionic/badge/star.svg?theme=dark' alt='star'></img></a>     |
| [NiceBlogElectron](https://gitee.com/mumu-osc/NiceBlogElectron)  | 这是一个基于 Electron 的桌面端项目，把 NiceFish 用 Electron 打包成了一个桌面端运行的程序。这是由 ZTE 中兴通讯的前端道友提供的，我 fork 了一个，有几个 node 模块的版本号老要改，如果您正在研究如何利用 Electron 开发桌面端应用，请参考这个项目。 | <a href='https://gitee.com/mumu-osc/NiceBlogElectron/stargazers'><img src='https://gitee.com/mumu-osc/NiceBlogElectron/badge/star.svg?theme=dark' alt='star'></img></a> |

<h4>NiceFish 的服务端项目：</h4>

| 名称                                                                      | 描述                                                                                                                             | Stars                                                                                                                                                                             |
| ------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| [nicefish-spring-boot](https://gitee.com/mumu-osc/nicefish-spring-boot)   | 用来示范前后端分离模式下，前端代码与后端服务的对接方式，已经完成了基线版本。以此为基础，你可以继续开发出适合自己业务场景的代码。 | <a href='https://gitee.com/mumu-osc/nicefish-spring-boot/stargazers'><img src='https://gitee.com/mumu-osc/nicefish-spring-boot/badge/star.svg?theme=dark' alt='star'></img></a>   |
| [nicefish-spring-cloud](https://gitee.com/mumu-osc/nicefish-spring-cloud) | 用来示范前后端分离模式下，前端代码与分布式后端服务的对接方式。                                                                   | <a href='https://gitee.com/mumu-osc/nicefish-spring-cloud/stargazers'><img src='https://gitee.com/mumu-osc/nicefish-spring-cloud/badge/star.svg?theme=dark' alt='star'></img></a> |

## 8.前端界面截图

<img src="https://gitee.com/mumu-osc/NiceFish/raw/master/src/assets/imgs/1.png">

<img src="https://gitee.com/mumu-osc/NiceFish/raw/master/src/assets/imgs/2.png">

<img src="https://gitee.com/mumu-osc/NiceFish/raw/master/src/assets/imgs/3.png">

<img src="https://gitee.com/mumu-osc/NiceFish/raw/master/src/assets/imgs/4.png">

<img src="https://gitee.com/mumu-osc/NiceFish/raw/master/src/assets/imgs/5.png">

<img src="https://gitee.com/mumu-osc/NiceFish/raw/master/src/assets/imgs/6.png">

<img src="https://gitee.com/mumu-osc/NiceFish/raw/master/src/assets/imgs/7.png">

## 9.开源许可证

MIT

（补充声明：您可以随意使用此项目，但是本人不对您使用此项目造成的任何损失承担责任。）
