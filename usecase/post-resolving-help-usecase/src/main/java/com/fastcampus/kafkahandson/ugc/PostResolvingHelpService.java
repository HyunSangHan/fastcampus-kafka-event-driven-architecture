package com.fastcampus.kafkahandson.ugc;

import com.fastcampus.kafkahandson.ugc.port.MetadataPort;
import com.fastcampus.kafkahandson.ugc.port.ResolvedPostCachePort;
import com.fastcampus.kafkahandson.ugc.port.PostPort;
import com.fastcampus.kafkahandson.ugc.post.model.Post;
import com.fastcampus.kafkahandson.ugc.post.model.ResolvedPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    public List<ResolvedPost> resolvePostsByIds(List<Long> postIds) {
        if (postIds == null || postIds.isEmpty()) {
            return List.of();
        }
        List<ResolvedPost> resolvedPostCaches = new ArrayList<>();
        resolvedPostCaches.addAll(resolvedPostCachePort.multiGet(postIds));
        List<Long> missingPostIds = postIds.stream()
            .filter(postId -> resolvedPostCaches.stream().noneMatch(resolvedPost -> resolvedPost.getId().equals(postId)))
            .toList();

        List<Post> missingPosts = postPort.listByIds(missingPostIds);
        List<ResolvedPost> missingResolvedPosts = missingPosts.stream()
            .map(this::resolvePost)
            .filter(Objects::nonNull)
            .toList();
        resolvedPostCaches.addAll(missingResolvedPosts);

        // postIds를 기준으로 resolvedPostCaches를 매핑하는 Map 생성
        Map<Long, ResolvedPost> resolvedPostMap = resolvedPostCaches.stream()
            .collect(Collectors.toMap(ResolvedPost::getId, Function.identity()));

        return postIds.stream()
            .map(resolvedPostMap::get)
            .filter(Objects::nonNull)
            .toList();
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
