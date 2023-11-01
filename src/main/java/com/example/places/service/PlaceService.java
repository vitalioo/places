package com.example.places.service;

import com.example.places.dto.LocationDTO;
import com.example.places.dto.WeatherDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
public class PlaceService {
    private final OkHttpClient client = new OkHttpClient();

    @Async
    public CompletableFuture<LocationDTO> getLocations(String placeName) throws IOException {
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

    @Async
    public CompletableFuture<WeatherDTO> getWeather(Double lng, Double lat) throws IOException {
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
}
