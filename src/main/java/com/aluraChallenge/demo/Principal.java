package com.aluraChallenge.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Principal implements CommandLineRunner {

    @Autowired
    private LibroService libroService;

    public static void main(String[] args) {
        SpringApplication.run(Principal.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        //libroService.buscarYGuardarTodosLosLibros();
        try {
            while (true) {
                System.out.println("Menu:");
                System.out.println("1. Guardar un nuevo libro");
                System.out.println("2. Mostrar todos los libros");
                System.out.println("3. Buscar libros culquier coincidencia");
                System.out.println("4. Buscar libros por lenguaje ");
                System.out.println("5. Mostrar top 10 libros por descargas");
                System.out.println("6. Salir");
                System.out.print("Elige una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (opcion) {
                    case 1:
                        // Guardar un nuevo libro
                        System.out.print("Ingrese el título del libro: ");
                        String tituloGuardar = scanner.nextLine();
                        try {
                            Libros libroGuardado = libroService.buscarYGuardarLibroPorTitulo(tituloGuardar);
                            if (libroGuardado != null) {
                                System.out.println("Libro guardado: " + libroGuardado.getTitle());
                            } else {
                                System.out.println("No se encontró el libro.");
                            }
                        } catch (Exception e) {
                            System.err.println("Error al guardar el libro");
                        }
                        break;
                    case 2:
                        // Mostrar todos los libros
                        try {
                            System.out.println("Libros en la base de datos:");
                            libroService.obtenerTodosLosLibros().forEach(libro -> {
                                System.out.println("ID: " + libro.getId() + ", Título: " + libro.getTitle() + ", Autores: " + libro.getAuthorName());
                            });
                        } catch (Exception e) {
                            System.err.println("Error al mostrar los libros: " + e.getMessage());
                        }
                        break;
                    case 3:
                        // Buscar libros por título y mostrar resultados
                        try {
                            System.out.print("Introduce el título del libro: ");
                            String tituloBuscar = scanner.nextLine();
                            libroService.buscarYMostrarLibros(tituloBuscar);
                        } catch (Exception e) {
                            System.err.println("Error al buscar libros por título: " + e.getMessage());
                        }
                        break;
                    case 4:
                        // Buscar libros por lenguaje y mostrar resultados
                        try {
                            System.out.print("Introduce el lenguaje (en, es, fr, etc.): ");
                            String lenguaje = scanner.nextLine();
                            libroService.buscarYMostrarLibrosPorLenguaje(lenguaje);
                        } catch (Exception e) {
                            System.err.println("Error al buscar libros por lenguaje: " + e.getMessage());
                        }
                        break;
                    case 5:
                        // Mostrar top 10 libros por descargas
                        try {
                            libroService.mostrarTop10LibrosPorDescargas();
                        } catch (Exception e) {
                            System.err.println("Error al mostrar el top 10 de libros por descargas: " + e.getMessage());
                        }
                        break;
                    case 6:
                        // Salir
                        System.out.println("Saliendo...");
                        return;
                    default:
                        System.out.println("Opción no válida. Por favor, elige una opción válida.");
                }
            }
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
