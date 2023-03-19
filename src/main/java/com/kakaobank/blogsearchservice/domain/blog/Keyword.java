package com.kakaobank.blogsearchservice.domain.blog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="keyword")
public class Keyword {
	@Id
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keyword_id;

	@Column(name="search_word")
    private String searchWord;

    @Builder
    public Keyword(Long keyword_id, String keyword) {
        this.keyword_id = keyword_id;
        this.searchWord = keyword;
    }
}
