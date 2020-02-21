create table question
(
id int auto_increment,
title varchar(50),
description text,
gmt_create bigint,
gmt_update bigint,
creator_id int,
comment_count int default 0,
view_count int default 0,
like_count int default 0,
tags varchar(256),
constraint question_pk
primary key (id)
);

comment on column question.title is '标题';

comment on column question.description is '描述';

comment on column question.creator_id is '创建用户id';

comment on column question.comment_count is '评论数';

comment on column question.view_count is '阅读数';

comment on column question.like_count is '点赞数';

comment on column question.tags is '标签';
