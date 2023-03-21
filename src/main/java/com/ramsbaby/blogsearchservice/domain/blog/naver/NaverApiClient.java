package com.ramsbaby.blogsearchservice.domain.blog.naver;

import com.ramsbaby.blogsearchservice.api.BlogSearchApi;
import com.ramsbaby.blogsearchservice.domain.blog.dto.BlogSearchRequestDto;
import com.ramsbaby.blogsearchservice.domain.blog.dto.BlogSearchResponseDto;
import com.ramsbaby.blogsearchservice.domain.blog.kakao.dto.KBlogSearchResponseDto;
import com.ramsbaby.blogsearchservice.domain.blog.naver.dto.NBlogSearchRequestDto;
import com.ramsbaby.blogsearchservice.domain.blog.naver.dto.NBlogSearchResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
public class NaverApiClient implements BlogSearchApi {

    private final String NAVER_CLIENT_ID_HEADER = "X-Naver-Client-Id";
    private final String NAVER_CLIENT_SECRET_HEADER = "X-Naver-Client-Secret";
    private final Long MAX_PAGE_SIZE = 100L;

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

    @Override
    public List<BlogSearchResponseDto> searchBlog(BlogSearchRequestDto requestDto) {
        NBlogSearchRequestDto transRequestDto = NBlogSearchRequestDto.of(requestDto);
        NBlogSearchResponseDto responseDto = send(transRequestDto);

        assert responseDto != null;
        return getNBlogResponse(responseDto);
    }

    private NBlogSearchResponseDto send(NBlogSearchRequestDto requestDto) {
        return this.webClient.get()
            .uri(uriBuilder -> uriBuilder.path("/v1/search/blog.json")
                .queryParam("query", requestDto.getQuery())
                .queryParam("display", requestDto.getDisplay())
                .queryParam("start", requestDto.getStart())
                .queryParam("sort", requestDto.getSort())
                .build())
            .header(NAVER_CLIENT_ID_HEADER, naverApiClientId)
            .header(NAVER_CLIENT_SECRET_HEADER, naverApiClientSecret)
            .retrieve()
            .bodyToMono(NBlogSearchResponseDto.class)
            .block();
    }

    private List<BlogSearchResponseDto> getNBlogResponse(NBlogSearchResponseDto responseDto) {
        return responseDto.getItems().stream().limit(MAX_PAGE_SIZE).map(BlogSearchResponseDto::new)
            .collect(Collectors.toList());
    }
}