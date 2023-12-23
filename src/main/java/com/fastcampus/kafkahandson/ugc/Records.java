package com.fastcampus.kafkahandson.ugc;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Records {

    public static Map<Long, Category> categoryMap = createCategoryMap();
    public static Map<Long, User> userMap = createUserMap();
    public static List<Follow> allFollows = createFollowList();
    public static Map<Long, List<Long>> followingIdsByFollowerIdMap = allFollows.stream()
        .collect(Collectors.groupingBy(
            Follow::getFollowerId, // Key: Follower ID
            Collectors.mapping(Follow::getFollowingId, Collectors.toList()) // Value: List of Following IDs
        ));
    public static Map<Long, List<Long>> followerIdsByFollowingIdMap = allFollows.stream()
        .collect(Collectors.groupingBy(
            Follow::getFollowingId, // Key: Following ID
            Collectors.mapping(Follow::getFollowerId, Collectors.toList()) // Value: List of Follower IDs
        ));

    private static Map<Long, Category> createCategoryMap() {
        Map<Long, Category> map = new HashMap<>();
        map.put(1L, new Category(1L, "IT"));
        map.put(2L, new Category(2L, "Travel"));
        map.put(3L, new Category(3L, "Health"));
        return map;
    }

    private static Map<Long, User> createUserMap() {
        Map<Long, User> map = new HashMap<>();
        LocalDateTime now = LocalDateTime.now();
        for (long i = 1; i <= 30; i++) {
            map.put(i, new User(i, "user" + i, "user" + i + "@example.com", now, now, null));
        }
        return map;
    }

    private static List<Follow> createFollowList() {
        List<Follow> list = new ArrayList<>();
        list.add(new Follow(1L, 5L, 25L, LocalDateTime.now()));
        list.add(new Follow(2L, 3L, 6L, LocalDateTime.now()));
        list.add(new Follow(3L, 23L, 20L, LocalDateTime.now()));
        list.add(new Follow(4L, 30L, 11L, LocalDateTime.now()));
        list.add(new Follow(5L, 8L, 20L, LocalDateTime.now()));
        list.add(new Follow(6L, 8L, 9L, LocalDateTime.now()));
        list.add(new Follow(7L, 16L, 26L, LocalDateTime.now()));
        list.add(new Follow(8L, 23L, 8L, LocalDateTime.now()));
        list.add(new Follow(9L, 21L, 24L, LocalDateTime.now()));
        list.add(new Follow(10L, 12L, 19L, LocalDateTime.now()));
        list.add(new Follow(11L, 30L, 6L, LocalDateTime.now()));
        list.add(new Follow(12L, 20L, 19L, LocalDateTime.now()));
        list.add(new Follow(13L, 13L, 18L, LocalDateTime.now()));
        list.add(new Follow(14L, 28L, 24L, LocalDateTime.now()));
        list.add(new Follow(15L, 16L, 23L, LocalDateTime.now()));
        list.add(new Follow(16L, 29L, 20L, LocalDateTime.now()));
        list.add(new Follow(17L, 7L, 12L, LocalDateTime.now()));
        list.add(new Follow(18L, 23L, 28L, LocalDateTime.now()));
        list.add(new Follow(19L, 21L, 6L, LocalDateTime.now()));
        list.add(new Follow(20L, 10L, 5L, LocalDateTime.now()));
        list.add(new Follow(21L, 16L, 30L, LocalDateTime.now()));
        list.add(new Follow(22L, 4L, 6L, LocalDateTime.now()));
        list.add(new Follow(23L, 28L, 17L, LocalDateTime.now()));
        list.add(new Follow(24L, 28L, 9L, LocalDateTime.now()));
        list.add(new Follow(25L, 18L, 25L, LocalDateTime.now()));
        list.add(new Follow(26L, 7L, 28L, LocalDateTime.now()));
        list.add(new Follow(27L, 16L, 29L, LocalDateTime.now()));
        list.add(new Follow(28L, 9L, 5L, LocalDateTime.now()));
        list.add(new Follow(29L, 23L, 16L, LocalDateTime.now()));
        list.add(new Follow(30L, 17L, 12L, LocalDateTime.now()));
        list.add(new Follow(31L, 13L, 8L, LocalDateTime.now()));
        list.add(new Follow(32L, 28L, 13L, LocalDateTime.now()));
        list.add(new Follow(33L, 4L, 3L, LocalDateTime.now()));
        list.add(new Follow(34L, 9L, 23L, LocalDateTime.now()));
        list.add(new Follow(35L, 13L, 20L, LocalDateTime.now()));
        list.add(new Follow(36L, 3L, 21L, LocalDateTime.now()));
        list.add(new Follow(37L, 23L, 2L, LocalDateTime.now()));
        list.add(new Follow(38L, 29L, 25L, LocalDateTime.now()));
        list.add(new Follow(39L, 2L, 15L, LocalDateTime.now()));
        list.add(new Follow(40L, 11L, 30L, LocalDateTime.now()));
        list.add(new Follow(41L, 28L, 5L, LocalDateTime.now()));
        list.add(new Follow(42L, 26L, 4L, LocalDateTime.now()));
        list.add(new Follow(43L, 6L, 20L, LocalDateTime.now()));
        list.add(new Follow(44L, 30L, 8L, LocalDateTime.now()));
        list.add(new Follow(45L, 18L, 16L, LocalDateTime.now()));
        list.add(new Follow(46L, 19L, 26L, LocalDateTime.now()));
        list.add(new Follow(47L, 29L, 30L, LocalDateTime.now()));
        list.add(new Follow(48L, 22L, 19L, LocalDateTime.now()));
        list.add(new Follow(49L, 7L, 26L, LocalDateTime.now()));
        list.add(new Follow(50L, 18L, 6L, LocalDateTime.now()));
        list.add(new Follow(51L, 22L, 16L, LocalDateTime.now()));
        list.add(new Follow(52L, 16L, 13L, LocalDateTime.now()));
        list.add(new Follow(53L, 29L, 7L, LocalDateTime.now()));
        list.add(new Follow(54L, 16L, 9L, LocalDateTime.now()));
        list.add(new Follow(55L, 19L, 18L, LocalDateTime.now()));
        list.add(new Follow(56L, 20L, 10L, LocalDateTime.now()));
        list.add(new Follow(57L, 13L, 11L, LocalDateTime.now()));
        list.add(new Follow(58L, 29L, 26L, LocalDateTime.now()));
        list.add(new Follow(59L, 9L, 2L, LocalDateTime.now()));
        list.add(new Follow(60L, 1L, 28L, LocalDateTime.now()));
        list.add(new Follow(61L, 25L, 9L, LocalDateTime.now()));
        list.add(new Follow(62L, 26L, 2L, LocalDateTime.now()));
        list.add(new Follow(63L, 30L, 5L, LocalDateTime.now()));
        list.add(new Follow(64L, 29L, 27L, LocalDateTime.now()));
        list.add(new Follow(65L, 15L, 21L, LocalDateTime.now()));
        list.add(new Follow(66L, 3L, 22L, LocalDateTime.now()));
        list.add(new Follow(67L, 8L, 3L, LocalDateTime.now()));
        list.add(new Follow(68L, 3L, 11L, LocalDateTime.now()));
        list.add(new Follow(69L, 13L, 5L, LocalDateTime.now()));
        list.add(new Follow(70L, 1L, 17L, LocalDateTime.now()));
        list.add(new Follow(71L, 24L, 29L, LocalDateTime.now()));
        list.add(new Follow(72L, 28L, 23L, LocalDateTime.now()));
        list.add(new Follow(73L, 13L, 10L, LocalDateTime.now()));
        list.add(new Follow(74L, 10L, 17L, LocalDateTime.now()));
        list.add(new Follow(75L, 10L, 2L, LocalDateTime.now()));
        list.add(new Follow(76L, 6L, 5L, LocalDateTime.now()));
        list.add(new Follow(77L, 5L, 9L, LocalDateTime.now()));
        list.add(new Follow(78L, 27L, 9L, LocalDateTime.now()));
        list.add(new Follow(79L, 27L, 11L, LocalDateTime.now()));
        list.add(new Follow(80L, 3L, 13L, LocalDateTime.now()));
        list.add(new Follow(81L, 16L, 11L, LocalDateTime.now()));
        list.add(new Follow(82L, 6L, 2L, LocalDateTime.now()));
        list.add(new Follow(83L, 28L, 10L, LocalDateTime.now()));
        list.add(new Follow(84L, 3L, 29L, LocalDateTime.now()));
        list.add(new Follow(85L, 29L, 24L, LocalDateTime.now()));
        list.add(new Follow(86L, 27L, 21L, LocalDateTime.now()));
        list.add(new Follow(87L, 30L, 12L, LocalDateTime.now()));
        list.add(new Follow(88L, 20L, 17L, LocalDateTime.now()));
        list.add(new Follow(89L, 1L, 2L, LocalDateTime.now()));
        list.add(new Follow(90L, 7L, 15L, LocalDateTime.now()));
        list.add(new Follow(91L, 28L, 12L, LocalDateTime.now()));
        list.add(new Follow(92L, 4L, 22L, LocalDateTime.now()));
        list.add(new Follow(93L, 4L, 13L, LocalDateTime.now()));
        list.add(new Follow(94L, 14L, 10L, LocalDateTime.now()));
        list.add(new Follow(95L, 17L, 16L, LocalDateTime.now()));
        list.add(new Follow(96L, 6L, 23L, LocalDateTime.now()));
        list.add(new Follow(97L, 28L, 15L, LocalDateTime.now()));
        list.add(new Follow(98L, 23L, 3L, LocalDateTime.now()));
        list.add(new Follow(99L, 28L, 4L, LocalDateTime.now()));
        list.add(new Follow(100L, 23L, 11L, LocalDateTime.now()));
        return list;
    }

    @Data
    public static class Category {
        private final Long id;
        private final String name;
    }

    @Data
    public static class User {
        private final Long id;
        private final String name;
        private final String email;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;
        private final LocalDateTime deletedAt;
    }

    @Data
    public static class Follow {
        private final Long id;
        private final Long followerId;
        private final Long followingId;
        private final LocalDateTime createdAt;
    }
}
