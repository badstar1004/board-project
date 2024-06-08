package com.board.boardproject.domain;

import java.time.LocalDateTime;

public class Article {

    private Long id;
    private String title;               // 제목
    private String content;             // 내용
    private String hashtag;             // 해시태그

    private LocalDateTime createAt;     // 생성일시
    private String createBy;            // 생성자
    private LocalDateTime updateAt;     // 수정일시
    private String updateBy;            // 수정자

}
