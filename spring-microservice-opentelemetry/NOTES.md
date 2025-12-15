


-Dotel.instrumentation.jdbc.pool.enabled=true
-Dotel.instrumentation.jdbc.enabled=true
-Dotel.instrumentation.jdbc-datasource.enabled=true
-Dotel.instrumentation.hibernate.enabled=true


例如想把 CartService.getProduct 也打成 span：
-Dotel.instrumentation.methods.include=com.springland365.tracing.cart.service.CartService[getProduct]

或者整包都打：
-Dotel.instrumentation.methods.include=com.springland365.tracing.cart.service.*[*]

metrics

query with tags
http://localhost:8090/actuator/metrics/jvm.memory.used?tag=area:heap

Resigering custom tags
https://docs.spring.io/spring-boot/3.5/reference/actuator/metrics.html#actuator.metrics.registering-custom

cart service and product service have app_name common custom tag


@Timed

Cart service is using annotation , cart-service.add-item does not show up until it is hit first time

Product service is using programatic way

promethus  url

http://localhost:8090/actuator/prometheus