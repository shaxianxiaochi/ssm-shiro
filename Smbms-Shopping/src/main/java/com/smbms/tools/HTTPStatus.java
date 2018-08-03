package com.smbms.tools;


public class HTTPStatus {
    public static final int CONTINUE = 100;             //请求的开始部分已经收到，客户可以继续它的请求
    public static final int SWITCHING_PROTOCOL = 101;   //服务器同意切换协议，只能切换到更高版本的协议
    public static final int OK = 200;                   //请求成功
    public static final int CREATED = 201;              //成功请求并创建了新的资源
    public static final int ACCEPTED = 202;             //已接受请求，但是还没有处理完成
    public static final int NON_AUTHORITATIVE_INFORMATION = 203; //非授权信息。请求成功，
    public static final int NO_CONTENT = 204;           //无内容，服务器成功处理，但未返回内容。在未更新网页的情况下，可确保浏览器继续显示当前文档
    public static final int MOVE_PERMANENTLY = 301;     //永久重定向，请求的资源已被永久的移动到新URI，返回信息会包括新的URI，浏览器会自动定向到新URI。今后任何新的请求都应使用新的URI代替
    public static final int FOUND = 302;                //临时移动。与301类似，但资源只是临时被移动。客户端应继续使用原有URI
    public static final int NOT_MODIFIED = 304;         //未修改，所请求的资源未修改，服务器返回此状态码时，不会返回任何资源。客户端通常会缓存访问过的资源，通过提供一个头信息指出客户端希望只返回在指定日期之后修改的资源
    public static final int BAD_REQUEST = 400;          //客户端请求的语法错误，服务器无法理解
    public static final int UNAUTHORIZED = 401;         //请求要求用户的身份认证
    public static final int FORBIDDEN = 403;            //服务器理解请求客户端的请求，但是拒绝执行此请求
    public static final int NOT_FOUND = 404;            //未找到，服务器无法根据客户端的请求找到资源（网页）。通过此代码，网站设计人员可设置”您所请求的资源无法找到”的个性页面
    public static final int METHOD_NOT_ALLOWED = 405;   //客户端请求中的方法被禁止
    public static final int NOT_ACCEPTABLE = 406;       //服务器无法根据客户端请求的内容特性完成请求
    public static final int INTERNAL_SERVER_ERROR = 500;//服务器内部错误，无法完成请求
    public static final int NOT_IMPLEMENTED = 501;      //服务器不支持请求的功能，无法完成请求
    public static final int SERVICE_UNAVAILABLE = 503;  //由于超载或系统维护，服务器暂时的无法处理客户端的请求。
}
