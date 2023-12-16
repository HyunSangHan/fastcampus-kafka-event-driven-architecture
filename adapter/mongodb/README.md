# MongoDB

### database 생성 및 사용
```sh
use campus
```

이후, 아래 명령어를 통해 현재 그 db를 사용하도록 잘 설정되었는지 확인 가능하다.

```sh
db
```

### collection 생성
```sh
db.createCollection("subscribingInboxPosts")
```

### collection에 index 생성
```sh
db.subscribingInboxPosts.createIndex( { "followerUserId": -1, "postCreatedAt": -1 } )
db.subscribingInboxPosts.createIndex( { "postId": -1 } )
```

이후, 아래 명령어를 통해 현재 그 인덱스가 잘 생성되었는지 확인 가능하다.

```sh
db.subscribingInboxPosts.getIndexes()
```

### Document 예시
```json
{
    "_id": "12_2",
    "postId": 12,
    "followerUserId": 2,
    "postCreatedAt": "2023-12-25T13:22:58.070Z",
    "addedAt": "2023-12-25T13:24:50.010Z",
    "read": false
}
```