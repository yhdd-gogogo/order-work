package com.tjj.zj.tjjwork.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.tjj.zj.tjjwork.swagger.config.BaseSwaggerConfig;
import com.tjj.zj.tjjwork.swagger.model.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Swagger API文档相关配置
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .title("")
                .description("")
                .contactName("zhao")
                .version("1.0")
//                .enableSecurity(true)
                .build();
    }

}
