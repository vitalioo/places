package com.example.places.controller;

import com.example.places.dto.DescriptionDTO;
import com.example.places.dto.LocationDTO;
import com.example.places.dto.PlacesDTO;
import com.example.places.dto.WeatherDTO;
import com.example.places.service.PlaceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.CompletableFuture;

@Controller("")
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/places")
    public String places() {
        return "form";
    }

    @PostMapping("/search")
    public CompletableFuture<String> search(@RequestParam String placeName, Model model) throws IOException {
        model.addAttribute("placeName", placeName);

        CompletableFuture<LocationDTO> locationDTOCompletableFuture = placeService.getLocations(placeName);

        return locationDTOCompletableFuture.thenApplyAsync(result -> {
            model.addAttribute("place", result);
            return "search";
        });
    }


    @PostMapping("/weather")
    public CompletableFuture<String> weather(@RequestParam Double lng, @RequestParam Double lat, Model model) throws IOException {
        CompletableFuture<WeatherDTO> weatherDTOCompletableFuture = placeService.getWeather(lng, lat);

        return weatherDTOCompletableFuture.thenApplyAsync(result -> {
            model.addAttribute("weather", result);

            double tmp = result.getMain().getTemp() - 273.15;
            BigDecimal roundedTemperature = BigDecimal.valueOf(tmp).setScale(1, RoundingMode.HALF_UP);
            model.addAttribute("roundedTemperature", roundedTemperature);

            return "weather";
        });
    }

    @PostMapping("interesting-places")
    public CompletableFuture<String> interestingPlaces(@RequestParam Double lng, @RequestParam Double lat, Model model) throws IOException {
        CompletableFuture<PlacesDTO> placesDTOCompletableFuture = placeService.getPlaces(lng, lat);

        return placesDTOCompletableFuture.thenApplyAsync(result -> {
            model.addAttribute("places", result);
            return "places";
        });
    }

    @PostMapping("description")
    public CompletableFuture<String> description(@RequestParam int id, Model model) throws IOException {
        CompletableFuture<DescriptionDTO> descriptionDTOCompletableFuture = placeService.getDescription(id);

        return descriptionDTOCompletableFuture.thenApplyAsync(result -> {
           model.addAttribute("description", result);

           return "description";
        });
    }
}
