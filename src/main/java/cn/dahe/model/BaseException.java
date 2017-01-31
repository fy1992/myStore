package cn.dahe.model;

/**
 * 自定义错误
 * Created by fy on 2017/1/31.
 */
public class BaseException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public BaseException() {
        super();
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

}
