package listagemTitulos;

import static com.jayway.restassured.RestAssured.given;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.jayway.restassured.response.Response;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ListarTodasRegioesTest {

	String urlListarAno = "http://127.0.0.1:8080/CSIG-GlobalAPI-web/rest/v1/premiosPagosPorRegiao/json/porAno";

	@Test
	public void CT01_ListarAno() {
		System.out.println("\n#### CT01 - Listar por ano ####\n");

		Response response = given().contentType("application/json").parameter("ano", "2020").get(urlListarAno);
		System.out.println("Modalidade do titulo: " + response.jsonPath().getJsonObject("modalidade"));
		Assert.assertEquals(200, response.getStatusCode());

	}

	@Test
	public void CT02_ListarAcimaDe2Anos() {
		System.out.println("\n#### CT02 - Listar acima de 2 anos ####\n");

		String msgEsperada = "Intervalo de Anos não pode ser maior que 2 anos!";

		Response response = given().contentType("application/json").parameter("ano", "2017").get(urlListarAno);

		System.out.println("Mensagem de erro: " + response.jsonPath().getJsonObject("message"));
		Assert.assertEquals(msgEsperada, response.jsonPath().getJsonObject("message"));

	}

	@Test
	public void CT03_UrlInvalida() {
		System.out.println("\n#### CT03 - URL Invalida ####\n");

		String msgEsperada = "URL Inválida";

		Response response = given().contentType("application/json").parameter("ano", "").get(urlListarAno);

		System.out.println("Mensagem de erro: " + response.jsonPath().getJsonObject("message"));
		Assert.assertEquals(msgEsperada, response.jsonPath().getJsonObject("message"));

	}

	@Test
	public void CT04_ListarAnoRegiao() {
		System.out.println("\n#### CT04 - Listar por Ano e região ####\n");

		Response response = given().contentType("application/json").parameter("ano", "2020").parameter("regiao", "SUL")
				.get(urlListarAno);
		System.out.println("Total: " + response.jsonPath().getJsonObject("listaDeRegioes.regiao") + " R$ "
				+ response.jsonPath().getJsonObject("listaDeRegioes.totalDePremios"));
		Assert.assertEquals(200, response.getStatusCode());

	}

	@Test
	public void CT05_RegiaoInvalida() {
		System.out.println("\n#### CT05 - Região Invalida ####\n");

		String msgEsperada = "Região inválida!";

		Response response = given().contentType("application/json").parameter("ano", "2020").parameter("regiao", "")
				.get(urlListarAno);

		Assert.assertEquals(msgEsperada, response.jsonPath().getJsonObject("message"));
		System.out.println("Mensagem de erro: " + response.jsonPath().getJsonObject("message"));
	}

	@Test
	public void CT06_ListarAnoFuturo() {
		System.out.println("\n#### CT06 - Ano futuro ####\n");

		String msgEsperada = "Ano inválido!";

		Response response = given().contentType("application/json").parameter("ano", "2022").get(urlListarAno);

		Assert.assertEquals(msgEsperada, response.jsonPath().getJsonObject("message"));
		System.out.println("Mensagem de erro: " + response.jsonPath().getJsonObject("message"));
	}

}
