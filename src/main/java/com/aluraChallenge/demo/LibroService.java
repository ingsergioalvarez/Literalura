package com.aluraChallenge.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public List<Libros> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    public Libros guardarLibro(Libros libro) {
        return libroRepository.save(libro);
    }

    public Libros buscarYGuardarLibroPorTitulo(String titulo) {
        // Verificar si el libro ya existe en la base de datos
        Optional<Libros> libroExistente = libroRepository.findByTitle(titulo);
        if (libroExistente.isPresent()) {
            System.out.println("El libro '" + titulo + "' ya está registrado en la base de datos.");
            return libroExistente.get(); // Devolver el libro existente
        }

        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://gutendex.com/books?search=" + titulo;
        GutendexResponse response = restTemplate.getForObject(apiUrl, GutendexResponse.class);

        if (response != null && !response.getResults().isEmpty()) {
            GutendexResponse.Result libroApi = response.getResults().get(0);

            Libros libro = new Libros();
            libro.setId(libroApi.getId());
            libro.setTitle(libroApi.getTitle());
            if (!libroApi.getAuthors().isEmpty()) {
                libro.setAuthorName(libroApi.getAuthors().get(0).getName());
                libro.setAuthorBirthYear(libroApi.getAuthors().get(0).getBirthYear());
                libro.setAuthorDeathYear(libroApi.getAuthors().get(0).getDeathYear());
            }
            libro.setSubjects(libroApi.getSubjects());
            libro.setLanguages(libroApi.getLanguages());
            libro.setHtmlFormat(libroApi.getFormats().getTextHtml());
            libro.setJpegFormat(libroApi.getFormats().getImageJpeg());
            libro.setDownloadCount(libroApi.getDownloadCount());

            return guardarLibro(libro);
        }
        return null;
    }

    public void buscarYMostrarLibros(String valorBuscado) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://gutendex.com/books?search=" + valorBuscado;
        GutendexResponse response = restTemplate.getForObject(apiUrl, GutendexResponse.class);

        if (response != null && response.getResults() != null) {
            response.getResults().forEach(result -> {
                String autores = result.getAuthors().stream()
                        .map(author -> author.getName())
                        .collect(Collectors.joining(", "));

                System.out.println("Título: " + result.getTitle());
                System.out.println("Autor: " + autores);
                System.out.println("Géneros: " + result.getSubjects());
                System.out.println("Idiomas: " + result.getLanguages());
                if (result.getFormats().getTextHtml() != null) {
                    System.out.println("Link libro: " + result.getFormats().getTextHtml());
                }
                if (result.getFormats().getImageJpeg() != null) {
                    System.out.println("Link portada: " + result.getFormats().getImageJpeg());
                }
                System.out.println("-----------------------------");
            });
        } else {
            System.out.println("No se encontraron coincidencias con: " + valorBuscado);
        }
    }
    public void buscarYMostrarLibrosPorLenguaje(String lenguaje) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://gutendex.com/books?languages=" + lenguaje;
        GutendexResponse response = restTemplate.getForObject(apiUrl, GutendexResponse.class);

        if (response != null && response.getResults() != null) {
            response.getResults().forEach(result -> {
                String autores = result.getAuthors().stream()
                        .map(author -> author.getName())
                        .collect(Collectors.joining(", "));

                System.out.println("Título: " + result.getTitle());
                System.out.println("Autor: " + autores);
                System.out.println("Idiomas: " + result.getLanguages());
                if (result.getFormats().getTextHtml() != null) {
                    System.out.println("Link libro: " + result.getFormats().getTextHtml());
                }
                if (result.getFormats().getImageJpeg() != null) {
                    System.out.println("Link portada: " + result.getFormats().getImageJpeg());
                }

                System.out.println("-----------------------------");
            });
        } else {
            System.out.println("No se encontraron libros en el lenguaje: " + lenguaje);
        }
    }

    public void mostrarTop10LibrosPorDescargas() {
        List<Libros> top10Libros = libroRepository.findAll().stream()
                .sorted(Comparator.comparing(Libros::getDownloadCount).reversed())
                .limit(10)
                .collect(Collectors.toList());

        top10Libros.forEach(libro -> {


            System.out.println("Título: " + libro.getTitle());
            System.out.println("Autor: "+ libro.getAuthorName());
            System.out.println("Descargas: " + libro.getDownloadCount());
            System.out.println("Portada: "+ libro.getJpegFormat());
            System.out.println("Link al libro: "+ libro.getHtmlFormat());
            System.out.println("-----------------------------");
        });
    }


    public void buscarYGuardarTodosLosLibros() {
        RestTemplate restTemplate = new RestTemplate();
        int pagina = 1;
        int librosGuardados = 0;

        while (true) {
            String apiUrl = "https://gutendex.com/books/?page=" + pagina;
            GutendexResponse response = restTemplate.getForObject(apiUrl, GutendexResponse.class);

            if (response != null && !response.getResults().isEmpty()) {
                List<GutendexResponse.Result> librosPagina = response.getResults();

                for (GutendexResponse.Result libroApi : librosPagina) {
                    if (libroRepository.existsByTitle(libroApi.getTitle())) {
                        System.out.println("El libro con título \"" + libroApi.getTitle() + "\" ya existe en la base de datos. Saltando...");
                        continue;
                    }

                    Libros libro = new Libros();
                    libro.setId(libroApi.getId());
                    libro.setTitle(libroApi.getTitle());
                    if (!libroApi.getAuthors().isEmpty()) {
                        libro.setAuthorName(libroApi.getAuthors().get(0).getName());
                        libro.setAuthorBirthYear(libroApi.getAuthors().get(0).getBirthYear());
                        libro.setAuthorDeathYear(libroApi.getAuthors().get(0).getDeathYear());
                    }
                    libro.setSubjects(libroApi.getSubjects());
                    libro.setLanguages(libroApi.getLanguages());
                    libro.setHtmlFormat(libroApi.getFormats().getTextHtml());
                    libro.setJpegFormat(libroApi.getFormats().getImageJpeg());
                    libro.setDownloadCount(libroApi.getDownloadCount());

                    try {
                        guardarLibro(libro);
                        librosGuardados++;
                    } catch (Exception e) {
                        System.err.println("Error al guardar el libro con ID " + libro.getId() + ": " + e.getMessage());
                    }
                }

                pagina++;
            } else {
                break;
            }
        }

        System.out.println("Se han guardado " + librosGuardados + " libros en la base de datos.");
    }



}
