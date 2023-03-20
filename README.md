
[![CircleCI](https://circleci.com/gh/ganvoa/java-cucumber-example/tree/main.svg?style=svg)](https://circleci.com/gh/ganvoa/java-cucumber-example/tree/main)

### Prerequisites

 - [docker](https://docs.docker.com/get-docker/) 
 - make

## run server

```console
make run
```

### API Examples

#### Create User

```console
curl -s -XPOST -H 'content-type: application/json' \
    -w "%{http_code}" \
    -d '{"uuid": "b8bd9278-b164-49a4-ad50-77df7ace8cec", "username": "jperez", "name": "Juan Perez", "email": "jperez@email.com", "password": "xxxXXxX"}' \
    localhost:8888/users
```  

#### Update User

```console
curl -s -XPUT -H 'content-type: application/json' \
    -w "%{http_code}" \
    -d '{"username": "jperez2", "name": "Juan Perez2", "email": "jperez@email.com", "password": "xxxXXxX"}' \
    localhost:8888/users/b8bd9278-b164-49a4-ad50-77df7ace8cec
```  

#### Get User

```console
curl -s -XGET localhost:8888/users/b8bd9278-b164-49a4-ad50-77df7ace8cec | jq .
```
response

```json
{
  "username": "jperez",
  "name": "Juan Perez",
  "email": "jperez@email.com",
  "uuid": "b8bd9278-b164-49a4-ad50-77df7ace8cec"
}
```

#### Delete User

```console
curl -s -XDELETE \
    -w "%{http_code}" \
    localhost:8888/users/b8bd9278-b164-49a4-ad50-77df7ace8cec
``` 

## run tests
```console
make test
```
