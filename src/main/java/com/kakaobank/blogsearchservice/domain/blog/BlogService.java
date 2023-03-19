package com.kakaobank.blogsearchservice.domain.blog;

import com.kakaobank.blogsearchservice.connector.KakaoApiClient;
import com.kakaobank.blogsearchservice.domain.blog.dto.BlogRequestDto;
import com.kakaobank.blogsearchservice.domain.blog.dto.DocumentDto;
import com.kakaobank.blogsearchservice.domain.blog.dto.KaKaoBlogResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final KeywordRepository keywordRepository;
    private final KakaoApiClient kakaoApiClient;

    public List<Top10KeywordResponse> getTop10Keyword() {
        List<Top10KeywordResponse> responseList = new ArrayList<>();
        List<Top10Keyword> top10List = keywordRepository.findTop10();
        top10List.forEach(keyword -> {
            responseList.add(Top10KeywordResponse.builder()
                .keyword(keyword.getKeyword())
                .count(keyword.getCount())
                .build());
        });

        return responseList;
    }

    public KaKaoBlogResponse getBlogs(BlogRequestDto requestDto) {
        return kakaoApiClient.searchKakaoBlog(requestDto);
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    static class Top10KeywordResponse {

        private String keyword;
        private Long count;
    }

}
