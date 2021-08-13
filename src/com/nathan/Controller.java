package com.nathan;

import com.nathan.util.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 29/07/2021
 * Ultima alteracao: 29/07/2021
 * Nome: Controller
 * Funcao: Controla as alteracoes da interface
 * ************************************************************** */
public class Controller implements Initializable {

  public ChoiceBox cbx_Algoritmo;
  public TextField txtfield_mensagem;
  public Text lbl_resultado;
  public Button btn_enviar;
  public ImageView img_dados;
  public Label lbl_log;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    String[] opcoes = {"Binario", "Manchester","Manchester Diferencial"};
    cbx_Algoritmo.setItems(FXCollections.observableArrayList(opcoes));
    cbx_Algoritmo.setValue(opcoes[0]);
    cbx_Algoritmo.setTooltip(new Tooltip("Selecione o protocolo de codificacao da rede"));
  }

  /**
   * Recebe os eventos de clique
   * @param event
   */
  public void onClick(ActionEvent event) {
    if(event.getSource().equals(btn_enviar)){
      if(txtfield_mensagem.getText().equals(""))
        return;
      Protocolo protocolo;
      RedeGUI redeGUI = new RedeGUI(img_dados, txtfield_mensagem, lbl_resultado, btn_enviar, lbl_log);
      switch (cbx_Algoritmo.getSelectionModel().getSelectedIndex()){
        case 1:
          protocolo = new Manchester();
          break;
        case 2:
          protocolo = new ManchesterDiferencial();
          break;
        default:
          protocolo = new Binaria();
      }
      Rede rede = new Rede(redeGUI,protocolo);
      rede.aplicacaoTransmissora();
      btn_enviar.setDisable(true);
    }
  }
}
