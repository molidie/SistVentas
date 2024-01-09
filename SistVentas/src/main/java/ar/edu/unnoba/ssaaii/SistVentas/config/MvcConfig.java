package ar.edu.unnoba.ssaaii.SistVentas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by jpgm on 31/10/22.
 */

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("/Home/home");
        registry.addViewController("/vendedor").setViewName("/Home/Vendedor/newVendedor");

    }
}