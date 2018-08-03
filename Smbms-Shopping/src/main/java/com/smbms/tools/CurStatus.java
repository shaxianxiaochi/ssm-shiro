package com.smbms.tools;

public class CurStatus {
    public static final int USERNAME_ERROR = 10001;  // 账户名错误
    public static final int USERPWD_ERROR = 10002;  // 密码名错误
    /**
     * 不知道发生什么错误
     */
    public static final int ERROR = 99999;
    /**
     * 要删除的数据被其它表引用
     */
    public static final int OBJ_EXIST = 10003;
    /**
     * 0条数据
     */
    public static final int DATA_ZERO = 10004;
    /**
     * 字段已存在
     */
    public static final int FIELD_EXISTS = 10005;

    /**
     * 对象不存在
     */
    public static final int OBJ_NOTEXISTS = 10006;

    /**
     * 不能删除自己
     */
    public static final int DEL_ERROR = 10007;

    /**
     * 不能删除自己
     */
    public static final int SESSION_ERROR = 10007;

    /**
     * 不能删除自己
     */
    public static final int STRING_ERROR = 10008;

    /**
     * 权限不够
     */
    public static final int AUTHORITY_ERROR = 10009;


}
