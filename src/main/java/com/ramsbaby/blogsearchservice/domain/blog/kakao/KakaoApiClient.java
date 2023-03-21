package com.ramsbaby.blogsearchservice.domain.blog.kakao;

import com.ramsbaby.blogsearchservice.api.BlogSearchApi;
import com.ramsbaby.blogsearchservice.domain.blog.dto.BlogSearchRequestDto;
import com.ramsbaby.blogsearchservice.domain.blog.dto.BlogSearchResponseDto;
import com.ramsbaby.blogsearchservice.domain.blog.kakao.dto.KBlogSearchRequestDto;
import com.ramsbaby.blogsearchservice.domain.blog.kakao.dto.KBlogSearchResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class KakaoApiClient implements BlogSearchApi {

    private final Long MAX_PAGE_SIZE = 50L;
    private WebClient webClient;
    @Value("${kakao-developers.search.host}")
    private String kakaoApiHost;
    @Value("${kakao-developers.search.api-key}")
    private String kakaoApikey;

    @PostConstruct
    public void initWebclient() {
        this.webClient = WebClient.builder().baseUrl(kakaoApiHost).build();
    }

    @Override
    public List<BlogSearchResponseDto> searchBlog(BlogSearchRequestDto requestDto) {
        KBlogSearchRequestDto transRequestDto = KBlogSearchRequestDto.of(requestDto);

        KBlogSearchResponseDto responseDto = this.webClient.get()
            .uri(uriBuilder -> uriBuilder.path("/v2/search/blog")
                .queryParam("query", transRequestDto.getQuery())
                .queryParam("sort", transRequestDto.getSort())
                .queryParam("page", transRequestDto.getPage())
                .queryParam("size", transRequestDto.getSize())
                .build())
            .header(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoApikey)
            .retrieve()
            .onStatus(HttpStatus::isError, clientResponse -> Mono.empty())
            .bodyToMono(KBlogSearchResponseDto.class)
            .block();
        assert responseDto != null;

        return responseDto.getDocuments().stream().map(BlogSearchResponseDto::new).limit(MAX_PAGE_SIZE)
            .collect(Collectors.toList());
    }
}
