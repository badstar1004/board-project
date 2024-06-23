package com.board.boardproject.repository;

import com.board.boardproject.config.JpaConfig;
import com.board.boardproject.domain.Article;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.*;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)    // config 를 제대로 읽지 못함 import 해줘야함
@DataJpaTest        // SpringExtension junit5 를 위해 만들어짐 Autowired 기능이 들어가있음 // 메서드들이 자동으로 트랜잭션이 걸려있음
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;


    // 생성자 주입 패턴
    public JpaRepositoryTest(@Autowired ArticleRepository articleRepository,
        @Autowired ArticleCommentRepository articleCommentRepository) {
        
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {
        // given

        // when
        List<Article> articles = articleRepository.findAll();

        // then
        assertThat(articles)
            .isNotNull()
            .hasSize(123);

    }

    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorksFine() {
        // given
        long previousCount = articleRepository.count();

        // when
        Article savedArticle =
            articleRepository.save(Article.of("new article", "new content", "#spring"));

        // then
        assertThat(articleRepository.count()).isEqualTo(previousCount + 1);

    }

    @DisplayName("update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {
        // given
        Article article = articleRepository.findById(1L).orElseThrow();
        String updateHashTag = "#springboot";
        article.setHashtag(updateHashTag);

        // when
        Article savedArticle =
            articleRepository.saveAndFlush(article);        // 자체적으로 적용해야함, 다만 롤백되기 때문에 실제로 반영은 안됨

        // then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updateHashTag);

    }

    @DisplayName("delete 테스트")
    @Test
    void givenTestData_whenDeleting_thenWorksFine() {
        // given
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousArticleCount = articleRepository.count();
        long previousArticleCommentCount = articleCommentRepository.count();
        int deleteCommentsCount = article.getArticleCommentSet().size();

        // when
        articleRepository.delete(article);

        // then
        assertThat(articleRepository.count())
            .isEqualTo(previousArticleCount - 1);

        assertThat(articleCommentRepository.count())
            .isEqualTo(previousArticleCommentCount - deleteCommentsCount);

    }
}