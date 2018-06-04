package com.gomezoscar.jgroupfilesender.sender;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import com.gomezoscar.jgroupfilesender.utils.Constants;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class GuiFileReceiver extends Application implements EventHandler<ActionEvent>{
	
	protected Button btnSelectDestinationFolder, btnStartReception, btnStopReception;
	private File folderForReceivedFile;
	private Stage stage;
	private Label lblDirectory, lblETA, lblSelectedIP;
	private ComboBox<String> cmbIpAdresses;
	ObservableList<String> ipAdresses;
	String ipAddressForReceiving=null;
	ProgressBar progressBar=null;
	TextArea log;
	ArrayList<Control> controls=new ArrayList<Control>();
	Thread fileReceiverThread;
	public void init(Stage primaryStage){
		ipAdresses=FXCollections.observableArrayList("224.0.0.8", "224.0.0.9", "224.0.0.10", 
				"224.0.0.11", "224.0.0.12",	"224.0.0.13", "224.0.0.14", 
				"224.0.0.15", "224.0.0.16", "224.0.0.17", "224.0.0.18");
		folderForReceivedFile=new File (System.getProperty("user.dir"));
		stage=primaryStage;
		
		lblDirectory=(Label) stage.getScene().lookup("#lblDirectory");
		lblDirectory.setText(folderForReceivedFile.toString());
		
		btnSelectDestinationFolder=(Button) stage.getScene().lookup("#btnSelectFolder");
		btnSelectDestinationFolder.setOnAction(this);
		
		lblETA=(Label) stage.getScene().lookup("#lblETA");
		
		cmbIpAdresses=(ComboBox<String>) stage.getScene().lookup("#cmbIpAdresses");
		cmbIpAdresses.setItems(ipAdresses);
		cmbIpAdresses.setOnAction(this);
		
		
		btnStartReception = (Button) stage.getScene().lookup("#btnStartReception");
		btnStartReception.setOnAction(this);
		
		btnStopReception = (Button) stage.getScene().lookup("#btnStopReception");
		btnStopReception.setOnAction(this);
		
		lblSelectedIP=(Label) stage.getScene().lookup("#selectedIP");
		
		progressBar=(ProgressBar) stage.getScene().lookup("#receivedFileProgress");
		log = (TextArea) stage.getScene().lookup("#log");
		
		controls.add(btnSelectDestinationFolder);
		controls.add(this.cmbIpAdresses);
		controls.add(this.btnStartReception);
		controls.add(log);
		//controls.add(this.btnStopReception);
		
		
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Parent root=FXMLLoader.load(getClass().getResource("Gui.xml.fxml"), 
				ResourceBundle.getBundle("resources.MessagesBundle", Locale.getDefault()));
		
		
		
		primaryStage.setTitle("GuiFileReceiver");
		primaryStage.setScene(new Scene(root, 640, 480));
		init(primaryStage);
		primaryStage.show();

	}

	
	public void handleSelectFolderClick(){
		
		DirectoryChooser folderChooser=new DirectoryChooser();
		File selectedDirectory=folderChooser.showDialog(this.stage);
		
		if (selectedDirectory!=null){
			this.folderForReceivedFile=selectedDirectory;
			lblDirectory.setText(this.folderForReceivedFile.toString());
		}
	}
	
	public void disableControls(){
		for (Control c: controls){
			c.setDisable(true);
		}
	}
	
	public void enableControls(){
		for (Control c: controls){
			c.setDisable(false);
		}
	}
	
	public boolean everythingConfigured(){
		if (this.ipAddressForReceiving==null){
			log.appendText("Ip address not selected");
			log.appendText("\n");
			return false;
		}
		return true;
	}
	
	public void receiveFile() throws IOException{
		FileReceiver fileReceiver = new FileReceiver(this.ipAddressForReceiving, Constants.UDP_PORT);		
		fileReceiver.receiveMetadata();
		ProgressBarObserver observer=new ProgressBarObserver(progressBar, 10);
		observer.setTotalBlocks(fileReceiver.getTotalBlocks());
		System.out.println(observer.totalBlocks);
		fileReceiver.setObserver(observer);
		this.fileReceiverThread=new Thread(fileReceiver);
		this.fileReceiverThread.start();
		//fileReceiver.receiveFile();
		
	}
	@Override
	public void handle(ActionEvent event) {
		if (event.getSource()==this.btnSelectDestinationFolder){
			this.handleSelectFolderClick();
		}
		if (event.getSource()==this.cmbIpAdresses){
			this.ipAddressForReceiving=this.cmbIpAdresses.getValue();
			this.lblSelectedIP.setText(this.ipAddressForReceiving);
		}
		if (event.getSource()==this.btnStartReception){
			if (!this.everythingConfigured()){
				return ;
			}
			log.appendText("Receiving...\n");
			this.disableControls();
			FileReceiver fileReceiver;
			try {
				
				this.receiveFile();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if (event.getSource()==this.btnStopReception){
			this.fileReceiverThread.interrupt();
			this.enableControls();
		}
		
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}



}
