package com.example.places.service.impl;

import com.example.places.dto.DescriptionDTO;
import com.example.places.dto.LocationDTO;
import com.example.places.dto.PlacesDTO;
import com.example.places.dto.WeatherDTO;
import com.example.places.service.PlaceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
public class PlaceServiceImpl implements PlaceService {
    private final OkHttpClient client = new OkHttpClient();

    @Async
    public CompletableFuture<LocationDTO> getLocations(String placeName) {
        CompletableFuture<LocationDTO> future = new CompletableFuture<>();
        try {
            String GRAPHHOPPER_API_KEY = "&key=33da5aa6-c9fe-4a70-87e3-86ea919699fc";
            String GEOCODE_SOURCE = "https://graphhopper.com/api/1/geocode?";
            String url = GEOCODE_SOURCE + "q=" + placeName + "&locale=de" + GRAPHHOPPER_API_KEY;

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                ObjectMapper mapper = new ObjectMapper();
                LocationDTO locationDTO = mapper.readValue(response.body().string(), LocationDTO.class);
                future.complete(locationDTO);
            } else {
                throw new IOException("Ошибка при запросе к Graphhopper API" + response.code());
            }

        } catch (IOException exception) {
            future.completeExceptionally(exception);
        }

        return future;
    }

    @Override
    @Async
    public CompletableFuture<WeatherDTO> getWeather(Double lng, Double lat) {
        CompletableFuture<WeatherDTO> future = new CompletableFuture<>();
        try {
            String OPEN_WEATHER_API_KEY = "b2a165e8e48a8eaad07a6a24ecb969e0";
            String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lng + "&appid=" + OPEN_WEATHER_API_KEY;

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                ObjectMapper mapper = new ObjectMapper();
                WeatherDTO weatherDTO = mapper.readValue(response.body().string(), WeatherDTO.class);
                future.complete(weatherDTO);
            } else {
                throw new IOException("Ошибка при запросе к Open Weather API" + response.code());
            }
        } catch (IOException exception) {
            future.completeExceptionally(exception);
        }

        return future;
    }

    @Override
    @Async
    public CompletableFuture<PlacesDTO> getPlaces(Double lng, Double lat) {
        CompletableFuture<PlacesDTO> future = new CompletableFuture<>();
        try {
            String url = "https://kudago.com/public-api/v1.4/places/?lang=&fields=&expand=&order_by=&text_format=&ids=&location=&has_showings=&showing_since=&showing_until=&categories=&" +
                    "lon=" + lng + "&lat=" + lat + "&radius=10000";

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                ObjectMapper mapper = new ObjectMapper();
                PlacesDTO placesDTO = mapper.readValue(response.body().string(), PlacesDTO.class);
                future.complete(placesDTO);
            } else {
                throw new IOException("Ошибка при запросе к KudaGo API");
            }
        } catch (IOException exception) {
            future.completeExceptionally(exception);
        }
        return future;
    }

    @Override
    @Async
    public CompletableFuture<DescriptionDTO> getDescription(int id) {
        CompletableFuture<DescriptionDTO> future = new CompletableFuture<>();
        try {
            String url = "https://kudago.com/public-api/v1.2/places/" + id;

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                ObjectMapper mapper = new ObjectMapper();
                DescriptionDTO descriptionDTO = mapper.readValue(response.body().string(), DescriptionDTO.class);
                future.complete(descriptionDTO);
            } else {
                throw new IOException("Ошибка при запросе к KudaGo API");
            }
        } catch (IOException exception) {
            future.completeExceptionally(exception);
        }
        return future;
    }
}
