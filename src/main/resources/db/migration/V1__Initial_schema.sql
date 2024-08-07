##### Users Table
CREATE TABLE users (
  userid BIGINT NOT NULL,
  createddate datetime NULL,
  createduser BIGINT NULL,
  lastupdatedate datetime NULL,
  lastupdateuser BIGINT NULL,
  isdeleted BIT(1) NULL,
  email VARCHAR(255) NULL,
  password VARCHAR(255) NULL,
  active INT NULL,
  CONSTRAINT pk_users PRIMARY KEY (userid)
);

CREATE TABLE account (
  id bigint NOT NULL,
  createddate datetime DEFAULT NULL,
  createduser bigint DEFAULT NULL,
  isdeleted bit(1) DEFAULT NULL,
  lastupdatedate datetime DEFAULT NULL,
  lastupdateuser bigint DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  organization varchar(25) DEFAULT NULL,
  subscription varchar(255) DEFAULT NULL,
  userid bigint DEFAULT NULL,
  CONSTRAINT pk_account PRIMARY KEY (id)
);

ALTER TABLE account ADD CONSTRAINT FK_ACCOUNT_ON_USERID FOREIGN KEY (userid) REFERENCES users (userid);

CREATE TABLE role (
  roleid INT AUTO_INCREMENT NOT NULL,
  rolename VARCHAR(20) NULL,
  CONSTRAINT pk_role PRIMARY KEY (roleid)
);

CREATE TABLE certification (
  id BIGINT NOT NULL,
  createddate datetime NULL,
  createduser BIGINT NULL,
  lastupdatedate datetime NULL,
  lastupdateuser BIGINT NULL,
  isdeleted BIT(1) NULL,
  name VARCHAR(255) NULL,
  code VARCHAR(255) NULL,
  issuer VARCHAR(255) NULL,
  desgin VARCHAR(255) NULL,
  description VARCHAR(255) NULL,
  published BIT(1) NULL,
  startdate datetime NULL,
  expiarydate datetime NULL,
  CONSTRAINT pk_certification PRIMARY KEY (id)
);

CREATE TABLE certificationgroups (
  id BIGINT NOT NULL,
  createddate DATETIME NULL,
  createduser BIGINT NULL,
  lastupdatedate DATETIME NULL,
  lastupdateuser BIGINT NULL,
  isdeleted BOOLEAN NULL,
  name VARCHAR(255) NULL,
  certificationid BIGINT NOT NULL,
  CONSTRAINT pk_groups PRIMARY KEY (id)
);

ALTER TABLE certificationgroups ADD CONSTRAINT FK_GROUP_ON_CERTIFICATIONID FOREIGN KEY (certificationid) REFERENCES certification (id);

CREATE TABLE members (
  id BIGINT NOT NULL,
  createddate datetime NULL,
  createduser BIGINT NULL,
  lastupdatedate datetime NULL,
  lastupdateuser BIGINT NULL,
  isdeleted BIT(1) NULL,
  name VARCHAR(255) NULL,
  email VARCHAR(255) NULL,
  groupid BIGINT NOT NULL,
  CONSTRAINT pk_members PRIMARY KEY (id)
);

ALTER TABLE members ADD CONSTRAINT FK_MEMBER_ON_GROUPID FOREIGN KEY (groupid) REFERENCES certificationgroups (id);

CREATE TABLE transactions (
  transactionid BIGINT NOT NULL,
  accountid BIGINT NULL,
  reference  VARCHAR(255) NULL,
  amount INT NULL,
  transactiontype VARCHAR(255) NULL,
  trasactiondate datetime NULL,
  status VARCHAR(255) NULL,
  description VARCHAR(255) NULL,
  CONSTRAINT pk_transactions PRIMARY KEY (transactionid)
);

CREATE TABLE messages (
  id BIGINT NOT NULL,
  senderid BIGINT NULL,
  receiverid BIGINT NULL,
  content VARCHAR(255) NULL,
  timestamp datetime NULL,
  messagetype VARCHAR(10) NULL,
  atttachements VARCHAR(255) NULL,
  deletedflag BIT(1) NULL,
  seenflag BIT(1) NULL,
  CONSTRAINT pk_messages PRIMARY KEY (id)
);

CREATE TABLE group_attributes (
  groupid bigint NOT NULL,
  attributevalue varchar(255) DEFAULT NULL,
  attributekey varchar(255) NOT NULL,
  PRIMARY KEY ( groupid, attributekey),
  CONSTRAINT FOREIGN KEY (groupid) REFERENCES certificationgroups (id)
);

CREATE TABLE hibernate_sequence (
  next_val bigint DEFAULT NULL
);

CREATE TABLE userrole (
  userid bigint NOT NULL,
  roleid int NOT NULL,
  PRIMARY KEY (userid,roleid),
  KEY  (roleid),
  CONSTRAINT FOREIGN KEY (userid) REFERENCES users (userid),
  CONSTRAINT FOREIGN KEY (roleid) REFERENCES role (roleid)
);