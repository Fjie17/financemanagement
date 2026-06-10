create database linyi;
use linyi;

-- 创建用户表
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,  -- 用户名
    password VARCHAR(255) NOT NULL,        -- 密码（加密存储）
    email VARCHAR(100) NOT NULL UNIQUE,    -- 邮箱，用于找回密码
    role ENUM('user', 'finance', 'admin') DEFAULT 'user', -- 用户角色
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

insert into user(id, username, password, email, role) VALUES
(1001,'zonghengshuxie',112233,'112233@123.com','user'),
(1002,'weilaiqiyejiaxiehui',112244,'112244@123.com','user'),
(2001,'caiwu1',123123,'123123@123.com','finance'),
(0001,'admin',123,'123@123.com','admin');

-- 创建场地预约记录表
CREATE TABLE Reservation_Records (
    Reservation_ID INT AUTO_INCREMENT PRIMARY KEY,
    Reserving_Club VARCHAR(255) NOT NULL,          -- 预约社团名称
    Venue VARCHAR(255) NOT NULL,                   -- 预约地点
    Reservation_Time_Start DATETIME NOT NULL,            -- 预约开始时间
    Reservation_Time_End DATETIME NOT NULL,            -- 预约结束时间
    Reservation_Status ENUM('Approved', 'Rejected') NOT NULL, -- 预约进度
    Record_Creation_Time TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 记录生成时间，默认为当前时间
);

insert into Reservation_Records(Reservation_ID, Reserving_Club, Venue,Reservation_Time_Start,Reservation_Time_End,Reservation_Status) VALUES
(10001,'zonghengshuxie','新教203','2025-1-12 13:00:00','2025-1-12 18:00:00','Approved');

-- 创建查看报销记录表
CREATE TABLE Reimbursement_Records (
    Reimbursement_ID INT AUTO_INCREMENT PRIMARY KEY,
    Reimbursing_Club VARCHAR(255) NOT NULL,          -- 报销社团名称
    Reimbursement_Content TEXT NOT NULL,             -- 报销内容
    Reimbursement_Amount DECIMAL(10, 2) NOT NULL,    -- 报销金额，保留两位小鼠
    Reimbursement_Status ENUM('Not Submitted', 'Submitted', 'Rejected by Finance', 'Approved by Finance', 'Issued') NOT NULL, -- 报销进度
    Last_Status_Update_Time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 报销进度最后修改时间
    Record_Creation_Time TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 记录生成时间
);

insert into Reimbursement_Records(Reimbursement_ID, Reimbursing_Club, Reimbursement_Content, Reimbursement_Amount, Reimbursement_Status) VALUES
(10001,'zonghengshuxie','打印海报费用及其他','300.00','Not Submitted');

select * from Reimbursement_Records;

-- 创建报销预约表
CREATE TABLE Reimbursement_application (
    Reimbursement_ID INT AUTO_INCREMENT PRIMARY KEY,
    Reimbursing_Club VARCHAR(255) NOT NULL,          -- 报销社团名称
    Reimbursement_Content TEXT NOT NULL,             -- 报销内容
    Reimbursement_Amount DECIMAL(10, 2) NOT NULL,    -- 报销金额，保留两位小数
    Reimbursement_Status ENUM('Not Submitted', 'Submitted', 'Rejected by Finance', 'Approved by Finance', 'Issued') NOT NULL, -- 报销进度
    Invoice_Attached BOOLEAN DEFAULT FALSE,         -- 是否有发票（默认没有）
    Invoice_Authentication ENUM('Not Authenticated', 'Authenticated') DEFAULT 'Not Authenticated', -- 发票认证状态
    Last_Status_Update_Time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 报销进度最后修改时间
    Record_Creation_Time TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 记录生成时间
);

insert into Reimbursement_application(Reimbursement_ID, Reimbursing_Club, Reimbursement_Content, Reimbursement_Amount, Reimbursement_Status, Invoice_Attached, Invoice_Authentication) VALUES
(10001,'zonghengshuxie','打印海报费用及其他',300.00,'Submitted',TRUE,'Not Authenticated');

-- 创建认证发票表
CREATE TABLE Invoice_Authentication (
    Invoice_ID INT AUTO_INCREMENT PRIMARY KEY,
    Reimbursing_Club VARCHAR(255) NOT NULL,          -- 所属社团
    Invoice_Name VARCHAR(255) NOT NULL,              -- 发票名称
    Authentication_Status ENUM('Not Authenticated', 'Authenticated') DEFAULT 'Not Authenticated', -- 认证状态
    Invoice_File BLOB,                      -- 发票文件，二进制大对象
    Upload_Time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 上传文件时间
    Authentication_Time TIMESTAMP,                  -- 认证时间（可为空）
    Record_Creation_Time TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 记录生成时间
);

drop table Invoice_Authentication;

insert into Invoice_Authentication(Invoice_ID, Reimbursing_Club, Invoice_Name ,Authentication_Status) VALUES
('10001','zonghengshuxie','海报发票','Not Authenticated');

-- 创建发票文件表
CREATE TABLE Invoice_File (
    File_ID INT AUTO_INCREMENT PRIMARY KEY,
    File_Name VARCHAR(255) NOT NULL,              -- 文件名称，与认证发票表中的 Invoice_Name 相同
    File_Path VARCHAR(255) NOT NULL,              -- 文件路径
    Invoice_ID INT,                              -- 外键关联 Invoice_Authentication 表
    Upload_Time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 上传时间
    Record_Creation_Time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 记录生成时间

    -- 外键约束
    FOREIGN KEY (Invoice_ID) REFERENCES Invoice_Authentication (Invoice_ID)
);

insert into Invoice_File(File_ID, File_Name, File_Path, Invoice_ID, Upload_Time) VALUES
('10001','海报发票','E:\Wechat File\WeChat Files\wxid_u23ixagx7zkj22\FileStorage\File\2024-11\发票\24112000000169300170_浙江工业大学.pdf','10001','2025-1-1 13:00:00');

-- 创建社团银行卡号表
CREATE TABLE Club_Bank_Info (
    Bank_ID INT AUTO_INCREMENT PRIMARY KEY,
    Club_Name VARCHAR(255) NOT NULL,              -- 社团名称
    Bank_Name VARCHAR(255) NOT NULL,              -- 银行名称
    Account_Number VARCHAR(50) NOT NULL,          -- 银行账号
    Account_Holder VARCHAR(255) NOT NULL,         -- 账户持有者
    Account_Type ENUM('Savings', 'Checking') NOT NULL, -- 账户类型储蓄/借记
    Branch_Name VARCHAR(255) NOT NULL,            -- 分行名称
    Created_At TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 创建时间
);

INSERT INTO Club_Bank_Info (Club_Name, Bank_Name, Account_Number, Account_Holder, Account_Type, Branch_Name)
VALUES ('zonghengshuxie', '中国银行', '1234567890123456', '张三', 'Savings', '总行');

drop table Suggestions;

CREATE TABLE Suggestions (
    Suggestion_ID INT AUTO_INCREMENT PRIMARY KEY,       -- 编号，主键
    Club_Name VARCHAR(255) NOT NULL,                    -- 社团名
    Suggestion_Content TEXT NOT NULL,                   -- 建议内容
    Submission_Time TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 提交时间
);

insert into Suggestions(Suggestion_ID, Club_Name, Suggestion_Content, Submission_Time) VALUES
(10001,'zonghengshuxie','优化页面显示等','2024-12-17 20:01:03');