package com.aluracursos.DesafioSpring.service;

public interface IConvierteDatos
{
    <T> T obtenerDatos(String json, Class<T> clase);
}
