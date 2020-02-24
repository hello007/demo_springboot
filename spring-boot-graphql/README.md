
# 相关网址
## Getting started with GraphQL Java and Spring Boot
https://www.graphql-java.com/tutorials/getting-started-with-spring-boot/

## github
https://github.com/graphql-java/graphql-java

## 入门教程
https://xeblog.cn/articles/6

# 可视化调试界面：/graphiql
url地址：http://localhost:8000/graphiql

## 查询：query
### 组合查询示例
```
{
  alisName: getUserById(id: 1) {
    id
    username
    password
    age
  }
  listUser {
    id
    username
  }
  getUserById(id: 3) {
    id
    username
    password
  }
}

```

## 修改：mutation
### 示例
```
mutation {
  deleteUser(id: 4) {
    id
    username
    password
    age
  }
}

```
