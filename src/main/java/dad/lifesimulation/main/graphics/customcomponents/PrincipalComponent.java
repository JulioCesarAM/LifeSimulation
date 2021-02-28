package dad.lifesimulation.main.graphics.customcomponents;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.lifesimulation.main.draw.DrawableFactory;
import dad.lifesimulation.main.entities.actor.Cell;
import dad.lifesimulation.main.graphics.customcomponents.component.CellStatsView;
import dad.lifesimulation.main.graphics.customcomponents.component.CellStatsViewEditable;
import dad.lifesimulation.main.utils.Coordinates;
import dad.lifesimulation.main.utils.Dimension;
import dad.lifesimulation.main.utils.GUIGame;
import dad.lifesimulation.main.utils.InitGameComponents;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.adapter.JavaBeanDoubleProperty;
import javafx.beans.property.adapter.JavaBeanDoublePropertyBuilder;
import javafx.beans.property.adapter.JavaBeanIntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.beans.property.adapter.JavaBeanObjectProperty;
import javafx.beans.property.adapter.JavaBeanObjectPropertyBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.converter.NumberStringConverter;

public class PrincipalComponent {

	private GUIGame guigame;
	private InitGameComponents processingGame;
	private DrawableFactory drawableFactory;

    @FXML
    private Tab visualizeTab;

    @FXML

    private Tab editableTab;

	private CellStatsView cellView;
	private CellStatsViewEditable cellEditable;
	
	private Scene scene;
	@FXML
	private ToggleGroup editor;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private GridPane view;

	@FXML
	private ToggleButton pause;

	@FXML
	private ToggleButton edit;

	@FXML
	private Button fastForward;

	@FXML
	private ToggleButton btnAddCell;

	@FXML
	private ToggleButton btnAddSpikes;

	@FXML
	private ToggleButton btnAddWall;

	@FXML
	private ToggleButton btnAddFood;

	@FXML
	private ToggleButton btnDeleteEntity;


	@FXML
	private Canvas canvas;

    @FXML
    void btFastForward(ActionEvent event) {

    }
    
    private void editorMode (MouseEvent event)
    {
    	
    	btnAddCell.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Image cursorImage = new Image(getClass().getResource("/fxml/PrincipalComponent.fxml").toString());
				scene.setCursor(Cursor.CROSSHAIR);
				System.out.println("cambio");
			}
		});
    	
    	if (btnAddCell.isSelected())
    	{
    		Coordinates coordinates = new Coordinates((int)event.getX(), (int)event.getY());
    		Dimension dimension = new Dimension(20, 20);
    		
    		drawableFactory.drawFromCanvas(true);
    		drawableFactory.createCellEntity(coordinates, dimension, false);
    		drawableFactory.drawFromCanvas(true);
    		
    		
    		
    	}
    	
    	else if (btnAddWall.isSelected())
    	{
    		Coordinates coordinates = new Coordinates((int)event.getX(), (int)event.getY());
    		Dimension dimension = new Dimension(20, 20);
    		
    		drawableFactory.drawFromCanvas(true);
    		drawableFactory.createWallEntity(coordinates, dimension);
    		drawableFactory.drawFromCanvas(true);
    	}
    	
    	else if (btnAddFood.isSelected())
    	{
    		Coordinates coordinates = new Coordinates((int)event.getX(), (int)event.getY());
    		Dimension dimension = new Dimension(20, 20);
    		
    		drawableFactory.drawFromCanvas(true);
    		drawableFactory.createFood(coordinates, dimension);
    		drawableFactory.drawFromCanvas(true);
    	}
    	
    	else if (btnDeleteEntity.isSelected())
    	{
    		Coordinates coordinates = new Coordinates((int)event.getX(), (int)event.getY());
    		drawableFactory.deleteIn(coordinates);
    	}
    	else
    	{
    		scene.setCursor(Cursor.DEFAULT);
    	}
    }




	@FXML
	void onClickedCanvas(MouseEvent event) {

		// ditor(event);
	}



	@FXML
	void onEdit(ActionEvent event) {
		//this.pane=cellEditable.getView();
		//cellEditable.test();
	}

	@FXML
	void onPlayPause(ActionEvent event) {
		if (pause.isSelected()) {

			guigame.stop();
			if (!processingGame.isPaused())
				processingGame.toPause(true);
		} else {

			guigame.start();

			if (processingGame.isPaused())
				processingGame.toPause(false);
		}

	}

	

	@FXML
    void onPressedCanvas(MouseEvent event) {
    	if (edit.isSelected())
    		editorMode(event);
    	else
    	{
    		
    		Coordinates coordinates = new Coordinates((int)event.getX(), (int)event.getY());
    		
    		Optional<Cell> maybe_cell = processingGame.getCellIn(coordinates);
    		if (maybe_cell.isPresent())
    		{
    			System.out.println("cogí una célula");
    			bindCell(maybe_cell.get());
    		}
    	}
    }
    
    
    private void bindCell(Cell cell)
    {		
    	cellView.getCoordinatesProperty().setValue(cell.getCoordinates().toString());
    	cellView.getDimensionProperty().setValue(cell.getDimension().toString());
    	cellView.setArmorProperty((Integer.toString(cell.getStatistics().getArmor())));
    	cellView.setEnergyProperty((Integer.toString(cell.getStatistics().getEnergy())));
    	cellView.setHealthPointsProperty((Integer.toString(cell.getStatistics().getHealth())));
    	System.out.println("Me bindeaste");
    }
    
    public PrincipalComponent() {
    	try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PrincipalComponent.fxml"));
			//loader.setRoot(this);
			loader.setController(this);
			loader.load();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void initialize() {
		assert view != null : "fx:id=\"view\" was not injected: check your FXML file 'PrincipalComponent.fxml'.";
		assert pause != null : "fx:id=\"pause\" was not injected: check your FXML file 'PrincipalComponent.fxml'.";
		assert edit != null : "fx:id=\"play\" was not injected: check your FXML file 'PrincipalComponent.fxml'.";
		assert fastForward != null
				: "fx:id=\"fastForward\" was not injected: check your FXML file 'PrincipalComponent.fxml'.";
		assert btnAddCell != null
				: "fx:id=\"btnAddCell\" was not injected: check your FXML file 'PrincipalComponent.fxml'.";
		assert btnAddSpikes != null
				: "fx:id=\"btnAddSpikes\" was not injected: check your FXML file 'PrincipalComponent.fxml'.";
		assert btnAddWall != null
				: "fx:id=\"btnAddWall\" was not injected: check your FXML file 'PrincipalComponent.fxml'.";
		assert btnAddFood != null
				: "fx:id=\"btnAddFood\" was not injected: check your FXML file 'PrincipalComponent.fxml'.";
		assert btnDeleteEntity != null
				: "fx:id=\"btnDeleteEntity\" was not injected: check your FXML file 'PrincipalComponent.fxml'.";
		assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'PrincipalComponent.fxml'.";
		System.out.println("_Inicializado");
		cellEditable = new CellStatsViewEditable();
		cellView = new CellStatsView();
		this.editableTab.setContent(cellEditable.getView());
		this.visualizeTab.setContent(cellView.getView());
	}

	public Canvas getCanvasElement() {
		return canvas;
	}

	public Parent getView() {
		return view;
	}

	public void setGame(InitGameComponents processingGame) {
		this.processingGame = processingGame;
	}

	public void setGUIGame(GUIGame guiGame) {
		this.guigame = guiGame;
	}

	public void setFactory(DrawableFactory levelGUI_creator) {
		this.drawableFactory = levelGUI_creator;
	}
	
	public void setScene(Scene scene)
	{
		this.scene = scene;
	}
}
