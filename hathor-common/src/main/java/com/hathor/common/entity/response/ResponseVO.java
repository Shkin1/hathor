package com.hathor.common.entity.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>ResponseVO</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/30 18:35
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@Data
public class ResponseVO {
    @ApiModelProperty(value = "错误编码")
    private String errCode;
    @ApiModelProperty(value = "错误信息")
    private String errMsg;
    @ApiModelProperty(value = "返回数据")
    private Object data;
    @ApiModelProperty(value = "返回状态")
    private Boolean flag;

    public static final String SC_OK = "200";
    public static final String SC_NOT_OK = "-1";


    /**
     * @param code    code
     * @param data    data
     * @param message message
     * @param flag    flag
     */
    private ResponseVO(String code, Object data, String message, boolean flag) {
        this.errCode = code;
        this.data = data;
        this.errMsg = message;
        this.flag = flag;
    }

    public static ResponseVO fail(String msg) {
        return new ResponseVO(SC_NOT_OK, null, msg, false);
    }

    public static ResponseVO fail(String code, String msg, boolean isSuccess) {
        return new ResponseVO(code, null, msg, isSuccess);
    }

    public static ResponseVO ok(Object o) {
        return new ResponseVO(SC_OK, o, "success", true);
    }

    public static ResponseVO ok() {
        return new ResponseVO(SC_OK, null, "success", true);
    }

}
