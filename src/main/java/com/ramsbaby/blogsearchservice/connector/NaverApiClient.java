package com.ramsbaby.blogsearchservice.connector;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
public class NaverApiClient {

    private WebClient webClient;

    @Value("${naver-developers.search.host}")
    private String naverApiHost;

    @Value("${naver-developers.search.client-id}")
    private String naverApiClientId;

    @Value("${naver-developers.search.client-secret}")
    private String naverApiClientSecret;

    @PostConstruct
    public void initWebclient() {
        this.webClient = WebClient.builder().baseUrl(naverApiHost).build();
    }

//    public KaKaoBlogSearchResponse searchKakaoBlog(KakaoBlogSearchRequestDto requestDto) {
//        return this.webClient.get()
//            .uri(uriBuilder -> uriBuilder.path("/v2/search/blog")
//                .queryParam("query", requestDto.getQuery())
//                .queryParam("sort", requestDto.getSort())
//                .queryParam("page", requestDto.getPage())
//                .queryParam("size", requestDto.getSize())
//                .build())
//            .header(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoApikey)
//            .retrieve()
//            .bodyToMono(KaKaoBlogSearchResponse.class)
//            .block();
//    }

}
