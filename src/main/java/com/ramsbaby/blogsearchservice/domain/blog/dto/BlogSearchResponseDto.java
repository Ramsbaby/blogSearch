package com.ramsbaby.blogsearchservice.domain.blog.dto;

import com.ramsbaby.blogsearchservice.domain.blog.kakao.dto.KBlogSearchResponseDetailDto;
import com.ramsbaby.blogsearchservice.domain.blog.naver.dto.NBlogSearchResponseDetailDto;
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
