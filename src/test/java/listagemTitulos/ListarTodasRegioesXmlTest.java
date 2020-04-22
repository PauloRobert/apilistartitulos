package listagemTitulos;

import static com.jayway.restassured.RestAssured.given;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.jayway.restassured.response.Response;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ListarTodasRegioesXmlTest {

	String urlListarAno = "http://127.0.0.1:8080/CSIG-GlobalAPI-web/rest/v1/premiosPagosPorRegiao/xml/porAno";

	@Test
	public void CT07_ListarAno() {
		System.out.println("\n#### CT07 - Listar por ano ####\n");

		Response response = given().header("Accept", "application/xml").parameter("ano", "2020").get(urlListarAno);

		System.out.println("Modalidade E valor total: " + response.xmlPath().getString("premiosPagosDTO.modalidade")
				+ ": R$ " + response.xmlPath().getString("premiosPagosDTO.totalGeral"));

		Assert.assertEquals(200, response.getStatusCode());

	}

	@Test
	public void CT08_ListarAcimaDe2Anos() {
		System.out.println("\n#### CT08 - Listar acima de 2 anos ####\n");

		String msgEsperada = "Intervalo de Anos não pode ser maior que 2 anos!";

		Response response = given().header("Accept", "application/xml").parameter("ano", "2017").get(urlListarAno);
		Assert.assertEquals(msgEsperada, response.jsonPath().getJsonObject("message"));
		System.out.println(response.jsonPath().getJsonObject("message"));

	}

	@Test
	public void CT09_UrlInvalida() {
		System.out.println("\n#### CT09 - URL Invalida ####\n");

		String msgEsperada = "URL Inválida";

		Response response = given().header("Accept", "application/xml").contentType("application/xml")
				.parameter("ano", "").get(urlListarAno);

		System.out.println("Mensagem de erro: " + response.jsonPath().getJsonObject("message"));

		Assert.assertEquals(msgEsperada, response.jsonPath().getJsonObject("message"));

	}

	@Test
	public void CT10_ListarAnoRegiao() {
		System.out.println("\n#### CT10 - Listar por Ano e região ####\n");

		Response response = given().header("Accept", "application/xml").contentType("application/xml")
				.parameter("ano", "2020").parameter("regiao", "SUL").get(urlListarAno);

		System.out.println("Total: " + response.xmlPath().getString("premiosPagosDTO.listaDeRegioes.regiao") + " R$ "
				+ response.xmlPath().getString("premiosPagosDTO.listaDeRegioes.totalDePremios"));

		Assert.assertEquals(200, response.getStatusCode());

	}

	@Test
	public void CT11_RegiaoInvalida() {
		System.out.println("\n#### CT11 - Região Invalida ####\n");

		String msgEsperada = "Região inválida!";

		Response response = given().header("Accept", "application/xml").contentType("application/xml")
				.parameter("ano", "2020").parameter("regiao", "").get(urlListarAno);

		Assert.assertEquals(msgEsperada, response.jsonPath().getJsonObject("message"));

		System.out.println("Mensagem de erro: " + response.jsonPath().getJsonObject("message"));
	}

	@Test
	public void CT12_ListarAnoFuturo() {
		System.out.println("\n#### CT12 - Ano futuro ####\n");

		String msgEsperada = "Ano inválido!";

		Response response = given().header("Accept", "application/xml").contentType("application/xml")
				.parameter("ano", "2022").get(urlListarAno);

		Assert.assertEquals(msgEsperada, response.jsonPath().getJsonObject("message"));
		// System.out.println("Mensagem de erro: " +
		// response.jsonPath().getJsonObject("message"));
	}

}
