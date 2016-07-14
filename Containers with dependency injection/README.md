# Containers with dependency injection
## Why and what is dependency injection?
### A typical application architecture

1. what if we want to switch to a file-based authentication? a database-based authentication?

`We need to rewrite the Blog Edition Controller`
2. what if we want to switch to MongoDB rather than PostgreSQL?

`We also need to rewrite the Blog Edition Controller if we use an OCBD, we need to change the API we use.`
3. how do we run unit and integration tests on our development machines without having to run PostgreSQL and a LDAP directory server? how do we ensure repeatability?
`- no idea... Maybe we can't...`


### Première utilisation de springframework
1. Test concluant !
2. On doit créer deux nouveaux fichier: AnotherGreetingTest.java et casual-greeter-app.xml
3. The cast on getBean(String) is annoying. Use getBean(String, Class) to get rid of the cast. ===> `To get the classse use ClassName.class`

### Annotations and class scan

- Now add the @Component annotation to CasualGreeter. What do you see? Can you explain it?
`The test doesn't know which Component choose. Both are used and are in conflict.`
