package br.com.uniesp.test;

import br.com.uniesp.entidate.PessoaRequest;
import br.com.uniesp.entidate.PessoaResponse;
import br.com.uniesp.entidate.UserRegister;
import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.response.ValidatableResponseLogSpec;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;

class JUnit5ExampleTest {

    @BeforeEach
    void configuraApi() {
        baseURI = 	"https://reqres.in/";
    }

    @Test
    void justAnExample() {

    }

    @Test
    @DisplayName("esse teste faz isso")
    @Tag("E2E")
    void methodGet() {
       given().log().all()
                .when()
                .get("api/users/2")
                .then().contentType("application/json")
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schemas/thiagoExample.json"))
                .log();
    }

    @Test
    void methodPost() {
        given()
                .when()
                .get("api/users/2")
                .then().contentType("application/json")
                .statusCode(HttpStatus.SC_OK)
                .log().all();
    }

    @Test
    void methodPostFull() {
        PessoaRequest pessoaRequest = new PessoaRequest("thiago","QA");

        basePath= "/api/users";
        PessoaResponse as = given().log().all()
                .contentType(ContentType.JSON)
                .body(pessoaRequest)
                .when()
                .post("/")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .and()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schemas/methodPost.json"))
                .log().all().extract().response().as(PessoaResponse.class);

        Assertions.assertNotNull(as);
        Assertions.assertNotNull(as.getId());
        Assertions.assertEquals(pessoaRequest.getNome(), as.getNome());
        Assertions.assertEquals(pessoaRequest.getJob(), as.getJob());
    }

    // by Wermerson
    @Test
    @DisplayName("Teste PUT delete User 2")
    void methodDelete() {
        given().log().all()
                .when()
                .delete("api/users/2")
                .then().statusCode(HttpStatus.SC_NO_CONTENT)
                .log();
    }

    // by Wermerson
    @Test
    @DisplayName("Teste verifica se o registro de usuário está sendo feito com sucesso.")
    void methodUserRegister() {
        UserRegister userRegister = new UserRegister("charles.morris@reqres.in","pistol");
        //UserRegister userRegister = new UserRegister("wermersonwca@gmail.com","123");

        given().log().all()
                .contentType(ContentType.JSON)
                .body(userRegister)
                .when()
                .post("api/register")
                .then().statusCode(HttpStatus.SC_OK)
                .and().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schemas/userRegister.json"))
                .log();
    }
}