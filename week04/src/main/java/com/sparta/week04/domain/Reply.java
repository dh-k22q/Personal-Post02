package com.sparta.week04.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.week04.dto.ReplyRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@Setter
@NoArgsConstructor
public class Reply extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contents;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Reply(Post post, ReplyRequestDto requestDto) {
        this.post = post;
        this.contents = requestDto.getContents();
    }

    public void editReply(ReplyRequestDto requestDto) {
        this.contents = requestDto.getContents();
    }
}