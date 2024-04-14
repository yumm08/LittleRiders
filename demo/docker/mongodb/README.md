# 실행 방법

먼저 `docker engine` 을 실행시킵니다.

`cmd` 또는 `terminal` 에 아래 명령어를 실행해주세요.

```sh
docker-compose -f {프로젝트경로/demo/docker/mongodb/docker-compose.yml} up -d
```

mongodb 의 접속 정보는 다음과 같습니다.

-   host : localhost:27017
-   id : user
-   password : password

기타 자세한 정보는 [여기서](https://hub.docker.com/_/mongo) 확인해주세요.

# 웹으로 보기

[http://localhost:8081](http://localhost:8081) 로 들어가면 mysql workbench 처럼 볼 수 있습니다.

# 삭제 방법

`cmd` 또는 `terminal` 에 아래 명령어를 실행해주세요.

```sh
docker-compose -f {프로젝트경로/demo/docker/mongodb/docker-compose.yml} down

```
