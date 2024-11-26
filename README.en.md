<p align="center">
    <img width="150" src="https://gitee.com/mumu-osc/nicefish-spring-boot/raw/master/docs/imgs/nice-fish.png">
</p>

<h1 align="center">NiceFish</h1>

<p align="left">
NiceFish is a series of projects aimed at demonstrating the development and deployment of front-end and back-end separation. There are three versions of the front-end: browser environment, mobile environment, and Electron environment; and two versions of the back-end: SpringBoot version and SpringCloud version.
</p>

<p>
🚀🚀🚀Please don't hesitate to ⭐️ Star ⭐️ this project. The more stars, the more motivation. 🚀🚀🚀
</p>

## 1. Introduction

This project provides services for the front-end interface of NiceFish. The front-end of the following two versions has been configured for the interfaces:

- Front-end based on the Angular framework: http://git.oschina.net/mumu-osc/NiceFish/
- Front-end based on the React framework: https://gitee.com/mumu-osc/NiceFish-React

It is recommended to use the React version as it has undergone significant improvements in the past few months.

**Note: This project is framework-agnostic. All interfaces are RESTful, and you can use any front-end framework to integrate with them.**

## 2. Development Environment

| Name | Version | Description |
| --- | --- | --- |
| Spring Boot | 3.2 | Released in July 2023 |
| JDK | OpenJDK 20 | Spring Boot 3.2 requires at least Java 17, no longer supports Java 8 |
| IDEA | Latest version | None |
| Maven | 3.8 | Spring Boot 3.2 requires Maven >= 3.6 |
| MySQL | >=5 (or MariaDB >=10) | Other versions have not been tested for compatibility |
| ElasticSearch (optional) | 8.9 | 8.9 is the latest version as of August 2023, other versions not tested for compatibility |

**Note: The compatibility of various Spring modules is complex. If you manage to start successfully, it's better not to modify the versions.**

## 3. Usage

1. git clone https://gitee.com/mumu-osc/NiceFish-React.git
2. Import the root pom.xml using IDEA.
3. Execute the docs/nicefish-spring-boot.sql script in MySQL (includes test data).
4. Modify the application.yml configuration file to match your MySQL configuration.
5. Modify the application.yml configuration file to set the upload directory `uploadPath` to your local directory.
6. Configure Maven to use the Aliyun repository.
7. Start the main class NiceFishCMSApplication.java in the nicefish-cms module.
8. Druid monitoring URL: http://localhost:8080/druid/sql.html

[Optional]: Enable ElasticSearch search service

1. Install and configure ES according to the ElasticSearch official documentation: https://www.elastic.co/guide/en/elasticsearch/reference/current/install-elasticsearch.html
2. Install and configure logstash according to the ElasticSearch official documentation: https://www.elastic.co/guide/en/logstash/8.9/installing-logstash.html
3. Complete the integration of logstash and ElasticSearch as per the official documentation.
4. Configure logstash using the /docs/elastic-mysql-jdbc.conf configuration file provided in this project, and restart the service. Make sure to modify the IP address, port, username, and password.
5. Modify the application.yml configuration file in the nicefish-elastic-search module, setting the IP address and port of ElasticSearch to your own.
6. Start the NiceFishSearchApplication.java in the nicefish-elastic-search module.
7. Open a browser and access http://localhost:8899/nicefish/search/user/get-all to test if the search service is working properly. Adjust it with your local configuration.
8. Set up a proxy in your front-end project to route services with the /search prefix to the nicefish-elastic-search service.

## 4. Project Module Dependency

<img src="https://gitee.com/mumu-osc/nicefish-spring-boot/raw/master/docs/imgs/maven-modules.png">

1. nicefish-core: Provides common utilities.
1. nicefish-shiro-rbac: Provides general authentication and authorization services based on Shiro, using RBAC.
1. nicefish-cms: CMS application based on the above basic modules.
1. nicefish-elastic-search: Provides search service based on ElasticSearch (optional, not required to start).

## 5. Physical Model

<img src="https://gitee.com/mumu-osc/nicefish-spring-boot/raw/master/docs/imgs/pdm.png">

The physical model .pdm file is located in the docs directory and can be opened using PowerDesigner. It is currently designed for MySQL databases.

## 6. Series Projects

<h4>NiceFish Client Projects:</h4>

| Name | Description | Stars |
| --- | --- | --- |
| [NiceFish](http://git.oschina.net/mumu-osc/NiceFish/) | The front-end interface based on the Angular framework, using the latest version of Angular with PrimeNG. | <a href='https://gitee.com/mumu-osc/NiceFish/stargazers'><img src='https://gitee.com/mumu-osc/NiceFish/badge/star.svg?theme=gvp' alt='star'></img></a> |
| [NiceFish-React](https://gitee.com/mumu-osc/NiceFish-React) | The front-end interface based on React 18.0.0, using PrimeReact and a customized Bootstrap version. Pure JSX, not TypeScript. | <a href='https://gitee.com/mumu-osc/NiceFish-React/stargazers'><img src='https://gitee.com/mumu-osc/NiceFish-React/badge/star.svg?theme=dark' alt='star'></img></a> |
| [nicefish-ionic](http://git.oschina.net/mumu-osc/nicefish-ionic) | A mobile demo based on Ionic, and this project also supports PWA. | <a href='https://gitee.com/mumu-osc/nicefish-ionic/stargazers'><img src='https://gitee.com/mumu-osc/nicefish-ionic/badge/star.svg?theme=dark' alt='star'></img></a> |
| [NiceBlogElectron](https://gitee.com/mumu-osc/NiceBlogElectron) | A desktop project based on Electron, packaging NiceFish into a desktop application. Contributed by a friend from ZTE. If you're exploring Electron development, refer to this project. | <a href='https://gitee.com/mumu-osc/NiceBlogElectron/stargazers'><img src='https://gitee.com/mumu-osc/NiceBlogElectron/badge/star.svg?theme=dark' alt='star'></img></a> |

<h4>NiceFish Server Projects:</h4>

| Name | Description | Stars |
| --- | --- | --- |
| [nicefish-spring-boot](https://gitee.com/mumu-osc/nicefish-spring-boot) | Demonstrates the integration of front-end and back-end in the front-end and back-end separation mode. Completed the baseline version. Based on this, you can further develop code that suits your business scenario. | <a href='https://gitee.com/mumu-osc/nicefish-spring-boot/stargazers'><img src='https://gitee.com/mumu-osc/nicefish-spring-boot/badge/star.svg?theme=dark' alt='star'></img></a> |
| [nicefish-spring-cloud](https://gitee.com/mumu-osc/nicefish-spring-cloud) | Demonstrates the integration of front-end code with distributed back-end services in the front-end and back-end separation mode. | <a href='https://gitee.com/mumu-osc/nicefish-spring-cloud/stargazers'><img src='https://gitee.com/mumu-osc/nicefish-spring-cloud/badge/star.svg?theme=dark' alt='star'></img></a> |

## 7. Front-End Interface Screenshots

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

## 8. Open Source License

MIT

(Note: You are free to use this project, but I am not responsible for any losses caused by its use.)

**(The author of this project is currently seeking a new job opportunity. If you have a good opportunity, please contact me on WeChat: lanxinshuma.)**
