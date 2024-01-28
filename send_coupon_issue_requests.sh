#!/bin/bash

# chmod +x send_coupon_issue_requests.sh
# ./send_coupon_issue_requests.sh

# API 엔드포인트를 변수로 설정
API_ENDPOINT="http://localhost:8080/coupons"

# 1부터 500까지 총 2번씩 반복하여 요청 보내기
for userId in {1..500}; do
    for i in {1..2}; do
        # 요청 바디에 해당하는 JSON 데이터 생성
        REQUEST_BODY="{\"userId\": $userId, \"couponEventId\": 1}"

        # curl 명령어로 POST 요청을 보내고 응답 받기
        curl -X POST \
             -H "Content-Type: application/json" \
             -d "$REQUEST_BODY" \
             "$API_ENDPOINT"
    done
done
