### Przygotowanie środowiska

0. Wymagane jest zainstalowanie Dockera
1. Uruchomić kontener z KeyCloak-iem

   `docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin -v <local keycloak folder>:/opt/keycloak/data/ quay.io/keycloak/keycloak:17.0 start-dev`

   UWAGA: W tej chwili mamy już KeyCloak w wersji 20, ale na szkoleniu użyjemy 17, ze względu na niekompatybilne zmiany od wersji 18 w górę (np. usunięcie parametru `redirect_uri`)
2. Założyć nowy realm o nazwie `example` - będziemy działać w jego ramach
3. Stworzyć dwie role - `admin` i `user`
4. Stworzyć dwóch użytkowników - `root` i `regular` (o takich samych hasłach jak loginy)
5. Dodać nowego klienta o id `spring-boot`
6. We własnościach klienta ustalić:

   * Access type: `confidential` (tak aby potrzebny był secret do zalogowania)
   * Valid redirect URI: `*` (UWAGA: absolutnie nie robić tak na produkcji bo to security hole jak diabli !!!)
7. Zapisać zmiany i w nowo stworzonej zakładce `Credentials` odczytać `secret`. Będzie nam potrzebny do logowania

### Logowanie za pomocą Postmana (lub innego klienta REST)

1. Pobrać informacje na temat dostępnych endpointów dla danego realma:

   `GET http://localhost:8080/realms/example/.well-known/openid-configuration`
2. Wygenerować token poprzez endpoint:

   `POST http://localhost:8080/realms/example/protocol/openid-connect/token`

   przekazując następujące parametry w body (x-www-form-urlencoded):

   ```
   grant_type:password
   client_id:spring-boot
   client_secret:<secret>
   username:root
   password:root
   ```
3. Sprawdzić zawartość wygenerowanego tokena na stronie [jwt.io](https://jwt.io)

### Przydatne linki:

https://javaleader.pl/2021/11/21/integracja-keycloak-z-spring-boot/

https://www.springcloud.io/post/2022-07/spring-boot-keycloak-roles/#gsc.tab=0
