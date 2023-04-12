package kr.ac.bokgpt.api.service;

import kr.ac.bokgpt.api.db.dto.WelfareRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class WelfareGetService {

    public WelfareRes welfareListServ(){
        log.info("Hi");

        WelfareRes welfareRes = null;
        return welfareRes;
    }


    public WelfareRes welfareServ(){

        WelfareRes welfareRes = null;
        log.info("run");
        return welfareRes;
    }

}



