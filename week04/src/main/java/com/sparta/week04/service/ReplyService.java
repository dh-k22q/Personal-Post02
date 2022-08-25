package com.sparta.week04.service;


import com.sparta.week04.domain.Reply;
import com.sparta.week04.dto.ReplyRequestDto;
import com.sparta.week04.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyService {

    private final ReplyRepository replyRepository;


    public Long editReply(Long id, ReplyRequestDto requestDto) {
        Reply reply = replyRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 없습니다.")
        );
        reply.editReply(requestDto);
        return id;
    }

}