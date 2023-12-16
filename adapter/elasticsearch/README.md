# Elasticsearch

Elasticsearch는 기본적으로 REST API를 제공한다. 따라서, HTTP Request를 통해 데이터를 주고 받을 수 있다.
하지만 Elastic Kibana의 Dev Tools(http://localhost:5601/app/dev_tools#/console)에 접속하면 좀 더 쉽게 query가 가능하다.

### index 생성 및 사용
```sh
PUT post-1
{
  "mappings" : {
    "properties" : {
      "id" : {
        "type" : "long"
      },
      "title" : {
        "type" : "text",
        "analyzer": "english"
      },
      "content" : {
        "type" : "text",
        "analyzer": "english"
      },
      "categoryName" : {
        "type" : "keyword"
      },
      "tags" : {
        "type" : "keyword"
      },
      "indexedAt": {
        "type": "date",
        "format": "date_optional_time||epoch_millis"
      }
    }
  },
  "settings" : {
    "index" : {
      "number_of_shards" : 1,
      "number_of_replicas" : 0
    }
  }
}
```

이후, 아래 명령어를 통해 현재 그 index가 생성되었는지 확인 가능하다.

```sh
GET post-1
```

검색은 아래와 같이 진행한다.

```sh
GET post-1/_search
{
  "query": {
    "bool": {
      "should": [
        { "match": { "title": { "query": "검색어" } } },
        { "match": { "content": { "query": "검색어" } } },
        { "term": { "categoryName": "검색어" } },
        { "term": { "tags": "검색어" } }
      ]
    }
  },
  "from": 0,
  "size": 5
}
```

### Document 예시
```json
{
  "_class" : "com.fastcampus.kafkahandson.ugc.postsearch.PostDocument",
  "id" : 1,
  "title" : "Reps and Muscle Size",
  "content" : "To increase muscle size, it is considered most ideal to exercise with the maximum weight that allows 8 to 12 repetitions per set.",
  "categoryName" : "Health",
  "tags" : [
    "muscle", 
    "weight",
    "repetitions"
  ],
  "indexedAt" : "2024-01-05T00:49:48.775"
}
```

### Troubleshooting
```sh
curl -XPUT -H "Content-Type: application/json" http://localhost:9200/_cluster/settings -d '{ "transient": { "cluster.routing.allocation.disk.threshold_enabled": false } }'
curl -XPUT -H "Content-Type: application/json" http://localhost:9200/_all/_settings -d '{"index.blocks.read_only_allow_delete": null}'
```

### Index Template
```sh
POST _template/access-log-index-template
{
  "index_patterns": [
    "access-log-*"
  ],
  "aliases": {
    "access-log": {}
  },
  "mappings": {
    "properties": {
      "@timestamp": {
        "type": "date"
      },
      "@version": {
        "type": "keyword"
      },
      "agent": {
        "properties": {
          "ephemeral_id": {
            "type": "keyword"
          },
          "hostname": {
            "type": "keyword"
          },
          "id": {
            "type": "keyword"
          },
          "name": {
            "type": "keyword"
          },
          "type": {
            "type": "keyword"
          },
          "version": {
            "type": "keyword"
          }
        }
      },
      "bytes": {
        "type": "keyword"
      },
      "client_ip": {
        "type": "keyword"
      },
      "duration": {
        "type": "keyword"
      },
      "ecs": {
        "properties": {
          "version": {
            "type": "keyword"
          }
        }
      },
      "host": {
        "properties": {
          "name": {
            "type": "keyword"
          }
        }
      },
      "http_method": {
        "type": "keyword"
      },
      "http_version": {
        "type": "keyword"
      },
      "input": {
        "properties": {
          "type": {
            "type": "keyword"
          }
        }
      },
      "log": {
        "properties": {
          "file": {
            "properties": {
              "path": {
                "type": "keyword"
              }
            }
          },
          "offset": {
            "type": "long"
          }
        }
      },
      "message": {
        "type": "text"
      },
      "port": {
        "type": "keyword"
      },
      "referrer": {
        "type": "text"
      },
      "request": {
        "type": "text"
      },
      "response_code": {
        "type": "keyword"
      },
      "timestamp": {
        "type": "date",
        "format": "dd/MMM/yyyy:HH:mm:ss Z"
      },
      "type": {
        "type": "keyword"
      },
      "user_agent": {
        "type": "text"
      }
    }
  }
}
```