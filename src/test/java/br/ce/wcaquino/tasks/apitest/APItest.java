package br.ce.wcaquino.tasks.apitest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class APItest {

    @BeforeClass
    public static void setup(){

        baseURI = "http://localhost:8001/tasks-backend";
    }

    @Test
    public void deveRetornarTarefas()
    {
        given()
                .log().all()
                .when()
                .get("/todo")
                .then()
                .log().all()
                .statusCode(200)
                ;
    }

    @Test
    public void deveAdicionarTarefaComSucesso(){
        given()
                .body("{ \"task\": \"Teste via API\", \"dueDate\": \"2023-12-30\" }")
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .post("/todo")
                .then()
                .log().all()
                .statusCode(201)


        ;
    }
    @Test
    public void naoDeveAdicionarTarefaComSucesso(){
        given()
                .body("{ \"task\": \"Teste via API\", \"dueDate\": \"2020-12-30\" }")
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .post("/todo")
                .then()
                .log().all()
                .statusCode(400)
                .body("message", CoreMatchers.is("Due date must not be in past"))

        ;
    }

        }


