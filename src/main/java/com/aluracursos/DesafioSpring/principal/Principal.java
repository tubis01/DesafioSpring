package com.aluracursos.DesafioSpring.principal;

import com.aluracursos.DesafioSpring.service.ConsumoAPI;
import com.aluracursos.DesafioSpring.service.ConvierteDatos;

public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();

    public void MuestraMenu(){

        String json = consumoAPI.obtenerDatos(URL_BASE);

    }
}
