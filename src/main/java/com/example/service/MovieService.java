package com.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class MovieService {

  private final RestClient restClient;

  public MovieService(
      RestClient.Builder restClientBuilder, @Value("${tmdb.api.token}") String apiToken) {
    this.restClient =
        restClientBuilder
            .baseUrl("https://api.themoviedb.org/3")
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiToken)
            .defaultHeader(HttpHeaders.ACCEPT, "application/json")
            .build();
  }

  public String searchMovies(String title) {
    return restClient
        .get()
        .uri(uriBuilder -> uriBuilder.path("/search/movie").queryParam("query", title).build())
        .retrieve()
        .body(String.class);
  }
}
