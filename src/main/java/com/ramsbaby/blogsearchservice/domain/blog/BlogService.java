package com.ramsbaby.blogsearchservice.domain.blog;

import com.ramsbaby.blogsearchservice.domain.blog.dto.BlogSearchRequestDto;
import com.ramsbaby.blogsearchservice.domain.blog.dto.BlogSearchResponseDto;
import com.ramsbaby.blogsearchservice.domain.blog.kakao.KakaoApiClient;
import com.ramsbaby.blogsearchservice.domain.blog.naver.NaverApiClient;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final KeywordRepository keywordRepository;
    private final KakaoApiClient kakaoApiClient;
    private final NaverApiClient naverApiClient;

    public List<Top10KeywordResponse> getTop10Keyword() {
        List<Top10KeywordResponse> responseList = new ArrayList<>();
        List<Top10Keyword> top10List = keywordRepository.findTop10();

        top10List.forEach(keyword -> responseList.add(Top10KeywordResponse.of(keyword)));

        return responseList;
    }

    public List<BlogSearchResponseDto> getBlogs(BlogSearchRequestDto requestDto) {
        try {
            return kakaoApiClient.searchBlog(requestDto);
        } catch (HttpServerErrorException e) {
            return naverApiClient.searchBlog(requestDto);
        }
    }

    @Transactional
    public void updateUseCount(String keyword) {
        Keyword keywordEntity = keywordRepository.findById(keyword)
            .orElse(new Keyword(keyword));

        keywordEntity.setUseCount(keywordEntity.getUseCount() + 1);
        keywordRepository.save(keywordEntity);
    }

    @Getter
    @Builder
    @AllArgsConstructor
    static class Top10KeywordResponse {

        private String keyword;
        private Long count;

        public static Top10KeywordResponse of(Top10Keyword keyword) {
            return Top10KeywordResponse.builder()
                .keyword(keyword.getTitle())
                .count(keyword.getCount())
                .build();
        }
    }


}
