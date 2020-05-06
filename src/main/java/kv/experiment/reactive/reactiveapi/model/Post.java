package kv.experiment.reactive.reactiveapi.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Document(collection = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {


    private Long post_id;

    private String content;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date created;

}
