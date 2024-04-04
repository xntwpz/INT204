# INT204
source for test



### for property
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.password=Xntwpz092...
spring.datasource.username=root
spring.datasource.url=jdbc:mysql://localhost:3306/classicmodels
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
#=> is mean u also have database
spring.jpa.hibernate.ddl-auto= none
# =>don't have database yet , it will create

# ------------------------------------------- option
# can change url when don't like default
spring.main.banner-mode=off
logging.level.root=error
logging.level.org.springframework.web=info
logging.level.org.hibernate=error
server.port=8099
server.servlet.context-path=/classic-models
server.error.include-stacktrace=on_param

# ------------------------------------------- h2 section
#spring.datasource.url=jdbc:h2:mem:demo
