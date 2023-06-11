package com.example.springdemo;

import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringDemoApplicationTests {
//    @Autowired
//    private TestRestTemplate restTemplate;
//    private final CardTransfer cardTransfer = new CardTransfer();
//    HttpHeaders headers;
//    @Container
//    private static final GenericContainer<?> myApp = new GenericContainer<>("spring-transfer:1.0")
//            .withExposedPorts(8080);
//
//    @BeforeEach
//    void setUp() {
//        myApp.start();
//        cardTransfer.setCardFromNumber("1111111111111111");
//        cardTransfer.setCardFromCVV("111");
//        cardTransfer.setCardFromValidTill("11/23");
//        cardTransfer.setCardToNumber("1111111111111122");
//        Amount amount = new Amount();
//        amount.setCurrency("ru");
//        amount.setValue(200);
//        cardTransfer.setAmount(amount);
//
//        headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//    }
//
//    @Test
//    void contextLoads() throws IOException {
//        HttpEntity<String> request = new HttpEntity<>(cardTransfer.toString(), headers);
//
//        Integer port = myApp.getMappedPort(8080);
//        ResponseEntity<?> entity = restTemplate.postForEntity("http//localhost:" + port + "/transfer", request,
//                String.class);
//
//        assertEquals("", entity.getBody(), new Res200("1"));
//    }
}
