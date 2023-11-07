package com.example.places.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class PlacesDTO {
    private int count;
    private String next;
    private String previous;

    @JsonProperty("results")
    private List<Place> results;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Place {
        private int id;
        private String title;
        private String slug;
        private String address;
        private String phone;

        @JsonProperty("site_url")
        private String siteURL;
        private String subway;
        private String location;
        @JsonAlias("site_url")
        private String siteUrl;

        @JsonProperty("is_closed")
        private boolean closed;

        @JsonProperty("has_parking_lot")
        private boolean hasParkingLot;
    }
}
