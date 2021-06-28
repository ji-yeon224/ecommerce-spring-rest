# ecommerce-springboot-rest

### 1. 개요

  Product와 Category를 관리하는 Rest API를 구현한다.

### 2. 기술 스택

Spring Boot + Spring Data JPA + Spring Security + Spring Rest with HATEOAS

### 3. 가정

  - Product는 다수개의 Category에 속할 수 있다. 즉, 전동 칫솔은 "Electronics" 와
"Beauty & Personal Care" 카테고리에 속할 수 있다.

  - Category는 다수개의 SubCategory를 가질 수 있다. 예를 들어 “Electronics”
category는 'Audio & Video Components', 'Camera & Photo', ‘Computers’
SubCategory를 가질 수 있다.

### 4. Rest API

### Products:
#### 1) Product CRUD ( URL: /api/products), `ProductController.java`

| URL                                              | 설명                               |
|--------------------------------------------------|------------------------------------|
| GET, /api/products                            | 모든 product를 조회한다           |
| GET, /api/products /{id}                       | 특정 id를 가진 product를 조회한다 |
| POST, /api/products, Body { "name": "P1", "price": 100.00 }      | 하나의 product를 생성한다         |
| PUT, /api/products/{id}, Body { "name": "P1", "price": 100.00 } | 하나의 product를 수정한다.        |
| DELETE, /api/products/{id}                     | 특정 id를 가진 product를 삭제한다 |

### Categories:

#### 1) Category CRUD ( URL: /api/categories), `CategoryController.java`

| URL                                              | 설명                               |
|--------------------------------------------------|------------------------------------|
| GET, /api/categories                             | 모든 category를 조회한다           |
| GET, /api/categories /{id}                       | 특정 id를 가진 category를 조회한다 |
| POST, /api/categories Body { "name": "C1" }      | 하나의 category를 생성한다         |
| PUT, /api/categories /{id} Body { "name": "C1" } | 하나의 category를 수정한다.        |
| DELETE, /api/categories/{id}                     | 특정 id를 가진 category를 삭제한다 |

#### 2) Add / Remove child categories `CategorySubcategoriesController.java`

| URL                                              | 설명                               |
|--------------------------------------------------|------------------------------------|
| GET, /api/categories/{parentid}/subcategories    | 특정 parentid를 가진 카테고리에 속한 자식카테고리를 조회한다   |
| POST, /api/categories/{parentid}/subcategories/{childid}   | Parent category와 child category를 연결한다|
| DELETE, /api/categories/{parentid}/subcategories/{childid}  | Parent category에서 child category를 제거한다   |

#### 3) Link / Unlink products, `CategoryProductsController.java`


| URL                                              | 설명                               |
|--------------------------------------------------|------------------------------------|
| GET, /api/categories/{categoryid}/products    | 특정 id를 가진 category에 속한 모든 product를 조회한다  |
| POST, /api/categories/{categoryid}/products/{productid}   | Product를 category에 넣는다 |
| DELETE, /api/categories/{categoryid}/products/{productid}  | Product를 category에서 제거한다   |


### 5. 데이터 베이스 초기화
1) 데이터베이스(ecommerce)를 생성한다:  create database ecommerce default character set utf8 collate utf8_general_ci;
2) 애를리케이션 실행시 data.sql에 있는 sql statement가 실행되어 초기 데이터가 저장된다

### 6. 실습할 Rest API

### Products:
1) Get, http://localhost:8080/api/products
2) Get, http://localhost:8080/api/products/1
3) Post, http://localhost:8080/api/products, id 100이 생성되는지 확인


{
 "name": "삼성컴퓨터",
 "price": 150
} // body는 postman에서 raw, json format으로 전송

4) Put, http://localhost:8080/api/products/100

{
 "name": "LG컴퓨터",
 "price": 120
}

5) Delete, http://localhost:8080/api/products/100

### Category:

#### 1) category CRUD
1-1) Get, http://localhost:8080/api/categories

1-2) Get, http://localhost:8080/api/categories/1

1-3) Post, http://localhost:8080/api/categories, id 101 카테고리 생성됨

{
 "name": "스마트폰"
}

1-4) Put, http://localhost:8080/api/categories/101

{
 "name": "태블릿"
}

1-5) Delete, http://localhost:8080/api/categories/101

#### 2) Add / Remove child categories 

2-1) Get, http://localhost:8080/api/categories/1/subcategories

2-2) subcategory를 생성한 후(id=102), category(id=1)에 연결한다

Post, http://localhost:8080/api/categories

{
 "name": "스마트폰"
}

Post, http://localhost:8080/api/categories/1/subcategories/102


2-3) Delete, http://localhost:8080/api/categories/1/subcategories/102


#### 3) Link / Unlink products

3-1) Get, http://localhost:8080/api/categories/8/products

먼저 “Computer” 카테고리에 존재하는 Product조회한다


3-2) Product( id=103)를 생성한 후, “Computer” 카테고리에 연결한다(Link)

Post, , http://localhost:8080/api/products

{
 "name": "LG컴퓨터",
 "price": 500
}

Post, http://localhost:8080/api/categories/8/products/103
