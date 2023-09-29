package com.lylastudio.catetduit.util;

import com.lylastudio.catetduit.model.http.Message;
import com.lylastudio.catetduit.model.http.ResponseRoot;
import com.lylastudio.catetduit.model.http.SendMessage;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
public class TelegramClient {
    RestClient restClient;

    @Value("${telegram.baseurl}")
    String telegramBaseUrl;

    @Value("${telegram.token}")
    String telegramToken;

    @Value("${telegram.sendmessageurl}")
    String sendMessageUrl;

    /** ini di set di PostConstruct, tidak di set di class Constactor
     * karena properti value nya tidak terbaca, nilai-nya masih null
     * saat class Constuctuctor
     */
    @PostConstruct
    private void init(){
        restClient = RestClient.builder()
        .baseUrl(telegramBaseUrl+telegramToken)
        .build();
    }

    public ResponseRoot sendMessage(SendMessage sendMessage ){

        log.info("base_url: " + telegramBaseUrl+telegramToken);
        log.info("uri: " + sendMessageUrl);

        return restClient.post()
                .uri(sendMessageUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .body(sendMessage)
                .retrieve()
                .body(ResponseRoot.class);

    }

//    public static void main(String[] args) {
//        log.info("test");
//    }


}
