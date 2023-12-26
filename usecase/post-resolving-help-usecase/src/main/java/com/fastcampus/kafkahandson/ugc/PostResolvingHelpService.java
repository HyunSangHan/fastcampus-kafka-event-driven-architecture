package com.fastcampus.kafkahandson.ugc;

import com.fastcampus.kafkahandson.ugc.port.MetadataPort;
import com.fastcampus.kafkahandson.ugc.port.ResolvedPostCachePort;
import com.fastcampus.kafkahandson.ugc.port.PostPort;
import com.fastcampus.kafkahandson.ugc.post.model.Post;
import com.fastcampus.kafkahandson.ugc.post.model.ResolvedPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostResolvingHelpService implements PostResolvingHelpUsecase {

    private final PostPort postPort;
    private final MetadataPort metadataPort;
    private final ResolvedPostCachePort resolvedPostCachePort;

    @Override
    public ResolvedPost resolvePostById(Long postId) {
        ResolvedPost resolvedPost = resolvedPostCachePort.get(postId);
        if (resolvedPost != null) {
            return resolvedPost;
        }
        Post post = postPort.findById(postId);
        if (post != null) {
            resolvedPost = this.resolvePost(post);
        }
        return resolvedPost;
    }

    @Override
    public List<ResolvedPost> resolvePostsByIds(List<Long> postIds) { // TODO: 임시이므로 수정 필요
        return postIds.stream().map(this::resolvePostById).toList();
    }

    @Override
    public void resolvePostAndSave(Post post) {
        ResolvedPost resolvedPost = this.resolvePost(post);
        if (resolvedPost != null) {
            resolvedPostCachePort.set(resolvedPost);
        }
    }

    @Override
    public void deleteResolvedPost(Long postId) {
        resolvedPostCachePort.delete(postId);
    }

    private ResolvedPost resolvePost(Post post) {
        if (post == null) return null;
        ResolvedPost resolvedPost = null;
        String userName = metadataPort.getUserNameByUserId(post.getUserId());
        String categoryName = metadataPort.getCategoryNameByCategoryId(post.getCategoryId());
        if (userName != null && categoryName != null) {
            resolvedPost = ResolvedPost.generate(
                post,
                userName,
                categoryName
            );
            resolvedPostCachePort.set(resolvedPost);
        }
        return resolvedPost;
    }
}
