package com.sparta.week04.service;


import com.sparta.week04.domain.Post;
import com.sparta.week04.dto.PostDetailDto;
import com.sparta.week04.dto.PostListDto;
import com.sparta.week04.dto.PostRequestDto;
import com.sparta.week04.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    //업데이트
    @Transactional
    public Long update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 없습니다.")
        );
        post.update(requestDto);
        return id;
    }

    //전체 게시글 조회
    @Transactional()
    public List<PostListDto> searchAllDesc() {
        return postRepository.findAllByOrderByModifiedAtDesc().stream()
                .map(PostListDto::new)
                .collect(Collectors.toList());
    }

    //게시글 조회
    @Transactional
    public PostDetailDto searchById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 없습니다.")
        );
        return new PostDetailDto(post);
    }
    //비밀번호 확인
    @Transactional
    public String check(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 없습니다.")
        );
        return post.getPassword();
    }
}