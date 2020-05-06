package kv.experiment.reactive.reactiveapi.repository;

import kv.experiment.reactive.reactiveapi.model.Post;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PostRepository extends ReactiveMongoRepository<Post,Long> {
}
