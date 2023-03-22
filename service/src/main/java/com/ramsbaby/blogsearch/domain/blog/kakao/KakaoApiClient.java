package com.ramsbaby.blogsearch.domain.blog.kakao;

import com.ramsbaby.blogsearch.domain.blog.dto.BlogSearchRequestDto;
import com.ramsbaby.blogsearch.domain.blog.dto.BlogSearchResponseDto;
import com.ramsbaby.blogsearch.domain.blog.kakao.dto.KBlogSearchRequestDto;
import com.ramsbaby.blogsearch.domain.blog.kakao.dto.KBlogSearchResponseDto;
import com.ramsbaby.blogsearch.api.BlogSearchApi;

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
    private String host;

    @Value("${kakao-developers.search.api-key}")
    private String kakaoApikey;

    @PostConstruct
    public void initWebclient() {
        this.webClient = WebClient.builder().baseUrl(host).build();
    }

    @Override
    public List<BlogSearchResponseDto> searchBlog(BlogSearchRequestDto requestDto) {
        KBlogSearchRequestDto transRequestDto = KBlogSearchRequestDto.of(requestDto);
        KBlogSearchResponseDto responseDto = send(transRequestDto);

        assert responseDto != null;
        return getKBlogResponse(responseDto);
    }

    private KBlogSearchResponseDto send(KBlogSearchRequestDto requestDto) {
        return this.webClient.get()
            .uri(uriBuilder -> uriBuilder.path("/v2/search/blog")
                .queryParam("query", requestDto.getQuery())
                .queryParam("sort", requestDto.getSort())
                .queryParam("page", requestDto.getPage())
                .queryParam("size", requestDto.getSize())
                .build())
            .header(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoApikey)
            .retrieve()
            .onStatus(HttpStatus::isError, clientResponse -> Mono.empty())
            .bodyToMono(KBlogSearchResponseDto.class)
            .block();
    }

    private List<BlogSearchResponseDto> getKBlogResponse(KBlogSearchResponseDto responseDto) {
        return responseDto.getDocuments().stream().limit(MAX_PAGE_SIZE).map(BlogSearchResponseDto::new)
            .collect(Collectors.toList());
    }
}
