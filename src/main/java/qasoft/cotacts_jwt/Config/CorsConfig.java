package qasoft.cotacts_jwt.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("*") // Permite todos los métodos
                .allowedHeaders("*")
                .exposedHeaders("Authorization") // Importante para JWT
                .allowCredentials(true)
                .maxAge(3600); // Cache de opciones preflight
    }
}
