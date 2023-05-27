package spring;

import data.UserData;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.martalea.SpringStart;
import ru.martalea.YamlConfig;

import java.util.List;

import static io.restassured.RestAssured.given;

@SpringBootTest(classes = SpringStart.class)
class SpringRestAssuredTest {

    @Autowired
    private YamlConfig myConfig;

    @Test
    void RestTest() {
        System.out.println("I am using the following application profile: " + myConfig.getEnvironment());
        List<UserData> users = given().baseUri("https://reqres.in/")
                .when()
                .contentType(ContentType.JSON)
                .get("api/users?page=2")
                .then().log().status()
                .extract().body().jsonPath().getList("data", UserData.class);
        users.forEach(x-> Assertions.assertTrue(
                x.getAvatar().contains(x.getId().toString())
        ));


    }

}