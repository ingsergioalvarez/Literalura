package com.aluraChallenge.demo;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    private String authorName;

    private Integer authorBirthYear;

    private Integer authorDeathYear;

    @ElementCollection
    private List<String> subjects;

    @ElementCollection
    private List<String> languages;

    private String htmlFormat;

    private String jpegFormat;

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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getAuthorBirthYear() {
        return authorBirthYear;
    }

    public void setAuthorBirthYear(Integer authorBirthYear) {
        this.authorBirthYear = authorBirthYear;
    }

    public Integer getAuthorDeathYear() {
        return authorDeathYear;
    }

    public void setAuthorDeathYear(Integer authorDeathYear) {
        this.authorDeathYear = authorDeathYear;
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

    public String getHtmlFormat() {
        return htmlFormat;
    }

    public void setHtmlFormat(String htmlFormat) {
        this.htmlFormat = htmlFormat;
    }

    public String getJpegFormat() {
        return jpegFormat;
    }

    public void setJpegFormat(String jpegFormat) {
        this.jpegFormat = jpegFormat;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }
}
