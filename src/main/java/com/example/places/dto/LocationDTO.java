package com.example.places.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class LocationDTO {

    private List<Hit> hits;
    private String locale;

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

        public static class Point {
            private Double lng;
            private Double lat;

            public Double getLng() {
                return lng;
            }

            public void setLng(Double lng) {
                this.lng = lng;
            }

            public Double getLat() {
                return lat;
            }

            public void setLat(Double lat) {
                this.lat = lat;
            }
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public List<Double> getExtent() {
            return extent;
        }

        public void setExtent(List<Double> extent) {
            this.extent = extent;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Long getOsmID() {
            return osmID;
        }

        public void setOsmID(Long osmID) {
            this.osmID = osmID;
        }

        public String getOsmType() {
            return osmType;
        }

        public void setOsmType(String osmType) {
            this.osmType = osmType;
        }

        public String getHouseNumber() {
            return houseNumber;
        }

        public void setHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
        }

        public String getOsmKey() {
            return osmKey;
        }

        public void setOsmKey(String osmKey) {
            this.osmKey = osmKey;
        }

        public String getOsmValue() {
            return osmValue;
        }

        public void setOsmValue(String osmValue) {
            this.osmValue = osmValue;
        }

        public Point getPoint() {
            return point;
        }

        public void setPoint(Point point) {
            this.point = point;
        }
    }

    public List<Hit> getHits() {
        return hits;
    }

    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

}
