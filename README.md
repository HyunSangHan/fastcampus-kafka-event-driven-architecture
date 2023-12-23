Fast Campus [한번에 끝내는 Kafka Ecosystem](https://fastcampus.co.kr/dev_online_newkafka) 강의의 Part 2-2. Event Driven Architecture 실습 교육용 자료입니다.

# 메타정보 API 서버

### 1. gradle build
```sh
./gradlew clean build
```

### 2. docker login
```sh
docker login
```

### 3. docker build
```sh
docker build --build-arg JAR_FILE='build/libs/*.jar' -t gustkd3/fastcampus-kafka-2-2-ugc .
```

만약 zsh이라면 안될 수가 있는데, 아래와 같이 실행하면 해결된다.
```zsh
set -o noglob
docker build --build-arg JAR_FILE='build/libs/*.jar' -t gustkd3/fastcampus-kafka-2-2-ugc .
set +o noglob
```

### 4. docker push
```
docker push gustkd3/fastcampus-kafka-2-2-ugc
```

이렇게 하면 docker hub에 이미지가 올라간다.
이제, 사용자들이 이를 docker pull해서 사용할 수 있게 된다.
