package com.hathor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * <p>Portal</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 15:59
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@SpringBootApplication(scanBasePackages = {HathorApplication.BASE_PACKAGE}, exclude = {DataSourceAutoConfiguration.class})
public class HathorApplication {

    public final static String BASE_PACKAGE = "com.hathor.*";
    public static void main(String[] args) {

        SpringApplication.run(HathorApplication.class, args);
    }
}
