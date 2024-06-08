package com.board.boardproject.domain;

import java.time.LocalDateTime;

public class ArticleComment {

    private Long id;                    // 아이디
    private Article article;            // 게시글
    private String content;             // 내용

    private LocalDateTime createAt;     // 생성일시
    private String createBy;            // 생성자
    private LocalDateTime updateAt;     // 수정일시
    private String updateBy;            // 수정자

}
