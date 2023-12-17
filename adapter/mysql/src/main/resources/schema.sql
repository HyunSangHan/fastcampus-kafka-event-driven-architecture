-- [Chapter 2] post 테이블 생성
CREATE TABLE post
(
    id          INT AUTO_INCREMENT PRIMARY KEY COMMENT '콘텐츠의 id',
    title       VARCHAR(100) NOT NULL COMMENT '콘텐츠의 제목',
    content     TEXT COMMENT '콘텐츠의 내용',
    user_id     INT COMMENT '콘텐츠의 작성자 user id',
    category_id INT COMMENT '콘텐츠의 카테고리 id',
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '콘텐츠의 생성일시',
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '콘텐츠의 수정일시',
    deleted_at  TIMESTAMP COMMENT '콘텐츠 삭제일시(미삭제 시 NULL)'
);

-- [Chapter 3] coupon_event 테이블 생성
CREATE TABLE coupon_event
(
    id           INT AUTO_INCREMENT PRIMARY KEY COMMENT 'coupon event의 id',
    display_name VARCHAR(100) NOT NULL COMMENT '쿠폰 디스플레이용 이름',
    expires_at   TIMESTAMP    NOT NULL COMMENT '쿠폰 만료기한',
    issue_limit  BIGINT       NOT NULL COMMENT '쿠폰 발급 제한 개수'
);

-- [Chapter 3] coupon_event 초기 데이터 삽입
INSERT INTO coupon_event (id, display_name, expires_at, issue_limit)
VALUES (1, '[선착순 발급] 무료 광고 1회 쿠폰 (2024년 한정)', '2025-01-01 00:00:00', 10);

-- [Chapter 3] coupon 테이블 생성
CREATE TABLE coupon
(
    id              INT AUTO_INCREMENT PRIMARY KEY COMMENT 'coupon의 id',
    coupon_event_id INT       NOT NULL COMMENT 'coupon event의 id',
    user_id         INT       NOT NULL COMMENT 'coupon을 발급받은 user id',
    issued_at       TIMESTAMP NOT NULL COMMENT 'coupon 발급 일시',
    used_at         TIMESTAMP COMMENT 'coupon 사용 일시',
    UNIQUE KEY unique_user_id_coupon_event_id (user_id, coupon_event_id),
    FOREIGN KEY (coupon_event_id) REFERENCES coupon_event (id)
);
