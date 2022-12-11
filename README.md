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

