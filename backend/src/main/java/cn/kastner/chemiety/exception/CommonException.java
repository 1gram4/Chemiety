package cn.kastner.chemiety.exception;

public class CommonException extends Exception {

    private Integer code;

    public CommonException(String message) {
        super(message);
    }

    public CommonException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
