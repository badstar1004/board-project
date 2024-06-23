package com.board.boardproject.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@ToString
@Table(indexes = {
    @Index(columnList = "content"),
    @Index(columnList = "createAt"),
    @Index(columnList = "createBy")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class ArticleComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // 아이디

    @Setter
    @ManyToOne(optional = false)
    private Article article;            // 게시글
    @Setter
    @Column(nullable = false, length = 500)
    private String content;             // 내용

    // 자동설정 -> jpa auditing -> 실시간으로 데이터를 넣어줌
    @CreatedDate
    private LocalDateTime createdAt;     // 생성일시

    @CreatedBy
    @Column(length = 100)
    private String createdBy;            // 생성자

    @LastModifiedDate
    private LocalDateTime updatedAt;     // 수정일시

    @LastModifiedBy
    @Column(length = 100)
    private String updatedBy;            // 수정자


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
