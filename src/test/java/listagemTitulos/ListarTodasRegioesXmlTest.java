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
	public void CT01_ListarAno() {
		System.out.println("\n#### CT01 - Listar por ano ####\n");

		Response response = given().header("Accept", "application/xml").parameter("ano", "2020").get(urlListarAno);

		System.out.println("Modalidade E valor total: " + response.xmlPath().getString("premiosPagosDTO.modalidade")
				+ ": R$ " + response.xmlPath().getString("premiosPagosDTO.totalGeral"));

		Assert.assertEquals(200, response.getStatusCode());

	}

	@Test
	public void CT02_ListarAcimaDe2Anos() {
		System.out.println("\n#### CT02 - Listar acima de 2 anos ####\n");

		String msgEsperada = "Intervalo de Anos não pode ser maior que 2 anos!";

		Response response = given().header("Accept", "application/xml").parameter("ano", "2017").get(urlListarAno);
		Assert.assertEquals(msgEsperada, response.jsonPath().getJsonObject("message"));
		System.out.println(response.jsonPath().getJsonObject("message"));

	}

	@Test
	public void CT03_UrlInvalida() {
		System.out.println("\n#### CT03 - URL Invalida ####\n");

		String msgEsperada = "URL Inválida";

		Response response = given().header("Accept", "application/xml").contentType("application/xml")
				.parameter("ano", "").get(urlListarAno);

		System.out.println("Mensagem de erro: " + response.jsonPath().getJsonObject("message"));
		
		Assert.assertEquals(msgEsperada, response.jsonPath().getJsonObject("message"));

	}

	@Test
	public void CT04_ListarAnoRegiao() {
		System.out.println("\n#### CT04 - Listar por Ano e região ####\n");

		Response response = given().header("Accept", "application/xml").contentType("application/xml")
				.parameter("ano", "2020").parameter("regiao", "SUL").get(urlListarAno);

		System.out.println("Total: " + response.xmlPath().getString("premiosPagosDTO.listaDeRegioes.regiao") + " R$ "
				+ response.xmlPath().getString("premiosPagosDTO.listaDeRegioes.totalDePremios"));

		Assert.assertEquals(200, response.getStatusCode());

	}

	@Test
	public void CT05_RegiaoInvalida() {
		System.out.println("\n#### CT05 - Região Invalida ####\n");

		String msgEsperada = "Região inválida!";

		Response response = given().header("Accept", "application/xml").contentType("application/xml").parameter("ano", "2020").parameter("regiao", "")
				.get(urlListarAno);

		Assert.assertEquals(msgEsperada, response.jsonPath().getJsonObject("message"));
		
		System.out.println("Mensagem de erro: " + response.jsonPath().getJsonObject("message"));
	}

	public void CT06_ListarAnoFuturo() {
		System.out.println("\n#### CT06 - Ano futuro ####\n");

		String msgEsperada = "Ano inválido!";

		Response response = given().contentType("application/xml").parameter("ano", "2022").get(urlListarAno);
		System.out.println(response.body());

		// Assert.assertEquals(msgEsperada,
		// response.jsonPath().getJsonObject("message"));
		// System.out.println("Mensagem de erro: " +
		// response.jsonPath().getJsonObject("message"));
	}

}
