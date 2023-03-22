package com.ramsbaby.blogsearch.domain.blog;

import com.ramsbaby.blogsearch.common.entity.CommonEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "keyword")
public class Keyword extends CommonEntity {

    @Id
    @Getter
    private String title;

    @Column
    @Getter
    @Setter
    private Integer useCount;

    @Version
    private Integer version;

    public Keyword(String keyword) {
        this.title = keyword;
        this.useCount = 0;
    }
}
