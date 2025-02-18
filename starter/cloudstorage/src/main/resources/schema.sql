CREATE TABLE IF NOT EXISTS USERS (
  userId INT PRIMARY KEY auto_increment,
  username VARCHAR(20),
  salt VARCHAR,
  password VARCHAR,
  firstname VARCHAR(20),
  lastname VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS NOTES (
    noteId INT PRIMARY KEY auto_increment,
    noteTitle VARCHAR(20),
    noteDescription VARCHAR (1000),
    userId INT,
    foreign key (userid) references USERS(userid)
);

CREATE TABLE IF NOT EXISTS FILES (
    fileId INT PRIMARY KEY auto_increment,
    filename VARCHAR,
    contentType VARCHAR,
    fileSize VARCHAR,
    userId INT,
    fileData BLOB,
    foreign key (userid) references USERS(userid)
);

CREATE TABLE IF NOT EXISTS CREDENTIALS (
    credentialId INT PRIMARY KEY auto_increment,
    url VARCHAR(100),
    username VARCHAR (30),
    key VARCHAR,
    password VARCHAR,
    userId INT,
    foreign key (userid) references USERS(userid)
);