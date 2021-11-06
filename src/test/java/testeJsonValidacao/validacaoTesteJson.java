package testeJsonValidacao;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class validacaoTesteJson {

	@Test
	public void testeApiCepValidaJson() throws Exception {
		File file = new File("./src/test/resources/response.json");

		Response response =

				RestAssured.given().when().get("http://viacep.com.br/ws/06186100/json").then()
						.contentType(ContentType.JSON).extract().response();

		String expectedJson = FileUtils.readFileToString(file, Charset.defaultCharset());

		String actualJson = response.body().asString();

		JSONAssert.assertEquals(expectedJson, actualJson, JSONCompareMode.STRICT);
	}

}
