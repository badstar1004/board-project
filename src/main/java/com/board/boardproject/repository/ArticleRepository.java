package com.board.boardproject.repository;

import com.board.boardproject.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

// @Repository 어노테이션을 안붙여도 정상 동작함
public interface ArticleRepository extends JpaRepository<Article, Long> {

}