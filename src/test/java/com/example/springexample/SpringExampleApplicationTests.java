package com.example.springexample;
import com.example.springexample.dto.CommentDto;
import com.example.springexample.entity.Author;
import com.example.springexample.entity.Comment;
import com.example.springexample.repositories.AuthorRepository;
import com.example.springexample.repositories.CommentRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringExampleApplicationTests {

    @LocalServerPort
    private Integer port;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private TestRestTemplate template = new TestRestTemplate();

    //тут создаем контейнер с postgresql 14
    @Container
    public static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:14");
    //это создается объект, который использует докер-контейнер с СУБД PostgreSQL, что позволит запускать БД в изолированной среде

//    @BeforeAll
//    public static void beforeAll(){
//        postgres.start();
//    }
//
//    @AfterAll
//    public static void afterAll(){
//        postgres.stop();
//    }

    @DynamicPropertySource
    public static void configureProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeEach
    public void fillingDataBase(){
        Comment comment = new Comment();
        comment.setText("Commentariy");
        comment.setAuthor(authorRepository.save(new Author()));
        commentRepository.save(comment);
    }

    @AfterEach
    public void clearDataBase(){
        authorRepository.deleteAll();
    }

    @Test
    @DisplayName("Test getById")
    public void testGetCommentById(){
        ResponseEntity<CommentDto> response = template
                .getRestTemplate()
                .getForEntity("http://localhost:" + port + "/comment/1", CommentDto.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(1, response.getBody().getId());
        assertEquals("Commentariy", response.getBody().getText());
    }
}
