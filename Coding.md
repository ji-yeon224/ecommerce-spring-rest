# 코딩 순서

### 프로젝트 생성시 추가할 모듈(Spring Boot Version : 2.4.5)
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- mysql-connector-java
- lombok
- spring-boot-starter-hateoas
- spring-boot-starter-thymeleaf

- validation-api

#### Rest API 테스트 후, Security 기능 추가하는 순서


#### 1) Entity Package(AbstractEntity, Category, Product) 작성
#### 2) Repository(CategoryRepository, ProductRepository) 작성
#### 3) application.properties, data.sql(user, role관련 statement는 제외) 작성

`프로젝트를 빌드한 후, DB에 초기 데이터가 잘 들어가있는지 확인(intellij의 databases를 통해 확인)`

#### 4) Service Package 작성

#### 5) api(RepresentationModel, assembler, controller) package, exception package , util package 작성

`postman을 사용하여 API 테스트`

#### 6) pom.xml에 security 관련 모듈 추가
spring-boot-starter-security

thymeleaf-extras-springsecurity5

#### 7) Config 패키지,  entity(User, Role) 패키지,  repository(UserRepository, RoleRepository)  패키지 작성
#### data.sql(user, role관련 statement  추가), template밑에 view 작성

`/admin/home으로 접속하여 로그인창이 뜨는지 확인`
