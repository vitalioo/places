package com.example.places.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WeatherDTO {
    private Coord coord;
    private List<Weather> weather;
    private String base;
    private Main main;
    private int visibility;
    private Wind wind;
    private Snow snow;
    private Rain rain;
    private Clouds clouds;
    private long dt;
    private Sys sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Coord {
        private double lon;
        private double lat;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Weather {
        private int id;
        private String main;
        private String description;
        private String icon;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Main {
        private double temp;
        @JsonAlias({"feels_like"})
        private double feelsLike;
        @JsonAlias({"temp_min"})
        private double tempMin;

        @JsonAlias({"temp_max"})
        private double tempMax;
        private int pressure;
        private int humidity;
        @JsonAlias({"sea_level"})
        private double seaLevel;
        @JsonAlias({"grnd_level"})
        private double grndLevel;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Wind {
        private double speed;
        private int deg;
        private double gust;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Snow {
        @JsonProperty("1h")
        private double h1;

        @JsonProperty("3h")
        private double h3;

    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Rain {
        @JsonProperty("1h")
        private double h1;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Clouds {
        private int all;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Sys {
        private int type;
        private int id;
        private String country;
        private long sunrise;
        private long sunset;
        private int message;
    }

}
