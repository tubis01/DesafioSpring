package com.aluracursos.DesafioSpring.principal;

import com.aluracursos.DesafioSpring.models.Datos;
import com.aluracursos.DesafioSpring.models.DatosLibros;
import com.aluracursos.DesafioSpring.service.ConsumoAPI;
import com.aluracursos.DesafioSpring.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConvierteDatos convierteDatos = new ConvierteDatos();

    Scanner scanner = new Scanner(System.in);

    public void MuestraMenu(){

        String json = consumoAPI.obtenerDatos(URL_BASE);
        Datos datos = convierteDatos.obtenerDatos(json, Datos.class);

        System.out.println("Libros encontrados: " + datos);

        // top 10 libros mas descargados
        System.out.println("Top 10 libros mas descargados: ");
        datos.resultados().stream()
                .sorted(Comparator.comparing(DatosLibros:: numeroDescargas).reversed())
                .limit(10)
                .map(l -> l.titulo().toUpperCase() + " - " + l.numeroDescargas() + " descargas" )
                .forEach(System.out::println);

        // Buscar libro por nonbre

        System.out.println("Buscar libro por nombre: ");
        String nombreLibro = scanner.nextLine();

        json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+")) ;

        Datos datosBusqueda = convierteDatos.obtenerDatos(json, Datos.class);

        Optional<DatosLibros> libro = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst();

        if (libro.isPresent()){
            System.out.println("Libro encontrado: " + libro.get());
        } else {
            System.out.println("Libro no encontrado");
        }

        //generando estadisticas
        System.out.println("Generando estadisticas");

        DoubleSummaryStatistics estadisticas = datos.resultados().stream()
                .filter(l -> l.numeroDescargas() > 0)
                .collect(Collectors.summarizingDouble(DatosLibros::numeroDescargas));

        System.out.println("Cantidad maxima de descargas: " + estadisticas.getMax());
        System.out.println("Cantidad minima de descargas: " + estadisticas.getMin());
        System.out.println("Promedio de descargas: " + estadisticas.getAverage());
        System.out.println("Total de descargas: " + estadisticas.getSum());
        System.out.println("Cantidad de libros con descargas: " + estadisticas.getCount());









    }
}
