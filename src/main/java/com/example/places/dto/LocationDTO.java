package com.example.places.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LocationDTO {

    private List<Hit> hits;
    private String locale;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Hit {
        private String state;
        private Point point;
        private List<Double> extent;
        private String name;
        private String country;
        @JsonAlias({"countrycode"})
        private String countryCode;
        private String city;
        private String street;
        private String postcode;
        @JsonAlias({"osm_id"})
        private Long osmID;
        @JsonAlias({"osm_type"})
        private String osmType;
        @JsonAlias({"housenumber", "house_number"})
        private String houseNumber;
        @JsonAlias({"osm_key"})
        private String osmKey;
        @JsonAlias({"osm_value"})
        private String osmValue;

        @AllArgsConstructor
        @NoArgsConstructor
        @Getter
        @Setter
        public static class Point {
            private Double lng;
            private Double lat;
        }
    }
}
