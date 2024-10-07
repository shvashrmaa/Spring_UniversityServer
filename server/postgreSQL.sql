drop table student;
drop table faculty;
drop sequence student_seq;
drop sequence faculty_seq;
drop database UniversityManagementSystem;
create user shvashrma with password 'shvashrma';
create database UniversityManagementSystem with owner=shvashrma;
\connect UniversityManagementSystem;
alter default privileges grant all on tables to shvashrma;
alter default privileges grant all on sequences to shvashrma;

create type gender as enum('Male' , 'Female');
create type stream as enum('CSE' , 'ME' , 'CE' , 'EE');
create type course as enum('B.TECH' , 'M.TECH' , 'BCA' , 'MCA' , 'BSC' , 'MSC');
create type courseType as enum('OFFLINE' , 'ONLINE');
create type studentType as enum('REGULAR' , 'PRIVATE');
create type designation as enum('HOD' , 'Professor' , 'Assistant Professor');
create type attendanceStatus as enum('PRESENT' , 'ABSENT');
create type availabilityStatus as enum('available' , 'borrowed');
create type reservationStatus as enum('pending' , 'fulfilled' , 'canceled');
create type fineStatus as enum('paid' , 'unpaid');

create table users(
    userId serial primary key not null,
    name varchar(255) not null,
    email varchar(255) not null unique,
    password text not null,
    gender gender,
    age integer,
    profilePicUrl text,
    createdAt timestamp,
    updateAt timestamp
);

create table student(
    userId integer primary key not null,
    enrollmentNumber integer not null,
    stream stream,
    course course,
    rollNumber integer not null,
    courseType courseType,
    studentType studentType,

    foreign key (userId)
                    references users(userId)
                    on delete cascade
);


create table faculty(
    userId integer primary key not null,
    designation designation,
    course course,
    stream stream,
    facultyId integer not null,
    subject varchar(255),
    foreign key (userId)
                    references users(userId)
                    on delete cascade
);


create table assignments
(
    assignmentId serial primary key not null,
    facultyId integer not null,
    deadline date default current_date + interval '15 days',
    courseId integer,
    title text,
    assignedDate date default current_date,
    foreign key (facultyId)
                          references faculty(userId)
                          on delete cascade ,
    foreign key (courseId)
                          references course(courseId)
                          on delete cascade

);

create table course(
    courseId serial primary key not null,
    courseName course,
    facultyId integer,
    foreign key (facultyId)
                   references faculty(userId)
                   on delete cascade
);

create table studentAssignments(
    assignmentId serial primary key not null,
    studentId integer,
    submittedTo integer,
    submittedAt date default current_date,
    grade int ,

    foreign key (studentId)
                               references student(userId)
                               on delete cascade ,
    foreign key (submittedTo)
                               references faculty(userId)
                               on delete cascade
);

create table Attendance(
    attendanceId serial primary key not null,
    studentId integer,
    courseId integer,
    attendanceDate date default current_date,
    status attendanceStatus,

    foreign key (studentId)
                       references student(userId)
                       on delete cascade,
    foreign key (courseId)
                       references course(courseId)
                       on delete cascade
);

create table reports(
    reportId serial primary key not null,
    studentId integer,
    courseId integer,
    content text,
    createdAt timestamp default now()
);

create table holidays(
    holidayId serial primary key not null,
    name text,
    date date
);

create table librarian(
    librarianId serial primary key,
    foreign key (librarianId)
                      references users(userId)
                      on delete cascade
);

create table book(
    bookId serial primary key not null,
    title text,
    author text,
    description text,
    publishedDate date,
    availabilityStatus availabilityStatus,
    isbn text
);

create table borrowings(
    borrowId serial primary key not null,
    bookId integer,
    userId integer,
    borrowedDate date default current_date,
    returnDate date,
    dueDate date default current_date + interval '15 days'
);

create table reservations(
    reservationId serial primary key not null,
    userId integer,
    bookId integer,
    reservationStatus reservationStatus,
    reservationDate date default current_date,

    foreign key (userId)
                         references users(userId)
                         on delete cascade,
    foreign key (bookId)
                         references book(bookId)
                         on delete cascade

);

create table fines(
    fineId serial primary key,
    userId integer,
    amount double precision,
    description text,
    fineStatus fineStatus,
    foreign key (userId)
                  references users(userId)
                  on delete cascade
);


create sequence user_seq increment 1 start 1;
create sequence assignment_seq increment 1 start 1;
create sequence course_seq increment 1 start 1;
create sequence studentAssignment_seq increment 1 start 1;
create sequence attendance_seq increment 1 start 1;
create sequence report_seq increment 1 start 1;
create sequence holiday_seq increment 1 start 1;
create sequence librarian_seq increment 1 start 1;
create sequence book_seq increment 1 start 1;
create sequence borrowing_seq increment 1 start 1;
create sequence reservations_seq increment 1 start 1;
create sequence fines_seq increment 1 start 1;

