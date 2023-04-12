package kr.ac.bokgpt.api.welfareapi.local.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class LocalDetailReq {

    @NotNull
    private String servId;

}
