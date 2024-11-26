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

此项目为 NiceFish 的前端界面提供服务，以下两个版本的前端都已经对好接口：

- 基于 Angular 框架的前端界面：http://git.oschina.net/mumu-osc/NiceFish/
- 基于 React 框架的前端界面：https://gitee.com/mumu-osc/NiceFish-React

推荐使用 React 版本，因为最近几个月这个版本向前改了很多。

**注意：本项目与具体的前端框架无关，所有接口都是 Restful 的，可以使用任意前端框架来接入。**

**关于 Apache Shiro 权限控制，这里有 14 篇文章进行了详细的解释， https://cloud.tencent.com/developer/user/8593014 。**

## 2.开发环境

| 名称                  | 版本号                 | 描述                                              |
| --------------------- | ---------------------- | ------------------------------------------------- |
| Spring Boot           | 3.2                    | 发布于 2023 年 7 月                               |
| JDK                   | OpenJDK 20             | Spring Boot 3.2 要求至少 Java 17，不再支持 Java 8 |
| IDEA                  | 最新版本               | 无                                                |
| Maven                 | 3.8                    | Spring Boot 3.2 要求 Maven >=3.6                  |
| MySQL                 | >=5（or MariaDB >=10） | 其它版本没有测试兼容性                            |
| ElasticSearch（可选） | 8.9                    | 8.9 是当前最新版，2023-08，其它版本没有测试兼容性 |

**目前 Spring 各个模块的版本兼容性比较复杂，如果能成功启动，最好不要修改版本。**

## 3.用法

1. git clone https://gitee.com/mumu-osc/NiceFish-React.git
1. 用 IDEA 导入根目录下的 pom.xml
1. 在 MySQL 中执行 docs/nicefish-spring-boot.sql 脚本（包含测试数据)
1. 修改 application.yml 配置文件，把 MySQL 改成你自己的配置
1. 修改 application.yml 配置文件，把上传文件的目录 uploadPath 改成你本地的目录
1. 配置 Maven ，使用阿里云的源
1. 启动 nicefish-cms 模块下的主类 NiceFishCMSApplication.java
1. druid 监控地址：http://localhost:8080/druid/sql.html

[可选]：启用 ElasticSearch 搜索服务

1. 按照 ElasticSearch 官方文档安装配置 ES: https://www.elastic.co/guide/en/elasticsearch/reference/current/install-elasticsearch.html
1. 按照 ElasticSearch 官方文档安装配置 logstash: https://www.elastic.co/guide/en/logstash/8.9/installing-logstash.html
1. 按照官方文档完成 logstash 与 ElasticSearch 的对接
1. 参考本项目中的 /docs/elastic-mysql-jdbc.conf 配置文件配置 logstash 并重启服务，注意修改其中的 IP 地址、端口号、用户名和密码
1. 修改 nicefish-elastic-search 模块下的 application.yml 配置文件，把 ElasticSearch 的 IP 地址和端口号改成你自己的
1. 启动 nicefish-elastic-search 模块下的 NiceFishSearchApplication.java
1. 打开浏览器，访问 http://localhost:8899/nicefish/search/user/get-all ，测试搜索服务是否正常，注意改成你自己本地的配置
1. 在前端项目中设置代理，把带有 /search 前缀的服务全部代理给 nicefish-elastic-search 的服务

## 4.项目模块依赖关系

<img src="https://gitee.com/mumu-osc/nicefish-spring-boot/raw/master/docs/imgs/maven-modules.png">

1. nicefish-core: 提供通用的工具
1. nicefish-shiro-rbac: 提供基于 Shiro 的通用认证和鉴权服务，RBAC 型。
1. nicefish-cms: 基于以上基础模块的 CMS 应用
1. nicefish-elastic-search: 提供基于 ElasticSearch 的搜索服务（可选，不是必须启动）

## 5.物理模型

<img src="https://gitee.com/mumu-osc/nicefish-spring-boot/raw/master/docs/imgs/pdm.png">

物理模型 pdm 文件在 docs 目录下，可以使用 PowerDesigner 打开，目前针对 MySQL 数据库。

## 6.系列项目

<h4>NiceFish 的客户端项目：</h4>

| 名称 | 描述 | Stars |
| --- | --- | --- |
| [NiceFish（美人鱼）](http://git.oschina.net/mumu-osc/NiceFish/) | 这是 Angular 版本的前端界面，基于最新的 Angular 版本，使用 PrimeNG 组件库。 | <a href='https://gitee.com/mumu-osc/NiceFish/stargazers'><img src='https://gitee.com/mumu-osc/NiceFish/badge/star.svg?theme=gvp' alt='star'></img></a> |
| [NiceFish-React](https://gitee.com/mumu-osc/NiceFish-React) | 这是 React 版本的前端界面，基于 React 18.0.0 ，使用 PrimeReact， 定制版 Bootstrap 开发。纯 JSX ，没有使用 TypeScript 。 | <a href='https://gitee.com/mumu-osc/NiceFish-React/stargazers'><img src='https://gitee.com/mumu-osc/NiceFish-React/badge/star.svg?theme=dark' alt='star'></img></a> |
| [nicefish-ionic](http://git.oschina.net/mumu-osc/nicefish-ionic) | 这是一个移动端的 demo，基于 ionic，此项目已支持 PWA。 | <a href='https://gitee.com/mumu-osc/nicefish-ionic/stargazers'><img src='https://gitee.com/mumu-osc/nicefish-ionic/badge/star.svg?theme=dark' alt='star'></img></a> |
| [NiceBlogElectron](https://gitee.com/mumu-osc/NiceBlogElectron) | 这是一个基于 Electron 的桌面端项目，把 NiceFish 用 Electron 打包成了一个桌面端运行的程序。这是由 ZTE 中兴通讯的前端道友提供的，我 fork 了一个，有几个 node 模块的版本号老要改，如果您正在研究如何利用 Electron 开发桌面端应用，请参考这个项目。 | <a href='https://gitee.com/mumu-osc/NiceBlogElectron/stargazers'><img src='https://gitee.com/mumu-osc/NiceBlogElectron/badge/star.svg?theme=dark' alt='star'></img></a> |

<h4>NiceFish 的服务端项目：</h4>

| 名称 | 描述 | Stars |
| --- | --- | --- |
| [nicefish-spring-boot](https://gitee.com/mumu-osc/nicefish-spring-boot) | 用来示范前后端分离模式下，前端代码与后端服务的对接方式，已经完成了基线版本。以此为基础，你可以继续开发出适合自己业务场景的代码。 | <a href='https://gitee.com/mumu-osc/nicefish-spring-boot/stargazers'><img src='https://gitee.com/mumu-osc/nicefish-spring-boot/badge/star.svg?theme=dark' alt='star'></img></a> |
| [nicefish-spring-cloud](https://gitee.com/mumu-osc/nicefish-spring-cloud) | 用来示范前后端分离模式下，前端代码与分布式后端服务的对接方式。 | <a href='https://gitee.com/mumu-osc/nicefish-spring-cloud/stargazers'><img src='https://gitee.com/mumu-osc/nicefish-spring-cloud/badge/star.svg?theme=dark' alt='star'></img></a> |

## 7.前端界面截图

<img src="https://gitee.com/mumu-osc/NiceFish-React/raw/master/src/assets/images/1.png">

<img src="https://gitee.com/mumu-osc/NiceFish-React/raw/master/src/assets/images/2.png">

<img src="https://gitee.com/mumu-osc/NiceFish-React/raw/master/src/assets/images/3.png">

<img src="https://gitee.com/mumu-osc/NiceFish-React/raw/master/src/assets/images/4.png">

<img src="https://gitee.com/mumu-osc/NiceFish-React/raw/master/src/assets/images/5.png">

<img src="https://gitee.com/mumu-osc/NiceFish-React/raw/master/src/assets/images/6.png">

<img src="https://gitee.com/mumu-osc/NiceFish-React/raw/master/src/assets/images/7.png">

<img src="https://gitee.com/mumu-osc/NiceFish-React/raw/master/src/assets/images/8.png">

<img src="https://gitee.com/mumu-osc/NiceFish-React/raw/master/src/assets/images/9.png">

<img src="https://gitee.com/mumu-osc/NiceFish-React/raw/master/src/assets/images/11.png">

<img src="https://gitee.com/mumu-osc/NiceFish-React/raw/master/src/assets/images/12.png">

## 8.开源许可证

MIT

（补充声明：您可以随意使用此项目，但是本人不对您使用此项目造成的任何损失承担责任。）

**（此项目的作者正在寻找一份新的工作，如果有好的机会，请联系我的 VX： lanxinshuma）**
