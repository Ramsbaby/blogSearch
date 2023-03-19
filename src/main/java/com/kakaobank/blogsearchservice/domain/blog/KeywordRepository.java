package com.kakaobank.blogsearchservice.domain.blog;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {

    @Query(value = "select search_word as keyword, count(*) as count " +
        "from Keyword keyword " +
        "group by keyword.search_word limit 10 ", nativeQuery = true)
    List<Top10Keyword> findTop10();
}
