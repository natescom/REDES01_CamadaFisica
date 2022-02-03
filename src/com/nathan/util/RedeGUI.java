package com.nathan.util;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;

public class RedeGUI {
  private final TextField txtInput;
  private final Label lblDisp02;
  private final HBox hboxLed;
  private final Button btnEnviar;
  private final Slider sld_speed;
  private final TextArea txt_emissor;
  private final TextArea txt_receptor;

  public RedeGUI(TextField txtInput, Label lblDisp02, HBox hboxLed, Button btnEnviar, Slider sld_speed, TextArea txt_emissor, TextArea txt_receptor) {
    this.txtInput = txtInput;
    this.lblDisp02 = lblDisp02;
    this.hboxLed = hboxLed;
    this.btnEnviar = btnEnviar;
    this.sld_speed = sld_speed;
    this.txt_emissor = txt_emissor;
    this.txt_receptor = txt_receptor;
  }

  public Slider getSld_speed() {
    return sld_speed;
  }

  public String getInput(){
    return txtInput.getText();
  }

  public void publishSignal(int[] bits){
    DisplayLed.show(bits, hboxLed, sld_speed);
  }

  public void publishTxtEmissor(String s){
    Platform.runLater(() -> {
      txt_emissor.setText(txt_emissor.getText() + s);
      txt_emissor.positionCaret(txt_emissor.getText().length());
    });
  }

  public void publishTxtReceptor(String s){
    Platform.runLater(() -> {
      txt_receptor.setText(txt_receptor.getText() + s);
      txt_receptor.positionCaret(txt_receptor.getText().length());
    });
  }

  public void publishResult(String s){
    Platform.runLater(() -> {
      lblDisp02.setText(s);
      lblDisp02.setTextFill(Paint.valueOf("#000000"));
      btnEnviar.setDisable(false);
    });
  }

  public void clearTxt(){
    Platform.runLater(() ->{
      txt_emissor.setText("");
      txt_receptor.setText("");
    } );
  }

  public void publishTxtEmissor(){
    Platform.runLater(() -> {
      txt_emissor.setText(txt_emissor.getText().substring(0,txt_emissor.getText().length()-2));
      txt_emissor.positionCaret(txt_emissor.getText().length());
    });
  }
}
