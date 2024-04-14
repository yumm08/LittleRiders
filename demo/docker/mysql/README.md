# 실행 방법

먼저 `docker engine` 을 실행시킵니다.

`cmd` 또는 `terminal` 에 아래 명령어를 실행해주세요.

```sh
docker run --name mysql -p 3306:3306 -e MYSQL_USER=user -e MYSQL_PASSWORD=password -e MYSQL_ROOT_PASSWORD=rootpassword -e MYSQL_DATABASE=mydatabase -d  mysql
```

mysql workbench 의 접속 정보는 다음과 같습니다.

-   host : localhost:3306
-   id : user
-   password : password
-   database : mydatabase

기타 자세한 정보는 [여기서](https://hub.docker.com/_/mysql) 확인해주세요.
