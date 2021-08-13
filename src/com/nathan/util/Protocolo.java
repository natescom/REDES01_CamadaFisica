package com.nathan.util;
/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 29/07/2021
 * Ultima alteracao: 29/07/2021
 * Nome: Protocolo
 * Funcao: Define as funcoes dos codificadores
 * ************************************************************** */
public abstract class Protocolo {
  abstract int[] codificar(int[] bits);
  abstract int[] decodificar(int[] bits);
}
