package spring;

import data.CatFacts;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.martalea.SpringStart;
import ru.martalea.YamlConfig;

import static io.restassured.RestAssured.given;

@SpringBootTest(classes = SpringStart.class)
public class CatFactsApiTest {

    @Autowired
    private YamlConfig myConfig;

    @Test
    void GetRandomTestFactTest(){
        System.out.println("I am using the following application profile: " + myConfig.getEnvironment());
        CatFacts fact = given()
                .baseUri("https://catfact.ninja/")
                .when().contentType(ContentType.JSON)
                .get("fact")
                .then().statusCode(200).log().body()
                .extract().as(CatFacts.class);
        System.out.println(fact.fact);
        Assertions.assertEquals(fact.fact.length(), fact.length);
    }
}