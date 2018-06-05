package gui;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.GridPane;
import logic.Game;
import logic.Position;

public class GameController {
	@FXML public Label turnLabel;
	@FXML public GridPane guiBoard;
	@FXML public GridPane unitPane;
	@FXML public GridPane menuPane;
	@FXML public Button endButton;
	@FXML public Button loadButton;
	
	private Game game;
	private Position startPos;
	private Position endPos;
	private ImageView oldView;
	private final int LEN = 50;
	public GameController()
	{
		game = new Game();
		startPos = new Position(-1,-1);
		endPos = new Position(-1,-1);
		oldView = null;
	}
	public void endTurn()
	{
		if (turnLabel.getText().equals("P1 Turn"))
			turnLabel.setText("P2 Turn");
		else
			turnLabel.setText("P1 Turn");
	}
	
	public void init()
	{
		loadButton.setVisible(false);
		int num = 0;
		Image ground1 = new Image("ground1.png"); 
		Image ground2 = new Image("ground2.png"); 
		Image ground3 = new Image("ground3.png"); 
		
		
		for( Node node : guiBoard.getChildren())
		{
			if(node instanceof ImageView)
			{
				ImageView cell = (ImageView) node;
				num = (int) (Math.random() * 6);
				if(num < 2)
					cell.setImage(ground1);
				else
					if(num < 4)
						cell.setImage(ground2);
					else
						cell.setImage(ground3);
				//cell.setOnMouseClicked(this::location);
			}
		}
		for( Node node : unitPane.getChildren())
		{
			if(node instanceof ImageView)
			{
				ImageView unit = (ImageView) node;
				if(num < 32 || num > 240)
				{
					unit.setImage(new Image("KnightBlue.png"));
				}
				else
					unit.setImage(null);
				unit.setOnMouseClicked(this::location);
				num++;
			}
		}
		num = 0;
		for(int col = 0; col < 16; col++)
		{
			for(int row = 0; row < 16; row++)
			{
				ContextMenu cMenu;
				MenuButton mButton = new MenuButton();
				mButton.setPrefHeight(LEN);
				mButton.setPrefWidth(LEN);
				mButton.setOpacity(0);	
				ButtonItem item = new ButtonItem(mButton, "move");
				item.setOnAction(this::location);
				cMenu = new ContextMenu(item);
				
				mButton.setContextMenu(cMenu);
				mButton.getItems().addAll(item);
				menuPane.add(mButton, col, row);
				cMenu.setOnShowing(e -> e.consume());
				mButton.setOnContextMenuRequested(e ->
				{
					e.consume();
					mButton.hide();
					System.out.print("something");
					//ContextMenu menu = (ContextMenu)e.getSource();
					//showMenu((ButtonItem)(menu.getItems().get(0)));
				});
				mButton.setOnMousePressed(e ->
				{
					e.consume();
					showMenu((ButtonItem)mButton.getItems().get(0));
				});
				mButton.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, e ->
				{
					e.consume();
					System.out.println("handled");
				});
			}
		}
		//menuPane.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> e.consume());
	} 
	
	/**
	 * Mouse Event to get two positions on the gridpane
	 * @param e	event
	 */
	public void location(Event e)
	{
		
		ButtonItem item = (ButtonItem) e.getSource();
		MenuButton cell = item.getMenuButton();
		//GridPane pane = (GridPane) cell.getParent();
		int row = GridPane.getRowIndex(cell);
		int col = GridPane.getColumnIndex(cell);
		/*
		if(startPos.getX() < 0 && oldView == null && cell.getImage() != null)
		{
			startPos.setPos(row, col);
			oldView = cell;
		}
		elsep,
			if(oldView != null && cell.getImage() == null)
			{
				endPos.setPos(row, col);
				moveUnit(cell);					
			} */
		System.out.println(row + " " + col);
	}

	/**
	 * moves the unit across gridPane
	 * @param cell the image view where the image is to be move to
	 */
	private void moveUnit(ImageView cell)
	{
		cell.setImage(oldView.getImage());
		oldView.setImage(null);
		oldView = null;
		startPos.setPos(-1, -1);
	}
	
	private void showMenu(ButtonItem item)
	{
		MenuButton button = item.getMenuButton();
		Position pos = new Position(GridPane.getRowIndex(button), GridPane.getColumnIndex(button));
		//Position pos = new Position(row, col);
		ImageView view = (ImageView)getNode(unitPane,pos.getX(),pos.getY());
		if(view.getImage() != null)
		{			
			System.out.println("shown");
			//MenuButton button = (MenuButton)(getNode(menuPane,row,col));
			button.show();
		}
	}
	@FXML
	public void resetPos()
	{
		startPos.setPos(-1,-1);
		System.out.println("reset");
	}
	
	private Node getNode(GridPane pane, int row, int col)
	{
		Node result = null;
		for(Node cell: pane.getChildren())
		{
			if(GridPane.getRowIndex(cell) == row && GridPane.getColumnIndex(cell) == col)
			{
				result = cell;
				break;
			}
		}
		return result;
	}
}
