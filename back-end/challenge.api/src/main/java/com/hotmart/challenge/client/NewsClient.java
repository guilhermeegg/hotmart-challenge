package com.hotmart.challenge.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.hotmart.challenge.domain.model.entity.CategoriaEntity;

/**
 * Client para consumir a API https://newsapi.org/ Endpoint
 * https://newsapi.org/docs/endpoints/top-headlines
 * 
 * @author Guilherme Egg
 *
 */
@Component
public class NewsClient {

	private static final String URL = "http://newsapi.org/v2/top-headlines?country={country}&category={category}";

	private static final String API_KEY = "25f2ca897fef487a91fe2ba97b13845a";

	private static final String COUNTRY = "br";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Long getTotalNoticiasPorCategoria(CategoriaEntity categoria) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-Api-Key", API_KEY);

		HttpEntity request = new HttpEntity(headers);

		Map<String, String> params = new HashMap();
		params.put("country", COUNTRY);
		params.put("category", categoria.getNome());

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<RetornoNewsDTO> responseNoticiaDTO = restTemplate.exchange(URL, HttpMethod.GET, request,
				RetornoNewsDTO.class, params);

		if (HttpStatus.OK.equals(responseNoticiaDTO.getStatusCode())) {
			return responseNoticiaDTO.getBody().getTotalResults();
		} else {
			throw new RestClientException("Erro na consulta ao serviço" + responseNoticiaDTO.toString());
		}

	}

}
