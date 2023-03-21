package com.ramsbaby.blogsearchservice.domain.blog;

import com.ramsbaby.blogsearchservice.domain.blog.commonDto.BlogSearchRequestDto;
import com.ramsbaby.blogsearchservice.domain.blog.commonDto.BlogSearchResponseDto;
import com.ramsbaby.blogsearchservice.domain.blog.kakao.KakaoApiClient;
import com.ramsbaby.blogsearchservice.domain.blog.naver.NaverApiClient;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
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
    private final NaverApiClient naverApiClient;

    public List<Top10KeywordResponse> getTop10Keyword() {
        List<Top10KeywordResponse> responseList = new ArrayList<>();
        List<Top10Keyword> top10List = keywordRepository.findTop10();

        top10List.forEach(keyword -> {
            responseList.add(Top10KeywordResponse.builder()
                .keyword(keyword.getTitle())
                .count(keyword.getCount())
                .build());
        });

        return responseList;
    }

    public List<BlogSearchResponseDto> getBlogs(BlogSearchRequestDto requestDto) {
        try {
            return kakaoApiClient.searchBlog(requestDto);
        } catch (Exception e) {
            if ("1001|KAKAO SERVER ERROR".equals(e.getMessage())) {
                return naverApiClient.searchBlog(requestDto);
            }
        }

        return null;
    }

    @Transactional
    public void updateUseCount(String keyword) {
        Keyword keywordEntity = keywordRepository.findById(keyword)
            .orElse(new Keyword(keyword));

        keywordEntity.setUseCount(keywordEntity.getUseCount() + 1);
        keywordRepository.save(keywordEntity);
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
