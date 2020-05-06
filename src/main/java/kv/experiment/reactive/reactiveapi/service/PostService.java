package kv.experiment.reactive.reactiveapi.service;

import kv.experiment.reactive.reactiveapi.model.Post;
import kv.experiment.reactive.reactiveapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;


    public Flux<Post> getAllPosts() {

        return postRepository.findAll();
    }


    public Mono<Post> createPosts(Post post) {
        return postRepository.save(post).map(r->{
           return r;
        });
    }


    public Mono<Post> getPostById(Long postId) {
        return postRepository.findById(postId);

    }


    public Mono<Post> updatePost( Long postId, Post post) {
        return postRepository.findById(postId)
                .flatMap(existingPost -> {
                    existingPost.setContent(post.getContent());
                    return postRepository.save(existingPost);
                });
    }


    public Mono<Void> deletePost( Long postId) {

        return postRepository.findById(postId)
                .flatMap(existingPost ->
                        postRepository.delete(existingPost)
                );

    }

    // Posts are Sent to the client as Server Sent Events

    public Flux<Post> streamAllPosts() {
        return postRepository.findAll();
    }
}
