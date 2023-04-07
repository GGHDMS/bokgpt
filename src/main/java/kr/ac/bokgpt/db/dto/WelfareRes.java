package kr.ac.bokgpt.db.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class WelfareRes {
    private Header header;
    private Welfare welfare;

    @Data
    public static class Header{
        private HttpStatus responseCode;

    }
}
