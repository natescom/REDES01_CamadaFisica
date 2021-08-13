package com.nathan.util;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 30/07/2021
 * Ultima alteracao: 30/07/2021
 * Nome: RedeGUI
 * Funcao: Gerencia as alteracoes que ha na interface para representar
 * a rede
 * ************************************************************** */
public class RedeGUI {
  private final ImageView arquivo;
  private final TextField textField;
  private final Text text_result;
  private final Button btn_enviar;
  private final Label lbl_log;

  public RedeGUI(ImageView arquivo, TextField textField, Text text, Button btn_enviar, Label lbl_log) {
    this.arquivo = arquivo;
    this.textField = textField;
    this.text_result = text;
    this.btn_enviar = btn_enviar;
    this.lbl_log = lbl_log;
  }

  /**
   * Pega o texto no editText
   * @return
   */
  public String getText_result(){
    return textField.getText();
  }

  /**
   * Publica o progresso no envio da mensagem
   * Altera a coordenada da imagem
   * @param i
   */
  public void publishProgress(float i){
    double xINICIAL = 95;
    double xFINAL = 560;
    double xATUAL = (xFINAL - xINICIAL) * i + xINICIAL;
    arquivo.setLayoutX(xATUAL);
    if((i>0.15 && i<0.40) && arquivo.getImage() != Gallery.ARQUIVOCOD){
      arquivo.setImage(Gallery.ARQUIVOCOD);
      Platform.runLater(() -> lbl_log.setText("Camada Fisica Transmissora"));
    }
    if((i>0.65 && i < 0.85) && arquivo.getImage() != Gallery.ARQUIVOCOD){
      arquivo.setImage(Gallery.ARQUIVOCOD);
      Platform.runLater(() -> lbl_log.setText("Camada Fisica Receptora"));
    }
    if(i>0.40 && i<0.65 && arquivo.getImage() != Gallery.SINAL){
      arquivo.setImage(Gallery.SINAL);
      Platform.runLater(() -> lbl_log.setText("Meio de comunicacao"));
    }
    if(i<0.15 && arquivo.getImage() != Gallery.ARQUIVO){
      arquivo.setImage(Gallery.ARQUIVO);
      Platform.runLater(() -> lbl_log.setText("Aplicacao Transmissora"));
    }
    if(i>0.85 && arquivo.getImage() != Gallery.ARQUIVO){
      arquivo.setImage(Gallery.ARQUIVO);
      Platform.runLater(() -> lbl_log.setText("Aplicacao Receptora"));
    }
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Publica a mensagem recebida
   * @param s
   */
  public void publishText(String s){
    Platform.runLater(() -> {
      text_result.setText(s);
      lbl_log.setText("Mensagem recebida");
    });
    btn_enviar.setDisable(false);
  }

}
