package com.ramsbaby.blogsearch.domain.blog;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BlogServiceTest {
    @Autowired
    private BlogService blogService;

    @Autowired
    private KeywordRepository keywordRepository;

    @Test
    @DisplayName("키워드 사용 횟수 확인")
    void 키워드_사용_횟수_확인() {
        String keyword = "https://test.com";

        blogService.updateUseCount(keyword);

        Optional<Keyword> keywordEntity = keywordRepository.findById(keyword);

        assertThat(keywordEntity).isPresent();

        assertThat(keywordEntity.get().getUseCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("기존 키워드 사용 횟수 업데이트")
    void updateKeywordUseCountTest() {
        String keyword = "카페";

        blogService.updateUseCount(keyword);
        blogService.updateUseCount(keyword);

        Optional<Keyword> keywordEntity = keywordRepository.findById(keyword);

        assertThat(keywordEntity).isPresent();

        assertThat(keywordEntity.get().getUseCount()).isEqualTo(2);
    }
}
