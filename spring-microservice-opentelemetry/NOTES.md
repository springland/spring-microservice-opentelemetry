


-Dotel.instrumentation.jdbc.pool.enabled=true
-Dotel.instrumentation.jdbc.enabled=true
-Dotel.instrumentation.jdbc-datasource.enabled=true
-Dotel.instrumentation.hibernate.enabled=true


例如想把 CartService.getProduct 也打成 span：
-Dotel.instrumentation.methods.include=com.springland365.tracing.cart.service.CartService[getProduct]

或者整包都打：
-Dotel.instrumentation.methods.include=com.springland365.tracing.cart.service.*[*]
