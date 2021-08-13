package com.nathan.util;

import javafx.scene.image.Image;
/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 23/03/2021
 * Ultima alteracao: 27/03/2021
 * Nome: Gallery
 * Funcao: Lista e instanica todas as imagens para serem utilizadas
 * ***************************************************************/
public abstract class Gallery {

  private static final String path = "/com/nathan/res/img";

  public static final Image ARQUIVO = new Image(Gallery.class.getResourceAsStream(path+"/file_lines-0.png"));
  public static final Image ARQUIVOCOD = new Image(Gallery.class.getResourceAsStream(path+"/file_sorted_lock-1.png"));
  public static final Image SINAL = new Image(Gallery.class.getResourceAsStream(path+"/sinal.gif"));
}
