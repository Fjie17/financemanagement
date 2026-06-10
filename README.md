# Finance Management · 社团报销管理系统

> 基于 **Java MVC + JSP + Servlet** 的高校社团财务与场地预约管理平台，支持多角色权限、报销全流程、发票认证与用户管理。

[![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Servlet](https://img.shields.io/badge/Servlet-4.0-blue)](https://jakarta.ee/specifications/servlet/4.0/)
[![JSP](https://img.shields.io/badge/JSP-2.3-lightgrey)](https://projects.eclipse.org/projects/ee4j.jsp)
[![Tomcat](https://img.shields.io/badge/Tomcat-9.0-red?logo=apachetomcat&logoColor=white)](https://tomcat.apache.org/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?logo=mysql&logoColor=white)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-green)](LICENSE)

---

## 目录

- [项目简介](#项目简介)
- [功能特性](#功能特性)
- [技术栈](#技术栈)
- [系统架构](#系统架构)
- [项目结构](#项目结构)
- [角色与权限](#角色与权限)
- [数据库设计](#数据库设计)
- [环境要求](#环境要求)
- [快速开始](#快速开始)
- [配置说明](#配置说明)
- [主要接口](#主要接口)
- [开发说明](#开发说明)
- [后续规划](#后续规划)
- [许可证](#许可证)
- [作者](#作者)

---

## 项目简介

**Finance Management** 是一套面向高校社团场景的 Web 管理系统，用于规范社团 **场地预约** 与 **费用报销** 流程。系统采用经典三层 MVC 架构，前端使用 JSP 渲染页面，后端通过 Servlet 处理请求，JDBC 直连 MySQL 数据库。

三类用户（社团用户、财务人员、系统管理员）登录后进入各自工作台，完成申请提交、财务审核、数据监管等操作。

---

## 功能特性

### 社团用户 `user`

| 模块 | 功能 |
|------|------|
| 场地预约 | 提交场地预约申请，查看本社团预约记录 |
| 报销申请 | 填写报销表单、上传发票附件，查看报销进度 |
| 个人信息 | 查看与维护账户信息 |
| 意见反馈 | 通过联系页面向管理员提交建议 |

### 财务人员 `finance`

| 模块 | 功能 |
|------|------|
| 报销审核 | 查看报销申请列表，批准或驳回报销 |
| 发票认证 | 上传并认证发票，查看认证状态 |
| 记录查询 | 查看报销记录与发票认证记录 |
| 发票下载 | 下载已上传的发票文件 |

### 系统管理员 `admin`

| 模块 | 功能 |
|------|------|
| 预约监管 | 查看全部场地预约记录 |
| 报销监管 | 查看全部报销记录 |
| 用户管理 | 查看普通用户与财务人员，支持增删改 |
| 管理员管理 | 查看管理员账户列表 |
| 建议箱 | 查看社团提交的意见与建议 |

### 公共功能

- 用户注册与登录（按角色分流至不同 Dashboard）
- Session 会话保持与身份校验
- 统一 UI 风格（紫色主题 `#391484`）

---

## 技术栈

| 层级 | 技术 |
|------|------|
| 表现层 | JSP、HTML5、CSS3、JavaScript |
| 控制层 | Servlet 4.0（`@WebServlet` 注解路由） |
| 业务层 | Service |
| 持久层 | DAO + JDBC（PreparedStatement） |
| 数据库 | MySQL 8.x |
| 应用服务器 | Apache Tomcat 9.0 |
| 开发工具 | Eclipse IDE（Dynamic Web Project） |
| JDK | Java 21 |

---

## 系统架构

```
┌─────────────────────────────────────────────────────────┐
│                     Browser（浏览器）                     │
└─────────────────────────┬───────────────────────────────┘
                          │ HTTP
┌─────────────────────────▼───────────────────────────────┐
│  View 层          JSP 页面（Dashboard / Form / List）     │
└─────────────────────────┬───────────────────────────────┘
                          │
┌─────────────────────────▼───────────────────────────────┐
│  Controller 层    Servlet（请求分发、参数校验、Session）    │
└─────────────────────────┬───────────────────────────────┘
                          │
┌─────────────────────────▼───────────────────────────────┐
│  Service 层       业务逻辑（报销、预约、用户、发票等）       │
└─────────────────────────┬───────────────────────────────┘
                          │
┌─────────────────────────▼───────────────────────────────┐
│  DAO / JDBC 层    数据访问（Admin5Dao、SuggestionDao 等）  │
└─────────────────────────┬───────────────────────────────┘
                          │
┌─────────────────────────▼───────────────────────────────┐
│  MySQL            数据库 linyi                             │
└─────────────────────────────────────────────────────────┘
```

**设计模式：** 经典 MVC 分层 · 面向接口的数据访问 · Session 身份认证

---

## 项目结构

```
financemanagement/
├── src/main/
│   ├── java/com/
│   │   ├── model/              # 实体类
│   │   │   ├── User.java
│   │   │   ├── ReimbursementRecord.java
│   │   │   ├── ReservationRecord.java
│   │   │   └── Suggestion.java
│   │   ├── servlet/            # 控制器（22 个 Servlet）
│   │   ├── service/            # 业务逻辑层
│   │   ├── dao/                # 数据访问层
│   │   └── util/
│   │       └── DatabaseUtil.java   # 数据库连接工具
│   └── webapp/                 # Web 资源根目录
│       ├── login.jsp           # 登录页
│       ├── register.jsp        # 注册页
│       ├── userDashboard.jsp   # 用户工作台
│       ├── financeDashboard.jsp
│       ├── adminDashboard.jsp
│       ├── *.jsp               # 业务表单与列表页
│       ├── WEB-INF/
│       │   └── Web.xml         # 部分 Servlet 映射配置
│       └── META-INF/
├── .project                    # Eclipse 项目配置
├── .classpath
└── README.md
```

---

## 角色与权限

登录时需同时选择 **用户名 / 密码 / 角色**，系统校验三者一致后跳转对应首页：

| 角色 | 标识 | 登录后跳转 | 主要职责 |
|------|------|-----------|----------|
| 社团用户 | `user` | `userDashboard.jsp` | 预约场地、提交报销 |
| 财务人员 | `finance` | `financeDashboard.jsp` | 审核报销、认证发票 |
| 系统管理员 | `admin` | `adminDashboard.jsp` | 全局数据与用户管理 |

---

## 数据库设计

默认数据库名：**`linyi`**

| 表名 | 说明 |
|------|------|
| `user` | 用户账户（username, password, email, role） |
| `Reimbursement_application` | 报销申请（待提交/审核） |
| `Reimbursement_Records` | 报销记录（含状态与时间戳） |
| `Invoice_Authentication` | 发票认证信息 |
| `Invoice_File` | 发票文件存储 |
| `Club_Bank_Info` | 社团银行账户信息 |
| `Reservation_Records` | 场地预约记录 |
| `Suggestions` | 社团意见与建议 |

> **注意：** 仓库中未包含 SQL 初始化脚本，请根据上述表结构在本地 MySQL 中自行建库建表，或从现有开发环境导出 schema。

---

## 环境要求

| 依赖 | 版本 |
|------|------|
| JDK | 21+ |
| Apache Tomcat | 9.0 |
| MySQL | 8.0+ |
| MySQL Connector/J | 8.x（需加入 Tomcat 或项目 `WEB-INF/lib`） |
| Eclipse IDE | 2023+（推荐，含 Web Tools Platform） |

---

## 快速开始

### 1. 克隆仓库

```bash
git clone https://github.com/Fjie17/financemanagement.git
cd financemanagement
```

### 2. 准备数据库

```sql
CREATE DATABASE linyi DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE linyi;
-- 在此创建上述业务表并插入测试数据
```

### 3. 配置数据库连接

修改 `src/main/java/com/util/DatabaseUtil.java` 中的连接信息：

```java
DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/linyi?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8",
    "你的用户名",
    "你的密码"
);
```

> 部分 Servlet（如 `LoginServlet`）内含独立数据库连接配置，部署前请一并修改，或统一重构为调用 `DatabaseUtil`。

### 4. 导入 Eclipse 并部署

1. 打开 Eclipse → **File → Import → Existing Projects into Workspace**
2. 选择项目根目录，确认 Facet 为 **Java 21 + Dynamic Web Module 4.0**
3. 将 **MySQL Connector/J** 驱动 JAR 放入 `src/main/webapp/WEB-INF/lib/`
4. 配置 Tomcat 9.0 运行环境
5. 右键项目 → **Run As → Run on Server**

### 5. 访问系统

```
http://localhost:8080/<项目上下文路径>/login.jsp
```

Eclipse 默认上下文路径通常为项目名（如 `111`），可在 Server 配置中修改。

---

## 配置说明

| 配置项 | 位置 | 说明 |
|--------|------|------|
| 数据库 URL / 账号 | `DatabaseUtil.java` | 全局 JDBC 连接 |
| 登录连接 | `LoginServlet.java` | 登录模块独立连接（建议合并） |
| Servlet 路由 | 各类 `*Servlet.java` 的 `@WebServlet` | 注解方式注册 |
| 补充映射 | `WEB-INF/Web.xml` | 部分 Servlet 的 XML 配置 |

**安全建议：** 请勿将真实数据库密码提交至公开仓库。生产环境应使用环境变量或外部配置文件管理敏感信息，并对用户密码进行哈希存储。

---

## 主要接口

| URL | Servlet | 功能 |
|-----|---------|------|
| `/login` | LoginServlet | 用户登录 |
| `/register` | RegisterServlet | 用户注册 |
| `/ReservationServlet` | ReservationServlet | 提交场地预约 |
| `/ViewReservationRecords` | ViewReservationRecordsServlet | 查看预约记录 |
| `/SubmitReimbursementServlet` | SubmitReimbursementServlet | 提交报销申请 |
| `/ProcessReimbursementApprovalServlet` | ProcessReimbursementApprovalServlet | 审批报销 |
| `/UploadInvoiceServlet` | UploadInvoiceServlet | 上传发票 |
| `/InvoiceAuthenticationServlet` | InvoiceAuthenticationServlet | 发票认证 |
| `/DownloadInvoiceServlet` | DownloadInvoiceServlet | 下载发票 |
| `/CheckBankInfoServlet` | CheckBankInfoServlet | 查询银行信息 |
| `/UserInformation` | UserInformationServlet | 用户信息 |
| `/SubmitSuggestionServlet` | SubmitSuggestionServlet | 提交建议 |
| `/admin1` ~ `/Admin5` | Admin1~5Servlet | 管理员数据查询 |

完整 Servlet 列表见 `src/main/java/com/servlet/` 目录。

---

## 开发说明

- **包命名规范：** `com.model` · `com.servlet` · `com.service` · `com.dao` · `com.util`
- **编码：** 全项目 UTF-8，数据库连接已指定 `characterEncoding=utf-8`
- **会话管理：** 登录成功后通过 `HttpSession` 存储 `user` 与 `username`
- **已知待优化项：**
  - 数据库连接分散在多个类中，建议统一至 `DatabaseUtil` 或连接池
  - 密码明文存储与传输，建议引入 BCrypt 等加密方案
  - `Web.xml` 中部分 servlet-name 与 mapping 存在不一致，建议核对

---

## 后续规划

- [ ] 提供完整的 `schema.sql` 初始化脚本
- [ ] 引入 Maven/Gradle 统一依赖管理
- [ ] 密码加密与配置文件外置
- [ ] 添加分页、搜索与操作日志
- [ ] 补充单元测试与接口文档

---

## 许可证

本项目采用 [MIT License](LICENSE) 开源。

---

## 作者

**Fjie17**

- GitHub：[@Fjie17](https://github.com/Fjie17)
- 仓库：[financemanagement](https://github.com/Fjie17/financemanagement)

---

<p align="center">
  <sub>Built with Java · JSP · Servlet · MySQL</sub>
</p>
