package com.ramsbaby.blogsearchservice.domain.blog.commonDto;

import com.ramsbaby.blogsearchservice.domain.blog.kakao.dto.KBlogSearchResponseDetailDto;
import com.ramsbaby.blogsearchservice.domain.blog.kakao.dto.KBlogSearchResponseDto;
import com.ramsbaby.blogsearchservice.domain.blog.naver.dto.NBlogSearchResponseDetailDto;
import com.ramsbaby.blogsearchservice.domain.blog.naver.dto.NBlogSearchResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogSearchResponseDto {

    String title;
    String content;
    String name;

    public BlogSearchResponseDto(String title, String contents, String name) {
        this.title = title;
        this.content = contents;
        this.name = name;
    }

    public BlogSearchResponseDto(KBlogSearchResponseDetailDto kBlogSearchResponseDto) {
        this(kBlogSearchResponseDto.getTitle(),
            kBlogSearchResponseDto.getContents(),
            kBlogSearchResponseDto.getBlogname());
    }

    public BlogSearchResponseDto(NBlogSearchResponseDetailDto nBlogSearchResponseDto) {
        this(nBlogSearchResponseDto.getTitle(),
            nBlogSearchResponseDto.getDescription(),
            nBlogSearchResponseDto.getBloggername());
    }

}
