package com.hathor.exception;

/**
 * <p>ParserException</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 10:47
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
public class ParserException extends RuntimeException {

    public ParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParserException(String message) {
        super(message);
    }

    public ParserException(String format, Object... args) {
        super(String.format(format, args));
    }

    public ParserException(String format, Throwable cause, Object... args) {
        super(String.format(format, args));
    }

    public ParserException(Throwable cause) {
        super(cause);
    }
}
