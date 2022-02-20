package br.mil.ccarj.baseapi;

import br.mil.ccarj.baseapi.domain.model.Disciplina;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DisciplinaTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Sql("/test.sql")
    public void getDisciplinaByIdTest() {

        ResponseEntity<Disciplina> response = testRestTemplate.getForEntity("/disciplina/1", Disciplina.class);

        assertEquals(1, response.getBody().getId());
        assertEquals("Matemática", response.getBody().getNome());

    }

    @Test
    public void salvarDisciplina() {

        Disciplina disciplina = new Disciplina();

        disciplina.setNome("História");

        HttpEntity<Disciplina> request = new HttpEntity<>(disciplina);

        ResponseEntity<Disciplina> response = testRestTemplate.postForEntity("/disciplina", request, Disciplina.class);

        assertNotNull(response.getBody().getId());

        assertEquals("História", response.getBody().getNome());

    }

}
