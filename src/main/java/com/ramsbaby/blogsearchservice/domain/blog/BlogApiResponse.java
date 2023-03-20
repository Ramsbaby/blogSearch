package com.ramsbaby.blogsearchservice.domain.blog;

import com.ramsbaby.blogsearchservice.domain.blog.dto.DocumentDto;
import com.ramsbaby.blogsearchservice.domain.blog.dto.MetaDto;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BlogApiResponse {
	private MetaDto meta;
	private List<DocumentDto> documents;
}
