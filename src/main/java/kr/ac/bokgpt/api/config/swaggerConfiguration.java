package kr.ac.bokgpt.api.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "BokgPT API 명세서", description = "hi there!", version = "v0"))
@RequiredArgsConstructor
public class swaggerConfiguration {

    @Bean
    public GroupedOpenApi welfareOpenApi(){
        String[] paths = {"/**"};
        return GroupedOpenApi.
                builder()
                .group("welfare_infomation")
                .group("business")
                .pathsToMatch(paths)
                .build();
    }

}
