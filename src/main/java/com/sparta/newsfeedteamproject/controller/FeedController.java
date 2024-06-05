package com.sparta.newsfeedteamproject.controller;

import com.sparta.newsfeedteamproject.dto.BaseResDto;
import com.sparta.newsfeedteamproject.dto.feed.FeedReqDto;
import com.sparta.newsfeedteamproject.dto.feed.FeedResDto;
import com.sparta.newsfeedteamproject.security.UserDetailsImpl;
import com.sparta.newsfeedteamproject.service.FeedService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FeedController {

    private final FeedService feedService;

    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    @GetMapping("/feeds/all")
    public ResponseEntity<BaseResDto<List<FeedResDto>>> getAllFeeds() {
        BaseResDto<List<FeedResDto>> response =  feedService.getAllFeeds();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/feeds")
    public ResponseEntity<BaseResDto<FeedResDto>> createFeed(@Valid @RequestBody FeedReqDto reqDto,
                                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        BaseResDto<FeedResDto> response =  feedService.createFeed(reqDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



}
