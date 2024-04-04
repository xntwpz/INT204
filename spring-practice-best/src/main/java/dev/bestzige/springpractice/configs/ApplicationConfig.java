package dev.bestzige.springpractice.configs;

import dev.bestzige.springpractice.utils.ListMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }

    @Bean
    public ListMapper listMapperBean() {
        return ListMapper.getInstance();
    }
}
