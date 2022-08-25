package com.sparta.week04.controller;


import com.sparta.week04.domain.Post;
import com.sparta.week04.domain.Reply;
import com.sparta.week04.dto.ReplyRequestDto;
import com.sparta.week04.repository.PostRepository;
import com.sparta.week04.repository.ReplyRepository;
import com.sparta.week04.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;

    //댓글 목록조회
    @GetMapping("/replies/{postId}")
    public List<Reply> showReply(@PathVariable Long postId) {
        return replyRepository.findAllByPostIdOrderByModifiedAtDesc(postId);
    }

    //댓글 작성
    @PostMapping("/replies/{postId}")
    public Reply writeReply(@PathVariable Long postId, @RequestBody ReplyRequestDto requestDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new IllegalArgumentException("아이디가 없습니다.")
        );
        //post자체를 넣어야함
        Reply reply = new Reply(post, requestDto);
        return replyRepository.save(reply);
    }

    //댓글 수정
    @PutMapping("/replies/{replyId}")
    public Long editReply(@PathVariable Long replyId, @RequestBody ReplyRequestDto requestDto) {
        return replyService.editReply(replyId, requestDto);
    }

    //댓글 삭제
    @DeleteMapping("/replies/{replyId}")
    public void deleteReply(@PathVariable Long replyId) {
        replyRepository.deleteById(replyId);
    }
}