package com.ramsbaby.blogsearchservice.domain.blog.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KaKaoBlogResponse {
    List<DocumentDto> documents;
}
