package com.example.places.service;

import com.example.places.dto.DescriptionDTO;
import com.example.places.dto.LocationDTO;
import com.example.places.dto.PlacesDTO;
import com.example.places.dto.WeatherDTO;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public interface PlaceService {
    CompletableFuture<LocationDTO> getLocations(String placeName) throws IOException;
    CompletableFuture<WeatherDTO> getWeather(Double lng, Double lat) throws IOException;
    CompletableFuture<PlacesDTO> getPlaces(Double lng, Double lat) throws IOException;
    CompletableFuture<DescriptionDTO> getDescription(int id) throws IOException;
}
