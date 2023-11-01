package com.example.places.controller;

import com.example.places.dto.LocationDTO;
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
    private CompletableFuture<LocationDTO> locationDTOCompletableFuture;
    private CompletableFuture<WeatherDTO> weatherDTOCompletableFuture;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/places")
    public String places(){
        return "form";
    }

    @PostMapping("/search")
    public CompletableFuture<String> search(@RequestParam String placeName, Model model) throws IOException {
        model.addAttribute("placeName", placeName);

        locationDTOCompletableFuture = placeService.getLocations(placeName);

        return locationDTOCompletableFuture.thenApplyAsync(result -> {
            model.addAttribute("place", result);
            return "search";
        });
    }

    //TODO: Добавить вывод погоды в локации, интересных мест, описания локации

    @PostMapping("/weather")
    public CompletableFuture<String> weather(@RequestParam Double lng, @RequestParam Double lat, Model model) throws IOException {
/*
        CompletableFuture<LocationDTO.Hit> hitDTOCompletableFuture = locationDTOCompletableFuture.thenApplyAsync(result ->{
           return result.getHits().stream()
                   .filter(hit ->Double.compare(hit.getPoint().getLng(), lng) == 0 && Double.compare(hit.getPoint().getLat(), lat) == 0)
                   .findFirst()
                   .orElse(null);
        });


        //TODO: ворачиваем отображение выбранной локации и её погоды
*/

        weatherDTOCompletableFuture = placeService.getWeather(lng, lat);

        return weatherDTOCompletableFuture.thenApplyAsync(result -> {
            model.addAttribute("weather", result);

            double tmp = result.getMain().getTemp() - 273.15;
            BigDecimal roundedTemperature = BigDecimal.valueOf(tmp).setScale(1, RoundingMode.HALF_UP);
            model.addAttribute("roundedTemperature", roundedTemperature);

            return "weather";
        });
    }
}
