package kv.experiment.reactive.reactiveapi.controller;

import kv.experiment.reactive.reactiveapi.model.Post;
import kv.experiment.reactive.reactiveapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/posts",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Post> getPosts(){
        return postService.getAllPosts().delayElements(Duration.of(60, ChronoUnit.SECONDS)).limitRate(1);
    }

    @PostMapping("/post")
    public Mono<Post> createPosts(@RequestBody Post post){
        return postService.createPosts(post);
    }
}
