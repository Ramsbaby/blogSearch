package com.kakaobank.blogsearchservice.connector;

import com.kakaobank.blogsearchservice.domain.blog.dto.BlogRequestDto;
import com.kakaobank.blogsearchservice.domain.blog.dto.KaKaoBlogResponse;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
public class KakaoApiClient {

    private WebClient webClient;

    @Value("${kakao-developers.search.host}")
    private String kakaoApiHost;

    @Value("${kakao-developers.search.api-key}")
    private String kakaoApikey;

    @PostConstruct
    public void initWebclient() {
        this.webClient = WebClient.builder().baseUrl(kakaoApiHost).build();
    }

    public KaKaoBlogResponse searchKakaoBlog(BlogRequestDto requestDto) {
        return this.webClient.get()
            .uri(uriBuilder -> uriBuilder.path("/v2/search/blog")
                .queryParam("query", requestDto.getQuery())
                .queryParam("sort", requestDto.getSort())
                .queryParam("page", requestDto.getPage())
                .queryParam("size", requestDto.getSize())
                .build())
            .header(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoApikey)
            .retrieve()
            .bodyToMono(KaKaoBlogResponse.class)
            .block();
    }

}
