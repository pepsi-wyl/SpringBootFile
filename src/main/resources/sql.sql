CREATE database file_boot;
use file_boot;

CREATE table t_user
(
    id       bigint primary key auto_increment,
    username varchar(30) not null,
    password varchar(20) not null
);

CREATE table t_file
(
    id          bigint primary key auto_increment,
    oldFilename varchar(200) not null,
    newFilename varchar(200) not null,
    path        varchar(300) not null,
    size        varchar(200) not null,
    ext         varchar(20)  not null,
    type        varchar(120) not null,
    downCount   int(6)       not null,
    uploadTime  datetime     not null,
    isImg       varchar(8)   not null,
    userId      bigint
);