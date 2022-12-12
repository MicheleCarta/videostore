# video-store-service

## Building

---

* [local development](#local-development)

---


# LOCAL DEVELOPMENT

<details>
<summary>TESTING MODULE</summary>


The first time, you should do a `./build.sh` as the produced jars
are mounted in to the docker-compose stack.


```bash
# ... run rabbitmq and postgres
docker-compose up

# ... properly destroy
docker-compose down --volumes --remove-orphans
```
</details>

<details>
<summary>RUN THE SERVICE</summary>


The first time, you should do a `./gradlew bootRun` as the produced jars
are mounted in to the docker-compose stack.


```bash
./gradlew bootRun
```
</details>



### Manual

* Add Movie

```yaml
  POST: /v1/movie/
  { 
  "title":"some title new2",  
  "description":"desc movie bla bla bla",
  "category": "Fantasy",
  "yearProduction":"2021",
  "yearReleased":"2022",
  "monthReleased":"5",
  "casting":"Tom,Jerry, willie, minnie",
  "rating": 0,
  "directors":[3,5]
}
```

* Add Director

```yaml
  POST: /v1/movie/
  {
    "name": "Stephen",
    "surname": "Spielberg",
    "bDay": "1950-01-02T12:34:56Z"
  }
```

* Add Director

```yaml
  POST: /v1/movie/
  {
    "name": "Stephen",
    "surname": "Spielberg",
    "bDay": "1950-01-02T12:34:56Z"
  }
```

