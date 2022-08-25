package com.sparta.week04.controller;

import com.sparta.week04.domain.Post;
import com.sparta.week04.dto.PostDetailDto;
import com.sparta.week04.dto.PostListDto;
import com.sparta.week04.dto.PostRequestDto;
import com.sparta.week04.repository.PostRepository;
import com.sparta.week04.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;
    private final PostService postService;

    //등록
    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostRequestDto requestDto) {

        Post post = new Post(requestDto);
        return postRepository.save(post);
    }

    //전체 게시글 조회
    @GetMapping("/api/posts")
    public List<PostListDto> readPost() {
        return postService.searchAllDesc();
    }

    //게시글 조회
    @GetMapping("/api/posts/{id}")
    public PostDetailDto searchById(@PathVariable Long id) {
        return postService.searchById(id);
    }

    //게시글 삭제
    @DeleteMapping("/api/posts/{id}")
    public boolean deletePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        if (requestDto.getPassword().equals(postService.check(id))) {
            postRepository.deleteById(id);
            return true;
        } else {
            System.out.println("비밀번호를 확인해 주세요");
            return false;
        }
    }

    //게시글 수정
    @PutMapping("/api/posts/{id}")
    public boolean updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        if (requestDto.getPassword().equals(postService.check(id))) {
            postService.update(id, requestDto);
            return true;
        } else {
            System.out.println("비밀번호를 확인해 주세요");
            return false;
        }
    }
}