package com.board.boardproject.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@Getter
@ToString
@Table(indexes = {
    @Index(columnList = "title"),
    @Index(columnList = "hashtag"),
    @Index(columnList = "createdAt"),
    @Index(columnList = "createdBy")
})
@Entity
public class Article extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 각 속성별로 setter를 할 수 있음 -> 특정 사용자가 일부러 수정하지 못하게
    @Setter
    @Column(nullable = false)
    private String title;               // 제목
    @Setter
    @Column(nullable = false, length = 10000)
    private String content;             // 내용

    @Setter
    private String hashtag;             // 해시태그

    @OrderBy("id")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @Exclude        // Article 에서 toString() 끊어주는게 좋음
    private final Set<ArticleComment> articleCommentSet = new HashSet<>();



    // Hibernate 구현체를 사용하는 경우 기본 생성자를 가지고 있어야함
    protected Article() {}

    // Factory method 를 통해 제공
    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static Article of(String title, String content, String hashtag) {
        return new Article(title, content, hashtag);
    }

    // 동일성, 동등성 검사 (equals hashcode) / 기본 제공되는건 모두 다 비교함 -> 다른 방법으로 구현
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        // 따로 캐스팅 없이 바로 변수 사용 가능 (패턴 매칭)
        if(!(o instanceof Article article)) {
            return false;
        }

        return id != null && id.equals(article.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
