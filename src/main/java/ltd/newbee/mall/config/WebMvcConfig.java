package ltd.newbee.mall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将所有以 /upload/ 开头的请求，映射到你电脑的 D:/pics/upload/ 目录下
        // 注意：路径必须以 file: 开头，并且以 / 结尾
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:D:/pics/upload/");
    }
}