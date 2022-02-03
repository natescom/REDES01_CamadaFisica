package com.nathan.controller;

import com.nathan.model.Binaria;
import com.nathan.model.Manchester;
import com.nathan.model.ManchesterDiferencial;
import com.nathan.model.Protocolo;
import com.nathan.util.Rede;
import com.nathan.util.RedeGUI;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller_TelaPrincipal implements Initializable {

  public HBox hbox_led;
  public Label lbl_men;
  public Button btn_enviar;
  public TextField txt_men;
  public MenuButton menu_cod;
  public CheckMenuItem menuItemBinario;
  public CheckMenuItem menuItemManchester;
  public CheckMenuItem menuItemManchesterDiferencial;
  public Label lbl_protocolo;
  public Slider sld_speed;
  public TextArea txt_disp01;
  public TextArea txt_disp02;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    menuItemBinario.setSelected(true);
  }

  private Protocolo getProtocoloFromMenu(){
    if(menuItemBinario.isSelected())
      return new Binaria();
    if(menuItemManchester.isSelected())
      return new Manchester();
    if(menuItemManchesterDiferencial.isSelected())
      return new ManchesterDiferencial();
    return null;
  }

  public void onClick(ActionEvent e) {

    if(e.getSource().equals(btn_enviar)) {
      if(txt_men.getText().equals("")) return;

      lbl_men.setText("Aguardando Mensagem");
      lbl_men.setTextFill(Paint.valueOf("#868686"));

      Rede rede = new Rede(new RedeGUI(txt_men,lbl_men,hbox_led,btn_enviar, sld_speed, txt_disp01, txt_disp02),getProtocoloFromMenu());
      rede.aplicacaoTransmissora();
      btn_enviar.setDisable(true);
    }

    if(e.getSource().equals(menuItemBinario)){
      menuItemBinario.setSelected(true);
      menuItemManchester.setSelected(false);
      menuItemManchesterDiferencial.setSelected(false);
      lbl_protocolo.setText("Binario");
    }
    if(e.getSource().equals(menuItemManchester)){
      menuItemBinario.setSelected(false);
      menuItemManchester.setSelected(true);
      menuItemManchesterDiferencial.setSelected(false);
      lbl_protocolo.setText("Manchester");
    }
    if(e.getSource().equals(menuItemManchesterDiferencial)){
      menuItemBinario.setSelected(false);
      menuItemManchester.setSelected(false);
      menuItemManchesterDiferencial.setSelected(true);
      lbl_protocolo.setText("Manchester-Diferencial");
    }
  }
}
