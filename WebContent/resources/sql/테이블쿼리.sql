-- 게시판테이블
create table t_board(
    no  number(5) primary key
    , title varchar2(300) not null
    , writer varchar2(300) not null
    , content varchar2(2000) not null
    , view_cnt number(5) default 0
    , reg_date  date  default sysdate
);

-- 게시판번호 시퀀스
create sequence seq_t_board_no;

-- 회원테이블
create table t_member(
    id  varchar2(30) primary key
    , name varchar2(100) not null
    , password varchar2(30) not null
    , type  char(1) default 'U'
);

insert into t_member
 values('admin', '관리자', 'admin', 'S');
 
insert into t_member(id, name, password)
 values('user', '홍길동', 'user');
 
commit;

select * from t_member;

create table t_board_file(
    no  number(5) primary key
    , board_no number(5) not null
    , file_ori_name varchar2(300) 
    , file_save_name varchar2(300)
    , file_size number(10)
    , constraint t_board_file_no_fk foreign key(board_no)
      references t_board(no)
);

drop sequence t_board_file_no;
create sequence seq_t_board_file_no nocache;

select * from t_board;
select * from t_board_file;






















