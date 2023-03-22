package com.ramsbaby.blogsearch.domain.blog;

import static org.assertj.core.api.Assertions.assertThat;

import com.ramsbaby.blogsearch.domain.blog.BlogService.Top10KeywordResponse;
import com.ramsbaby.blogsearch.domain.blog.dto.BlogSearchRequestDto;
import com.ramsbaby.blogsearch.domain.blog.dto.BlogSearchResponseDto;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class BlogControllerTest {

    @Autowired
    private BlogService blogService;

    private BlogSearchRequestDto blogSearchRequestDto;

    @BeforeEach
    void setUp() {
        String query = "keyword";
        String sort = "accuracy";
        Integer page = 1;
        Integer size = 10;
        blogSearchRequestDto = BlogSearchRequestDto.builder().query(query).sort(sort).page(page).size(size).build();
    }

    @Test
    @DisplayName("블로그 검색")
    @Order(1)
    void 블로그_검색() throws Exception {
        // given

        // when
        List<BlogSearchResponseDto> result = blogService.getBlogs(blogSearchRequestDto);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(10);
        assertThat(result.get(0).getTitle()).isNotEmpty();
    }


    @Test
    @DisplayName("2. TOP10 인기 키워드 조회")
    @Order(2)
    void TOP10_인기_키워드_조회(){
        // given
        blogService.getBlogs(blogSearchRequestDto);

        // when
        List<Top10KeywordResponse> result = blogService.getTop10Keyword();

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getKeyword()).isEqualTo(blogSearchRequestDto.getQuery());
        assertThat(result.get(0).getCount()).isEqualTo(1);
    }
}
