drop database UniversityManagementSystem;
drop user postgres;
drop table student;
drop table faculty;
drop sequence student_seq;
drop sequence faculty_seq;
create user postgres with password 'shvashrma';
create database UniversityManagementSystem with owner=postgres;
\connect UniversityManagementSystem;
alter default privileges grant all on tables to UniversityManagementSystem;
alter default privileges grant all on sequences to UniversityManagementSystem;

create table student(
    userId integer primary key not null,
    name varchar(100) not null,
    email varchar(100) unique not null,
    password text not null,
    isAdmin boolean default false
);


create table faculty(
    userId integer primary key,
    name varchar(100) not null,
    email varchar(100) unique not null,
    password text not null,
    isAdmin boolean default true
);

create sequence student_seq increment 1 start 0001;
create sequence faculty_seq increment 1 start 0001;

