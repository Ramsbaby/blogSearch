package com.ramsbaby.blogsearch.domain.blog;

import static org.assertj.core.api.Assertions.assertThat;

import com.ramsbaby.blogsearch.domain.blog.BlogService.Top10KeywordResponse;
import com.ramsbaby.blogsearch.domain.blog.dto.BlogSearchRequestDto;
import com.ramsbaby.blogsearch.domain.blog.dto.BlogSearchResponseDto;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BlogServiceConcurrencyTest {
    @Autowired
    private BlogService blogService;

    private BlogSearchRequestDto blogSearchRequestDto;

    @BeforeEach
    void setUp() {
        String query = "test";
        String sort = "accuracy";
        Integer page = 1;
        Integer size = 10;
        blogSearchRequestDto = BlogSearchRequestDto.builder().query(query).sort(sort).page(page).size(size).build();
    }

    @Test
    @DisplayName("동시성테스트")
    void 동시성_테스트() throws InterruptedException, ExecutionException {
        // given
        blogService.getBlogs(blogSearchRequestDto);

        List<Integer> list = new ArrayList();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        int numberOfThreads = 2;
        ForkJoinPool forkjoinPool = new ForkJoinPool(numberOfThreads);
        forkjoinPool.submit(() -> list.parallelStream().forEach(item -> {
            blogService.getBlogs(blogSearchRequestDto);
        })).get();
        forkjoinPool.shutdown(); // after jdk 11

        // when
        List<Top10KeywordResponse> result = blogService.getTop10Keyword();

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getCount()).isEqualTo(10);
    }
}
