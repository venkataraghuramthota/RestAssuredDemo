package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;


public class MyStepdefs
{

    @Given("^url \"([^\"]*)\"$")
    public void url(String url)
    {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("URL is: " + url);
        given().contentType(ContentType.JSON);


    }

    @When("userID is {string}")
    public void useridIs(String userID)
    {
        System.out.println("UserID is: " + userID);
        when().get("https://gorest.co.in/public/v2/users/" +userID).then().statusCode(200).log().all();
    }

    @Then("status should be {int}")
    public void statusShouldBe(int statusCode)
    {
        System.out.println("Status is: " + statusCode);

    }
}
