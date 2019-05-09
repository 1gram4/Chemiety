package cn.kastner.chemiety.exception;

public class FileException extends CommonException {

    public static final String EMPTY_FILE = "文件内容为空";

    private final Integer code;

    public FileException(String message) {
        super(message);
        switch (message) {
            case EMPTY_FILE:
                this.code = -2;
                break;
            default:
                this.code = -1;
                break;
        }
    }

    @Override
    public Integer getCode() {
        return this.code;
    }
}
