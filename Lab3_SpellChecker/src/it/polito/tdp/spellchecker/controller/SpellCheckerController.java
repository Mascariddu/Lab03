package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dizionario;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SpellCheckerController{
	
	private Dizionario d;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> ComboBox;

    @FXML
    private TextField TxtInserimento;

    @FXML
    private Button SpellCheckButton;

    @FXML
    private TextArea TxtResult;

    @FXML
    private Label LabelResult;

    @FXML
    private Button ClearTextButton;

    @FXML
    private Label LabelTime;
    
    @FXML
    void ComboSelection(ActionEvent event) {
    	ComboBox.setDisable(true);
    	SpellCheckButton.setDisable(false);
    	TxtInserimento.setEditable(true);
    }

    @FXML
    void doClearText(ActionEvent event) {
    	TxtInserimento.clear();
    	TxtResult.clear();
    	LabelResult.setText("The text contains ... errors");
    	LabelTime.setText("Spell check completed in ... seconds");
    	d.pulisciDizionario();
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	
    	TxtResult.clear();
    	double time = System.nanoTime();
    	int errore=0;
    	String s="";
    	d.loadDictionary(ComboBox.getValue());
    	List<String> l = new ArrayList();
    	String c[]= TxtInserimento.getText().replaceAll("[.,\\/#?!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", " ").split(" ");
    	
    	for(int i=0; i < c.length; i++) {
    		if(!c[i].equals(""))
    		l.add(c[i].toLowerCase());
    	}
    	
    	List<RichWord> text = d.spellCheckTextDichotomic(l);
    	for(RichWord r: text) {
    		if(!r.isEsattezza()) {
    			 s += r.getTesto()+"\n";
    			 errore++;
    		}
    	}
    	
    	TxtResult.appendText(s);
    	LabelResult.setText("The text contains "+errore+" errors");
    	ComboBox.setDisable(false);
    	double time2 = System.nanoTime();
    	LabelTime.setText("Spell check completed in "+(time2 - time)+" seconds");
    	text.clear();
    }

    @FXML
    void initialize() {
        assert ComboBox != null : "fx:id=\"ComboBox\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert TxtInserimento != null : "fx:id=\"TxtInserimento\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert SpellCheckButton != null : "fx:id=\"SpellCheckButton\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert TxtResult != null : "fx:id=\"TxtResult\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert LabelResult != null : "fx:id=\"LabelResult\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert ClearTextButton != null : "fx:id=\"ClearTextButton\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert LabelTime != null : "fx:id=\"LabelTime\" was not injected: check your FXML file 'SpellChecker.fxml'.";

    }

	public void setD(Dizionario d) {
		this.d = d;
		ComboBox.getItems().addAll("English","Italian");
	}
}