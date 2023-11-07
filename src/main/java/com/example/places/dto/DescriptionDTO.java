package com.example.places.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DescriptionDTO {
    private int id;
    private String title;
    private String slug;
    private String address;
    private String timetable;
    private String phone;

    @JsonProperty("is_stub")
    private boolean isStub;

    @JsonProperty("body_text")
    private String bodyText;
    private String description;

    @JsonProperty("site_url")
    private String siteUrl;

    @JsonProperty("foreign_url")
    private String foreignUrl;
    private Coords coords;
    private String subway;

    @JsonProperty("favorites_count")
    private int favoritesCount;
    private Image[] images;
    @JsonProperty("comments_count")
    private int commentsCount;

    @JsonProperty("is_closed")
    private boolean isClosed;
    private String[] categories;

    @JsonProperty("short_title")
    private String shortTitle;
    private String[] tags;
    private String location;

    @JsonProperty("age_restriction")
    private String ageRestriction;

    @JsonProperty("disable_comments")
    private boolean disableComments;

    @JsonProperty("has_parking_lot")
    private boolean hasParkingLot;


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Coords {
        private double lat;
        private double lon;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Image {
        private String image;
        private Source source;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Source {
        private String link;
        private String name;

    }
}
