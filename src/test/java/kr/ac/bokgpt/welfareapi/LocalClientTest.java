package kr.ac.bokgpt.welfareapi;


import kr.ac.bokgpt.welfareapi.local.LocalClient;
import kr.ac.bokgpt.welfareapi.local.dto.LocalReq;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LocalClientTest {

    @Autowired
    private LocalClient localClient;

    @Test
    public void localSearchTest(){

        var search = new LocalReq();
        search.setNumOfRows("10");
        var result = localClient.localSearch(search);
        System.out.println(result);
    }

}
