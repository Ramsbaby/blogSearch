package com.ramsbaby.blogsearchservice.domain.blog.kakao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KBlogSearchResponseDetailDto {

    String title;
    String contents;
    String url;
    String blogname;

    public static KBlogSearchResponseDetailDto of(String title, String contents, String url, String blogname) {
        return KBlogSearchResponseDetailDto.builder()
            .title(title)
            .contents(contents)
            .url(url)
            .blogname(blogname)
            .build();
    }
}
