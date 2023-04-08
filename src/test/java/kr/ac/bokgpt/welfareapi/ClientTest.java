package kr.ac.bokgpt.welfareapi;


import kr.ac.bokgpt.welfareapi.center.dto.CenterReq;
import kr.ac.bokgpt.welfareapi.center.dto.CenterRes;
import kr.ac.bokgpt.welfareapi.local.dto.LocalReq;
import kr.ac.bokgpt.welfareapi.local.dto.LocalRes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientTest {

    public final Client<CenterRes, CenterReq> client;

    public ClientTest(@Qualifier("centerClient") Client<CenterRes, CenterReq> centerClient){
        this.client=centerClient;
    }


    @Test
    public void searchTest(){

        var search = new CenterReq();
        search.setNumOfRows("10");
        var result = client.search(search);
        System.out.println(result);
    }

//    public final Client<LocalRes, LocalReq> client;
//
//    public ClientTest(@Qualifier("localClient") Client<LocalRes, LocalReq> localClient) {
//        this.client = localClient;
//    }
//
//    @Test
//    public void searchTest(){
//
//        var search = new LocalReq();
//        search.setNumOfRows("10");
//        var result = client.search(search);
//        System.out.println(result);
//    }
}