package com.hathor.common.exception;

/**
 * <p>CommonException</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 16:56
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
public class CommonException extends RuntimeException {

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonException(String message) {
        super(message);
    }

    public CommonException(String format, Object... args) {
        super(String.format(format, args));
    }

    public CommonException(Throwable cause) {
        super(cause);
    }
}
