package com.aluraChallenge.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GutendexResponse {

    private int count;
    private String next;
    private String previous;
    private List<Result> results;

    // Getters y Setters

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public static class Result {
        private Long id;
        private String title;
        private List<Author> authors;
        private List<String> subjects;
        private List<String> languages;
        private Formats formats;
        @JsonProperty("download_count")
        private Integer downloadCount;

        // Getters y Setters

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Author> getAuthors() {
            return authors;
        }

        public void setAuthors(List<Author> authors) {
            this.authors = authors;
        }

        public List<String> getSubjects() {
            return subjects;
        }

        public void setSubjects(List<String> subjects) {
            this.subjects = subjects;
        }

        public List<String> getLanguages() {
            return languages;
        }

        public void setLanguages(List<String> languages) {
            this.languages = languages;
        }

        public Formats getFormats() {
            return formats;
        }

        public void setFormats(Formats formats) {
            this.formats = formats;
        }

        public Integer getDownloadCount() {
            return downloadCount;
        }

        public void setDownloadCount(Integer downloadCount) {
            this.downloadCount = downloadCount;
        }

        public static class Author {
            private String name;
            @JsonProperty("birth_year")
            private Integer birthYear;
            @JsonProperty("death_year")
            private Integer deathYear;

            // Getters y Setters

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Integer getBirthYear() {
                return birthYear;
            }

            public void setBirthYear(Integer birthYear) {
                this.birthYear = birthYear;
            }

            public Integer getDeathYear() {
                return deathYear;
            }

            public void setDeathYear(Integer deathYear) {
                this.deathYear = deathYear;
            }
        }

        public static class Formats {
            @JsonProperty("text/html")
            private String textHtml;
            @JsonProperty("image/jpeg")
            private String imageJpeg;

            // Getters y Setters

            public String getTextHtml() {
                return textHtml;
            }

            public void setTextHtml(String textHtml) {
                this.textHtml = textHtml;
            }

            public String getImageJpeg() {
                return imageJpeg;
            }

            public void setImageJpeg(String imageJpeg) {
                this.imageJpeg = imageJpeg;
            }
        }
    }
}
