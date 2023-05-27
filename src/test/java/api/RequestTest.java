package api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RequestTest {

    private static String URL_UNDER_TEST = "https://reqres.in/";

    @Test
    void checkCheckAvatarAndIdEqualsTest() {
        List<UserData> users = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL_UNDER_TEST + "api/users?page=2")
                .then().log().status()
                .extract().body().jsonPath().getList("data", UserData.class);
        users.forEach(x-> Assertions.assertTrue(
                x.getAvatar().contains(x.getId().toString())
        ));
    }
}
