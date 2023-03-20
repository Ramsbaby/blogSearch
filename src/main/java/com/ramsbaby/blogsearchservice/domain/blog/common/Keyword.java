package com.ramsbaby.blogsearchservice.domain.blog.common;

import com.ramsbaby.blogsearchservice.common.entity.CommonEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity(name="keyword")
@Builder
public class Keyword extends CommonEntity {
	@Id
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keyword_id;

	@Column(name="search_word")
    private String searchWord;
}
