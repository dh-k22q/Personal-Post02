package com.sparta.week04.dto;

import com.sparta.week04.domain.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostDetailDto {

    private Long id;
    private LocalDateTime starDate;
    private LocalDateTime modifiedAt;
    private String title;
    private String name;
    private String contents;

    public PostDetailDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.name = post.getName();
        this.starDate = post.getStarDate();
        this.modifiedAt = post.getModifiedAt();
        this.contents = post.getContents();

    }

}
