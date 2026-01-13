# Typing Practice Backend

영어 타자 연습 애플리케이션의 백엔드 서버입니다.
Spring Boot와 Oracle Database를 기반으로 구축되었습니다.

## 🛠 기술 스택 (Tech Stack)
- **Java**: 21
- **Framework**: Spring Boot 3.2.2
- **Database**: Oracle Database 11g/19c/21c (XE 권장)
- **Security**: Spring Security + JWT (JSON Web Token)
- **ORM**: Spring Data JPA
- **Build Tool**: Maven

## ⚙️ 사전 요구사항 (Prerequisites)
1. **Oracle Database**가 실행 중이어야 합니다.
   - 포트: 1521
   - 사용자 생성 및 권한 부여가 필요합니다.
     ```sql
     CREATE USER TYPING IDENTIFIED BY "1234";
     GRANT CONNECT, RESOURCE, DBA TO TYPING;
     ```
2. **Java 21** 이상이 설치되어 있어야 합니다.

## 🚀 실행 방법 (Getting Started)

### 설정 파일 (Configuration)
`src/main/resources/application.properties` 파일에서 DB 연결 정보를 확인할 수 있습니다.
```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
spring.datasource.username=TYPING
spring.datasource.password=1234
server.port=8081
```

### 실행 (Run)
프로젝트 루트(`TypingPractice/backend`)에서 다음 명령어를 실행합니다.

```bash
mvn spring-boot:run
```
또는 IDE(IntelliJ, Eclipse)에서 `TypingApplication.java`를 실행합니다.

## 📡 API 엔드포인트 (API Endpoints)

### 인증 (Auth)
- `POST /api/auth/signup`: 회원가입
- `POST /api/auth/login`: 로그인 (JWT 토큰 발급)

### 단어 관리 (Words)
- `GET /api/words`: 내 단어장 목록 조회
- `POST /api/words`: 단어 추가
- `DELETE /api/words/{id}`: 단어 삭제
