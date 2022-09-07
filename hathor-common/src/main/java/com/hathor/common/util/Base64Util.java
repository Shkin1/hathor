package com.hathor.common.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * <p>Base64Util</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/08/2022/8/2 09:42
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
public class Base64Util {
    public static String decodeUTF8(String code) {
        return new String(Base64.getDecoder().decode(code), StandardCharsets.UTF_8);
    }
}
