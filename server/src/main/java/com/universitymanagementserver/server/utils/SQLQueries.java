package com.universitymanagementserver.server.utils;

public class SQLQueries {

//    User Table Queries -->
    public static final String SQL_CREATE_USER = "insert into users(userId , name , email , password)" + "values(NEXTVAL('user_seq'), ? , ? , ?)";
    public static final String SQL_FIND_USER_BY_ID = "select * from users where userId = ?";
    public static final String SQL_FIND_USER_BY_EMAIL = "select * from users where email = ?";
    public static  final String SQL_FIND_USER_COUNT_BY_EMAIL = "select COUNT(*) from users where email = ?";


}
