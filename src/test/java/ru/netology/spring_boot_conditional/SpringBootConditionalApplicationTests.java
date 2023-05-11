package ru.netology.spring_boot_conditional;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootConditionalApplicationTests {

    private static final String HOST = "http://localhost:";

    @Autowired
    TestRestTemplate restTemplate;

    private static final GenericContainer<?> myDevApp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);

    private static final GenericContainer<?> myProdApp = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        myDevApp.start();
        myProdApp.start();
    }

    @Test
    void contextLoads() {
        ResponseEntity<String> devAppEntity = restTemplate.getForEntity(HOST
                + myDevApp.getMappedPort(8080), String.class);
        Assertions.assertEquals(devAppEntity.getBody(), "Current profile is dev");
//        System.out.println(devAppEntity.getBody());
        ResponseEntity<String> prodAppEntity = restTemplate.getForEntity(HOST
                + myProdApp.getMappedPort(8081), String.class);
        Assertions.assertEquals(prodAppEntity.getBody(), "Current profile is production");
//        System.out.println(prodAppEntity.getBody());
    }

}
