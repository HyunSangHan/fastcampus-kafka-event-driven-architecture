package com.fastcampus.kafkahandson.ugc.post;

import com.fastcampus.kafkahandson.ugc.port.PostPort;
import com.fastcampus.kafkahandson.ugc.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PostAdapter implements PostPort {

    private final PostJpaRepository postJpaRepository;

    @Override
    public Post save(Post post) {
        PostEntity postEntity = postJpaRepository.save(PostEntityConverter.toEntity(post));
        return PostEntityConverter.toModel(postEntity);
    }

    @Override
    public Post findById(Long id) {
        PostEntity postEntity = postJpaRepository.findById(id).orElse(null);
        if (postEntity == null) return null;
        return PostEntityConverter.toModel(postEntity);
    }

    @Override
    public List<Post> listByIds(List<Long> ids) {
        List<PostEntity> postEntities = postJpaRepository.findAllById(ids);
        return postEntities.stream().map(PostEntityConverter::toModel).toList();
    }
}
