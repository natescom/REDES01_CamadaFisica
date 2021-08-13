package com.nathan.util;

import java.util.Arrays;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 29/07/2021
 * Ultima alteracao: 29/07/2021
 * Nome: Rede
 * Funcao: Executa os passos de transmissao de mensagem na rede
 * ************************************************************** */
public class Rede {
  private final RedeGUI gui;
  private Protocolo protocolo;

  public Rede(RedeGUI gui, Protocolo protocolo) {
    this.gui = gui;
    this.protocolo = protocolo;
  }

  /**
   * Pega o conteudo da caixa de texto e manda pra camada de aplicaxao transmissora
   */
  public void aplicacaoTransmissora(){
    camadaDeAplicacaoTransmissora(gui.getText_result());
  }

  /**
   * Pega a mensagem e transforma em vetor de bit
   */
  private void camadaDeAplicacaoTransmissora(String s){
    // Transforma a string da mensagem em binario em vetor de inteiro //
    char[] chars = convertStringToBinary(s).toCharArray();
    int[] bits = new int[chars.length];
    for (int i = 0; i < chars.length; i++) {
      bits[i] = Character.getNumericValue(chars[i]);
    }
    camadaFisicaTransmissora(bits, protocolo);
  }

  /**
   * Aplica alguma codificacao na mensagem e envia
   */
  private void camadaFisicaTransmissora(int[] quadro, Protocolo protocolo){
    int[] fluxoBrutoDeBits = protocolo.codificar(quadro);
    meioDeComunicacao(fluxoBrutoDeBits);
  }

  /**
   * Ativa o envio
   * @param fluxoBrutoDeBits
   */
  private void meioDeComunicacao(int[] fluxoBrutoDeBits){
    int[] pontoA = fluxoBrutoDeBits ;
    int[] pontoB = new int[pontoA.length];
    new Thread(() -> {
      for (int i = 0; i < pontoA.length; i++) {
        pontoB[i] = pontoA[i];
        gui.publishProgress((float)i/(float)pontoA.length);
      }
      camadaFisicaReceptora(fluxoBrutoDeBits, protocolo);
    }).start();
  }

  /**
   * Pega o fluxo bruto de bits e decodifica
   * @param fluxoBrutoDeBits
   * @param protocolo
   */
  private void camadaFisicaReceptora(int[] fluxoBrutoDeBits, Protocolo protocolo){
    int[] quadro = protocolo.decodificar(fluxoBrutoDeBits);
    camadaDeAplicacaoReceptora(quadro);
  }

  /**
   * Pega os bits recebidos e transforma em mensagem
   */
  private void camadaDeAplicacaoReceptora(int[] quadro){
    String mensagem = "";
    for (int i = 0; i < quadro.length; i++) {
      mensagem+= quadro[i];
    }
    aplicacaoReceptora(convertBinaryToString(mensagem));
  }

  /**
   * Publica o texto recebido
   * @param s
   */
  private void aplicacaoReceptora (String s){
    gui.publishText(s);
  }

  /**
   * Converte uma string para uma string de binario
   * @param input
   * @return
   */
  public static String convertStringToBinary(String input) {
    StringBuilder result = new StringBuilder();
    char[] chars = input.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      result.append(String.format("%8s", Integer.toBinaryString(chars[i])).replaceAll(" ", "0"));
    }
    return result.toString();
  }

  /**
   * Converte uma string de binario me uma string
   * @param input
   * @return
   */
  public static String convertBinaryToString(String input) {
    StringBuilder sb = new StringBuilder();
    Arrays.stream(input.split("(?<=\\G.{8})")).forEach(s -> sb.append((char) Integer.parseInt(s, 2)));
    return sb.toString();
  }
}
