![Java_11](https://img.shields.io/badge/java-v1.11-red?logo=java)
![Spring_Boot](https://img.shields.io/badge/Spring_Boot-v2.7.5-green.svg?logo=spring)

# 블로그검색 서비스 API

## Feature
* Java 11
* Sprint boot 2.7.5
* JPA, h2
* gradle 7.3

## 프로젝트 소개
블로그 검색, 검색된 상위 10개 키워드 조회 기능 제공

### 기능
1. 블로그 검색
2. 인기 키워드 조회

## JAR 파일 다운로드 주소
```
https://github.com/Ramsbaby/blogSearch/blob/main/service-0.0.1-SNAPSHOT.jar
```

## Getting Started
* build
```cmd
./gradlew clean bootJar
```
* run(default port:8080)
```cmd
java -jar service/build/libs/service-0.0.1-SNAPSHOT.jar
```

### 핵심문제 해결전략
```
  1. AOP를 이용하여 키워드 히스토리 저장, 블로그 조회 리퀘스트 요청 유효성 검사.
  2. 카카오API를 이용하여 조회를 진행했으나, HttpServerErrorException가 발생하면 네이버API 조회.
  3. BlogSearchApi 인터페이스 추상화를 통해 다른 플랫폼 API 가 추가 되더라도 확장가능 용이.
  4. WebClient 를 이용하여 서버 간 통신. 추후 WebFlux 도입 시 용이한 전환 가능할 것으로 예상.
  5. 낙관적락을 이용하여 키워드 조회에 대한 동시성 요청이 많아 질 경우를 대비.
    5-1. 낙관적락이 실패할 경우 한 번의 재시도를 더 해줌으로써 요청이 많아졌을 경우 실패 대비.
    5-1. 하지만 이조차도 완벽하지는 않으니 유입되는 트래픽양을 고려하여 비관적락으로 전환할 지 트레이드오프 고려해야 할 것으로 예상. 
```

