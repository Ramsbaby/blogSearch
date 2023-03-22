package com.ramsbaby.blogsearch.domain.blog;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, String> {

    @Query(value = "select title, use_count as count " +
        "from Keyword keyword "
        + "order by keyword.use_count desc limit 10 ", nativeQuery = true)
    List<Top10Keyword> findTop10();
}
