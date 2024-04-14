# 실행 방법

먼저 `docker engine` 을 실행시킵니다.

`cmd` 또는 `terminal` 에 아래 명령어를 실행해주세요.

```sh
docker run --name redis -p 6379:6379 -d redis
```

redis 의 접속 정보는 다음과 같습니다.

-   host : localhost:6379

기타 자세한 정보는 [여기서](https://hub.docker.com/_/redis) 확인해주세요.

# 삭제 방법

`cmd` 또는 `terminal` 에 아래 명령어를 실행해주세요.

```sh
docker stop redis
docker rm redis
```
