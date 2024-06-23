package com.board.boardproject.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Table(indexes = {
    @Index(columnList = "content"),
    @Index(columnList = "createdAt"),
    @Index(columnList = "createdBy")
})
@Entity
public class ArticleComment extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // 아이디

    @Setter
    @ManyToOne(optional = false)
    private Article article;            // 게시글
    @Setter
    @Column(nullable = false, length = 500)
    private String content;             // 내용



    protected ArticleComment() {}

    private ArticleComment(Article article, String content) {
        this.article = article;
        this.content = content;
    }

    public ArticleComment of(Article article, String content) {
        return new ArticleComment(article, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        // 따로 캐스팅 없이 바로 변수 사용 가능 (패턴 매칭)
        if(!(o instanceof ArticleComment articleComment)) {
            return false;
        }

        return id != null && id.equals(articleComment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
