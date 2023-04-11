package kr.ac.bokgpt.welfareapi;


import kr.ac.bokgpt.welfareapi.center.CenterClient;
import kr.ac.bokgpt.welfareapi.center.dto.CenterReq;
import kr.ac.bokgpt.welfareapi.local.LocalClient;
import kr.ac.bokgpt.welfareapi.local.dto.LocalDetailReq;
import kr.ac.bokgpt.welfareapi.local.dto.LocalReq;
import kr.ac.bokgpt.welfareapi.youth.YouthClient;
import kr.ac.bokgpt.welfareapi.youth.dto.YouthReq;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientTest {

    @Autowired
    public LocalClient localClient;
    @Autowired
    public CenterClient centerClient;
    @Autowired
    public YouthClient youthClient;




    @Test
    public void localSearchTest(){

        var search = new LocalReq();
        search.setNumOfRows("10");
        var result = localClient.localSearch(search);
        System.out.println(result);
    }
    @Test
    public void localDetailSearchTest(){

        var search = new LocalDetailReq();
        search.setServId("WLF00001138");
        var result = localClient.localDetailSearch(search);
        System.out.println(result);
    }

    @Test
    public void centerSearchTest(){

        var search = new CenterReq();
        search.setNumOfRows("10");
        var result = centerClient.centerSearch(search);
        System.out.println(result);
    }

    @Test
    public void youthSearchTest(){
        var search = new YouthReq();
        var result = youthClient.youthSearch(search);
        System.out.println(result);
    }
}