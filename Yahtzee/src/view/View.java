package view;
/**
 * <p>The view acts as a tool for the user to interact between the model and the controller.This class in particular is the View of the 
 *    MVC structure we are using, and its job is to store all the Graphical User Interface components.<p>
 *    
 * @author Jacob Koko
 */
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import model.Category;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.lang.reflect.Method;
import java.util.Vector;

import controller.Controller;

public class View extends JFrame 
{
   private static final long serialVersionUID = 1L;
   private Controller myController;
   private JFrame myMainView;
   private JPanel myDicePanel;
   private JPanel myButtonsPanel;
   private Container myContainer;
   private ButtonListener myButtonListener;
   private JTable myTable;
   private JButton myRollbtn, myOnes, myTwos, myThrees, myFours, myFives, mySixes, my3ofKind, 
                   my4ofKind, myChance, myFullHouse, myYahtzee, myLrgStrt, mySmlStrt;
   
   private JButton[] myDieBtns; 
   private JLabel myGameUpdateLabel;
   private int[]myPlayerUpperScores;
   private int[]myPlayerLowerScores;
   private int[]myComputerPlayerUpperScores;
   private int[]myComputerPlayerLowerScores; 
	
   public View(Controller controller)
   {
	   myController = controller;
	   displayGUI();
   }
   
   /**
    * <p>This method is called to display all the contents of the GUI. The GUI leverages layouts to dynamically adjust content to the
    *    user's liking. Multiple nested panels allow for the development of separate entities that interact smoothly. The main panel
    *    is the center panel and it holds the main bulk of the Graphical User Interface.<p>
    */
   public void displayGUI()
   {
	   myMainView = new JFrame();
	   myMainView.setSize(1150, 700);
	   myMainView.setTitle("Java Yahtzee");
	   myMainView.setDefaultCloseOperation(EXIT_ON_CLOSE);
	   myContainer = myMainView.getContentPane();
	   
	   myPlayerUpperScores = new int[6];
	   myPlayerLowerScores = new int[7];
	   
	   myComputerPlayerUpperScores = new int[6];
	   myComputerPlayerLowerScores = new int[7];
	   
	   /*
	    * Instantiate score card table model
	    */
	   setScoreCardTable();
	   JScrollPane myScrollPane = new JScrollPane(myTable);
	   myScrollPane.setPreferredSize(new Dimension(390, HEIGHT));
	   myContainer.add(myScrollPane,  BorderLayout.LINE_END);
	    
	  /*
	   * Add the main center panel
	   */
	   JPanel centerPanel = new JPanel();
	   centerPanel.setLayout(null);
	   
	   if(myController.getGameInPlay())
	   {
		   myRollbtn = new JButton("Roll Dice");
	   }
	   else
	   {
		   myRollbtn = new JButton("Start Game");
	   }
	   
	   myRollbtn.setSize(100, 25);
	   myRollbtn.setBounds(300, 400, 100, 30);
	   myRollbtn.setFont(new Font("Georgia", Font.PLAIN, 12));
	   associateListener(myRollbtn,300);
	   
	   myGameUpdateLabel = new JLabel();
	   myGameUpdateLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
       myGameUpdateLabel.setText("");
       myGameUpdateLabel.setBounds(200, 80, 400, 20);
	   centerPanel.add(myGameUpdateLabel);
	   
	   centerPanel.add(myRollbtn);
	   myContainer.add(centerPanel, BorderLayout.CENTER);
		
	  /*
	   * Add dice panel within the main center panel. This is where dice images are displayed.
	   */
	   myDicePanel = new JPanel();
	   myDicePanel.setLayout(new FlowLayout());
	   myDicePanel.setPreferredSize(new Dimension(300, 100));
	   myDicePanel.setBounds(50, 100, 600, 290);
	  
	   myDieBtns = new JButton[5];
	   for(int i = 0; i < myDieBtns.length; i++)
	   {
		  myDieBtns[i] = new JButton();
		  myDieBtns[i].setIcon(new ImageIcon("Resources/dice-1.png"));
		  myDieBtns[i].setEnabled(false);
	      myDicePanel.add(myDieBtns[i]);
	      myDieBtns[i].setPressedIcon(new ImageIcon("Resources/checkmark.png"));
	      associateListener(myDieBtns[i], i);
	   }
	
	   TitledBorder border;
	   border = BorderFactory.createTitledBorder("Dice");
	   border.setTitleFont(new Font("Georgia", Font.BOLD, 14));
	   myDicePanel.setBorder(border);
	    
	   centerPanel.add(myDicePanel);
	   
	   /*
	    * button panel configuration
	    */
	   
	   myButtonsPanel = new JPanel();
	   myButtonsPanel.setLayout(new FlowLayout());
	   myButtonsPanel.setPreferredSize(new Dimension(300, 100));
	   myButtonsPanel.setBounds(50, 470, 600, 180);
	   
	   TitledBorder border2;
	   border2 = BorderFactory.createTitledBorder("Categories To Fill");
	   border2.setTitleFont(new Font("Georgia", Font.BOLD, 14));
	   myButtonsPanel.setBorder(border2);
	   
	   myOnes = new JButton("Ones");
	   myOnes.setSize(100, 20);
	   myOnes.setIcon(new ImageIcon("Resources/checkmark.png"));
	   myOnes.setFont(new Font("Georgia", Font.BOLD, 12));
	   myOnes.setVisible(true);
	   myButtonsPanel.add(myOnes);
	   associateCategoryListener(myOnes, 0);
	   
	   myTwos = new JButton("Twos");
	   myTwos.setSize(100, 20);
	   myTwos.setIcon(new ImageIcon("Resources/checkmark.png"));
	   myTwos.setFont(new Font("Georgia", Font.BOLD, 12));
	   myTwos.setVisible(true);
	   myButtonsPanel.add(myTwos);
	   associateCategoryListener(myTwos, 1);
	   
	   myThrees = new JButton("Threes");
	   myThrees.setSize(100, 20);
	   myThrees.setIcon(new ImageIcon("Resources/checkmark.png"));
	   myThrees.setFont(new Font("Georgia", Font.BOLD, 12));
	   myThrees.setVisible(true);
	   myButtonsPanel.add(myThrees);
	   associateCategoryListener(myThrees, 2);
	   
	   myFours = new JButton("Fours");
	   myFours.setSize(100, 20);
	   myFours.setIcon(new ImageIcon("Resources/checkmark.png"));
	   myFours.setFont(new Font("Georgia", Font.BOLD, 12));
	   myFours.setVisible(true);
	   myButtonsPanel.add(myFours);
	   associateCategoryListener(myFours, 3);
	   
	   myFives = new JButton("Fives");
	   myFives.setSize(100, 20);
	   myFives.setIcon(new ImageIcon("Resources/checkmark.png"));
	   myFives.setFont(new Font("Georgia", Font.BOLD, 12));
	   myFives.setVisible(true);
	   myButtonsPanel.add(myFives);
	   associateCategoryListener(myFives, 4);
	   
	   mySixes = new JButton("Sixes");
	   mySixes.setSize(100, 20);
	   mySixes.setIcon(new ImageIcon("Resources/checkmark.png"));
	   mySixes.setFont(new Font("Georgia", Font.BOLD, 12));
	   mySixes.setVisible(true);
	   myButtonsPanel.add(mySixes);
	   associateCategoryListener(mySixes, 5);
	   
	   mySmlStrt = new JButton("Small Straight");
	   mySmlStrt.setSize(100, 20);
	   mySmlStrt.setIcon(new ImageIcon("Resources/checkmark.png"));
	   mySmlStrt.setFont(new Font("Georgia", Font.BOLD, 12));
	   mySmlStrt.setVisible(true);
	   myButtonsPanel.add(mySmlStrt);
	   associateCategoryListener(mySmlStrt, 6);
	   
	   myLrgStrt = new JButton("Large Straight");
	   myLrgStrt.setSize(100, 20);
	   myLrgStrt.setIcon(new ImageIcon("Resources/checkmark.png"));
	   myLrgStrt.setFont(new Font("Georgia", Font.BOLD, 12));
	   myLrgStrt.setVisible(true);
	   myButtonsPanel.add(myLrgStrt);
	   associateCategoryListener(myLrgStrt, 7);
	   
	   myChance = new JButton("Chance");
	   myChance.setSize(100, 20);
	   myChance.setIcon(new ImageIcon("Resources/checkmark.png"));
	   myChance.setFont(new Font("Georgia", Font.BOLD, 12));
	   myChance.setVisible(true);
	   myButtonsPanel.add(myChance);
	   associateCategoryListener(myChance, 8);
	   
	   my3ofKind = new JButton("Three of Kind");
	   my3ofKind.setSize(100, 20);
	   my3ofKind.setIcon(new ImageIcon("Resources/checkmark.png"));
	   my3ofKind.setFont(new Font("Georgia", Font.BOLD, 12));
	   my3ofKind.setVisible(true);
	   myButtonsPanel.add(my3ofKind);
	   associateCategoryListener(my3ofKind, 9);
	   
	   my4ofKind = new JButton("Four of Kind");
	   my4ofKind.setSize(100, 20);
	   my4ofKind.setIcon(new ImageIcon("Resources/checkmark.png"));
	   my4ofKind.setFont(new Font("Georgia", Font.BOLD, 12));
	   my4ofKind.setVisible(true);
	   myButtonsPanel.add(my4ofKind);
	   associateCategoryListener(my4ofKind, 10);
	   
	   myYahtzee = new JButton("Yahtzee");
	   myYahtzee.setSize(100, 20);
	   myYahtzee.setIcon(new ImageIcon("Resources/checkmark.png"));
	   myYahtzee.setFont(new Font("Georgia", Font.BOLD, 12));
	   myYahtzee.setVisible(true);
	   myButtonsPanel.add(myYahtzee);
	   associateCategoryListener(myYahtzee, 11);
	   
	   myFullHouse = new JButton("Full house");
	   myFullHouse.setSize(100, 20);
	   myFullHouse.setIcon(new ImageIcon("Resources/checkmark.png"));
	   myFullHouse.setFont(new Font("Georgia", Font.BOLD, 12));
	   myFullHouse.setVisible(true);
	   myButtonsPanel.add(myFullHouse);
	   associateCategoryListener(myFullHouse, 12);
	   
	   centerPanel.add(myButtonsPanel);
	    
	   /*
	    * The menu configuration details begin here
	    */
	   JMenuBar menuBar = new JMenuBar();
	   myMainView.setJMenuBar(menuBar);
	   menuBar.setBackground(Color.RED);
	   
	   JMenu file = new JMenu("File");
	   file.setBackground(Color.RED);
	   file.setFont(new Font("Georgia", Font.PLAIN, 12));
	   menuBar.add(file);
	   
	   JMenu help = new JMenu("Help");
	   help.setBackground(Color.RED);
	   help.setFont(new Font("Georgia", Font.PLAIN, 12));
	   menuBar.add(help);
	   
	   JMenuItem basicRulesDropDownItem = new JMenuItem("Basic Rules", new ImageIcon("Resources/icon_message_info.png"));
	   basicRulesDropDownItem.setFont(new Font("Georgia", Font.PLAIN, 13));
	   help.add(basicRulesDropDownItem);
	   associateMenuListener(basicRulesDropDownItem, 200);
	   
	   JMenuItem endGameDropDownItem = new JMenuItem("End Game", new ImageIcon("Resources/ic_action_cancel.png"));
	   endGameDropDownItem.setFont(new Font("Georgia", Font.PLAIN, 13));
	   file.add(endGameDropDownItem);
	   associateMenuListener(endGameDropDownItem, 100);
	  	   
	   this.pack();
	   myMainView.setVisible(true);
   }
   
   /**
    * <p>This method is used to update the game label with a live feed of the game play.<p>
    * @param name
    */
   public void updateGameStatusLabel(String name)
   {
	   String message =  "It is " + name.toUpperCase() + "'s turn to roll!" + " " + name.toUpperCase() + " has " +  myController.turnsLeftToRoll() + " rolls left.";
	   
	   myGameUpdateLabel.setText(message);
   }
   
   /**
    * <p>This method is called once in the constructor to instantiate the JTable's model. After being called once, it is updated
    *    by calling the updateScoreCard method.<p>
    */
   public void setScoreCardTable()
   {    
	   for(int i = 0; i < myPlayerUpperScores.length; i++)
	   {
		   myPlayerUpperScores[i] = myController.getPlayerUpperCats().get(i).getValue();
	   }
	   for(int i = 0; i < myPlayerLowerScores.length; i++)
	   {
		   myPlayerLowerScores[i] = myController.getPlayerLowerCats().get(i).getValue();
	   }
	   
	   for(int i = 0; i < myComputerPlayerUpperScores.length; i++)
	   {
		   myComputerPlayerUpperScores[i] = myController.getComputerPlayerUpperCats().get(i).getValue();
	   }
	   for(int i = 0; i < myComputerPlayerLowerScores.length; i++)
	   {
		   myComputerPlayerLowerScores[i] = myController.getComputerPlayerLowerCats().get(i).getValue();
	   }
	   
	   System.out.println(myController.getPlayerLowerCats().size());

	   String[] columnNames = {
		    "Category",
		     myController.getPlayerName(),
		    "Computer Player"
	   };
	   
	   Object[][] data = {
		   {"", "Upper Section", ""},
		   {"Ones", myPlayerUpperScores[0],  myComputerPlayerUpperScores[0]},
		   {"Twos", myPlayerUpperScores[1],  myComputerPlayerUpperScores[1]},
		   {"Threes",myPlayerUpperScores[2], myComputerPlayerUpperScores[2]},
		   {"Fours", myPlayerUpperScores[3], myComputerPlayerUpperScores[3]},
		   {"Fives", myPlayerUpperScores[4], myComputerPlayerUpperScores[4]},
		   {"Sixes", myPlayerUpperScores[5], myComputerPlayerUpperScores[5]},
		   {"", "", ""},
		   {"Upper Score:", myController.getPlayerUpperScore(1),  myController.getPlayerUpperScore(2)},
		   {"", "", ""},
		   {"", "Lower Section", ""},
		   {"3 of Kind", myPlayerLowerScores[0], myComputerPlayerLowerScores[0]},
		   {"4 of Kind", myPlayerLowerScores[1], myComputerPlayerLowerScores[1]},
		   {"Full House",myPlayerLowerScores[2], myComputerPlayerLowerScores[2]},
		   {"Small Straight", myPlayerLowerScores[3], myComputerPlayerLowerScores[3]},
		   {"Large Straight", myPlayerLowerScores[4], myComputerPlayerLowerScores[4]},
	       {"Yahtzee", myPlayerLowerScores[5], myComputerPlayerLowerScores[5]},
		   {"Chance", myPlayerLowerScores[6], myComputerPlayerLowerScores[6]},
		   {"", "", ""},
		   {"Lower Total:", myController.getPlayerLowerTotal(1), myController.getPlayerLowerTotal(2)},
		   {"Upper Total:", myController.getPlayerUpperTotal(1), myController.getPlayerUpperTotal(2)},
		   {"Grand Total:", myController.getPlayerGrandTotal(1), myController.getPlayerGrandTotal(2)},
	    };
	   
	    DefaultTableModel tableMod = new DefaultTableModel(columnNames, 0)
	    {
			private static final long serialVersionUID = 1L;

			@Override
	    	public boolean isCellEditable(int row, int col)
	    	{
	    		return false;
	    	}
	    };
	   
	    for(int i = 0; i < data.length; i++)
	    {
	    	tableMod.addRow(data[i]);
	    }
	    
	    myTable = new JTable(data, columnNames);
	    myTable.setModel(tableMod);
	    myTable.setSelectionForeground(Color.RED);
	    myTable.setRowHeight(50);
	    myTable.getTableHeader().setFont(new Font("Georgia", Font.BOLD, 12));
	    myTable.setFont(new Font("Georgia", Font.BOLD, 16));
	    myTable.setFillsViewportHeight(true);
	    myTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    myTable.setOpaque(false);
   }
   
   /**
    * <p>This method is used to update the players scores in the score card each time a category has been filled. This method 
    *    must be used in order to update the JTable's data model.<p>
    */
   public void updateScoreCard()
   {
	   for(int i = 0; i < myPlayerUpperScores.length; i++)
	   {
		   myPlayerUpperScores[i] = myController.getPlayerUpperCats().get(i).getValue();
	   }
	   for(int i = 0; i < myPlayerLowerScores.length; i++)
	   {
		   myPlayerLowerScores[i] = myController.getPlayerLowerCats().get(i).getValue();
	   }
	   
	   for(int i = 0; i < myComputerPlayerUpperScores.length; i++)
	   {
		   myComputerPlayerUpperScores[i] = myController.getComputerPlayerUpperCats().get(i).getValue();
	   }
	   for(int i = 0; i < myComputerPlayerLowerScores.length; i++)
	   {
		   myComputerPlayerLowerScores[i] = myController.getComputerPlayerLowerCats().get(i).getValue();
	   }
	   
	   System.out.println(myController.getPlayerLowerCats().size());
	
	   String[] columnNames = {
		    "Category",
		     myController.getPlayerName(),
		    "Computer Player"
	   };
	     
	   Object[][] data = {
		   {"", "Upper Section", ""},
		   {"Ones", myPlayerUpperScores[0],  myComputerPlayerUpperScores[0]},
		   {"Twos", myPlayerUpperScores[1],  myComputerPlayerUpperScores[1]},
		   {"Threes",myPlayerUpperScores[2], myComputerPlayerUpperScores[2]},
		   {"Fours", myPlayerUpperScores[3], myComputerPlayerUpperScores[3]},
		   {"Fives", myPlayerUpperScores[4], myComputerPlayerUpperScores[4]},
		   {"Sixes", myPlayerUpperScores[5], myComputerPlayerUpperScores[5]},
		   {"", "", ""},
		   {"Upper Score:", myController.getPlayerUpperScore(1),  myController.getPlayerUpperScore(2)},
		   {"", "", ""},
		   {"", "Lower Section", ""},
		   {"3 of Kind", myPlayerLowerScores[0], myComputerPlayerLowerScores[0]},
		   {"4 of Kind", myPlayerLowerScores[1], myComputerPlayerLowerScores[1]},
		   {"Small Straight",myPlayerLowerScores[2], myComputerPlayerLowerScores[2]},
		   {"Large Straight", myPlayerLowerScores[3], myComputerPlayerLowerScores[3]},
		   {"Full House", myPlayerLowerScores[4], myComputerPlayerLowerScores[4]},
	       {"Yahtzee", myPlayerLowerScores[5], myComputerPlayerLowerScores[5]},
		   {"Chance", myPlayerLowerScores[6], myComputerPlayerLowerScores[6]},
		   {"", "", ""},
		   {"Lower Total:", myController.getPlayerLowerTotal(1), myController.getPlayerLowerTotal(2)},
		   {"Upper Total:", myController.getPlayerUpperTotal(1), myController.getPlayerUpperTotal(2)},
		   {"Grand Total:", myController.getPlayerGrandTotal(1), myController.getPlayerGrandTotal(2)},
	    };
	   
	    DefaultTableModel tableMod = new DefaultTableModel(columnNames, 0)
	    {
			private static final long serialVersionUID = 1L;
	
			@Override
	    	public boolean isCellEditable(int row, int col)
	    	{
	    		return false;
	    	}
	    };
	   
	    for(int i = 0; i < data.length; i++)
	    {
	    	tableMod.addRow(data[i]);
	    }
	   myTable.setModel(tableMod);
   }
   
   
   /**
    * <p>This is my method for attaching reflection to a JMenuItem. There is a file menu item, and exit menu item, which are linked to 
    *    methods in the controller.<p>
    */
   public void associateMenuListener(JMenuItem item, int menuParameter)
   {
	   try
	   {
		  Class<?> cont = myController.getClass();
		  Class<?> parameters = Class.forName("java.lang.Integer");
		  
		  Method endGameAction = cont.getMethod("endGame", parameters);
		  Method openGameRulesAction = cont.getMethod("openGameRules", parameters);
		  
		  Object param = new Object();
		  param = menuParameter;
		  
		  if(menuParameter == 100)
		  {
			  myButtonListener = new ButtonListener(myController, endGameAction, param);
			  item.addMouseListener(myButtonListener);
		  }
		  else if(menuParameter == 200)
		  {
			  myButtonListener = new ButtonListener(myController, openGameRulesAction, param);
			  item.addMouseListener(myButtonListener);
		  }
	   }
	   catch(ClassNotFoundException e)
	   {
		   e.printStackTrace();
	   }
	   catch(SecurityException e)
	   {
		   e.printStackTrace();
	   }
	   catch(NoSuchMethodException e)
	   {
		   e.printStackTrace();
	   }
   }
   
   /**
    * <p>This method uses reflection to select which dice to keep. It also uses reflection to perform the roll dice function.<p>
    * @param button
    * @param btnParameter
    */
   public void associateListener(JButton button, int btnParameter)
   {
	   try
	   {
		  Class<?> cont = myController.getClass();
		  Class<?> parameters = Class.forName("java.lang.Integer");
		  
		  Method rollDiceAction = cont.getMethod("rollDice", parameters);
		  Method selectDiceToKeep = cont.getMethod("selectDiceToKeep", parameters);
		  
		  Object param = new Object();
		  param = btnParameter;
		  
		  if(btnParameter == 300)
		  {
			  myButtonListener = new ButtonListener(myController, rollDiceAction, param);
			  button.addMouseListener(myButtonListener);
		  }
		  else
		  {
			  myButtonListener = new ButtonListener(myController, selectDiceToKeep, param);
			  button.addMouseListener(myButtonListener);
		  }
	   }
	   catch(Exception e)
	   {
		   System.out.println("error in associate listener");
		   e.printStackTrace();
	   }
   }
   
   /**
    * <p>This method attaches reflection to the appropriate Category buttons. A method in the controller is called to fire the actions
    *    when pressed.<p>
    * @param Category button
    * @param btnParameter
    */
   public void associateCategoryListener(JButton button, int btnParameter)
   {
	   try
	   {
		  Class<?> cont = myController.getClass();
		  Class<?> parameters = Class.forName("java.lang.Integer");
		  
		  Method fillCategories = cont.getMethod("selectCategoriesToFill", parameters);
		  
		  Object param = new Object();
		  param = btnParameter;
		  
		  
		  myButtonListener = new ButtonListener(myController, fillCategories, param);
		  button.addMouseListener(myButtonListener);
		  
	   }
	   catch(Exception e)
	   {
		   System.out.println("error in associate listener");
		   e.printStackTrace();
	   }
   }
   
   /**
    * <p>This method is used to show the new dice face values by setting the ImageIcons each roll.<p>
    * @param diceValues
    */
   public void showDieFaces(int[] diceValues)
   {
	  for(int t = 0; t < diceValues.length; t++)
	  {
		 myDieBtns[t].setIcon(returnDieImage(diceValues[t]));
	  }
    }
   
   
   /**
    * <p>This method is called upon in order to mark the category buttons that correspond to the current dice face values. When found in the vector,
    *    the buttons image icon is set to a red plus button.<p>
    *    @param Vector of potential categories to fill based on current dice face values
    */
   public void categoriesToHighLight(Vector<Category> vect)
   {
	   for(int i = 0 ; i < vect.size(); i++)
	   {
		   if(vect.get(i).getName().equals("Ones"))
		   {
			   myOnes.setIcon(new ImageIcon("Resources/button_red.png"));
		   }
		   else if(vect.get(i).getName().equals("Twos"))
		   {
			   myTwos.setIcon(new ImageIcon("Resources/button_red.png"));
		   }
		   else if(vect.get(i).getName().equals("Threes"))
		   {
			   myThrees.setIcon(new ImageIcon("Resources/button_red.png"));
		   }
		   else if(vect.get(i).getName().equals("Fours"))
		   {
			   myFours.setIcon(new ImageIcon("Resources/button_red.png"));
		   }
		   else if(vect.get(i).getName().equals("Fives"))
		   {
			   myFives.setIcon(new ImageIcon("Resources/button_red.png"));
		   }
		   else if(vect.get(i).getName().equals("Sixes"))
		   {
			   mySixes.setIcon(new ImageIcon("Resources/button_red.png"));
		   }
		   else if(vect.get(i).getName().equals("Small Straight"))
		   {
			   mySmlStrt.setIcon(new ImageIcon("Resources/button_red.png"));
		   }
		   else if(vect.get(i).getName().equals("Large Straight"))
		   {
			   myLrgStrt.setIcon(new ImageIcon("Resources/button_red.png"));
		   }
		   else if(vect.get(i).getName().equals("Chance"))
		   {
			   myChance.setIcon(new ImageIcon("Resources/button_red.png"));
		   }
		   else if(vect.get(i).getName().equals("Full House"))
		   {
			   myFullHouse.setIcon(new ImageIcon("Resources/button_red.png"));
		   }
		   else if(vect.get(i).getName().equals("Yahtzee"))
		   {
			   myYahtzee.setIcon(new ImageIcon("Resources/button_red.png"));
		   } 
		   else if(vect.get(i).getName().equals("Three of a kind"))
		   {
			   my3ofKind.setIcon(new ImageIcon("Resources/button_red.png"));
		   }
		   else if(vect.get(i).getName().equals("Four of a kind"))
		   {
			   my4ofKind.setIcon(new ImageIcon("Resources/button_red.png"));
		   }
	   }
   }
   
   /**
    * <p>This method is used to show the buttons of the available categories ONLY. It does so by leveraging a vector of possible categories to fill. 
    *    This allows buttons to be displayed dynamically based on the face values of the dice that were rolled.<p>
    *    
    * @param vector of categories that can be filled
    */
   public void categoriesToFill(Vector<Category> vect)
   {
	   for(int i = 0 ; i < vect.size(); i++)
	   {
		   if(vect.get(i).getName().equals("Ones"))
		   {
			   myOnes.setVisible(true);
		   }
		   else if(vect.get(i).getName().equals("Twos"))
		   {
			   myTwos.setVisible(true);
		   }
		   else if(vect.get(i).getName().equals("Threes"))
		   {
			   myThrees.setVisible(true);
		   }
		   else if(vect.get(i).getName().equals("Fours"))
		   {
			   myFours.setVisible(true);
		   }
		   else if(vect.get(i).getName().equals("Fives"))
		   {
			   myFives.setVisible(true);
		   }
		   else if(vect.get(i).getName().equals("Sixes"))
		   {
			   mySixes.setVisible(true);
		   }
		   else if(vect.get(i).getName().equals("Small Straight"))
		   {
			   mySmlStrt.setVisible(true);
		   }
		   else if(vect.get(i).getName().equals("Large Straight"))
		   {
			   myLrgStrt.setVisible(true);
		   }
		   else if(vect.get(i).getName().equals("Chance"))
		   {
			   myChance.setVisible(true);
		   }
		   else if(vect.get(i).getName().equals("Full House"))
		   {
			   myFullHouse.setVisible(true);
		   }
		   else if(vect.get(i).getName().equals("Yahtzee"))
		   {
			   myYahtzee.setVisible(true);
		   } 
		   else if(vect.get(i).getName().equals("Three of a kind"))
		   {
			   my3ofKind.setVisible(true);
		   }
		   else if(vect.get(i).getName().equals("Four of a kind"))
		   {
			   my4ofKind.setVisible(true);
		   }
	   }
   }
   
   /**
    * <p>Utility method for resetting category icons on each roll of the dice. This must be done or the image icon will never
    *    be removed from the view.<p>
    */
   public void refreshButtonsForRoll()
   {
	   myOnes.setIcon(new ImageIcon("Resources/checkmark.png"));
	   myTwos.setIcon(new ImageIcon("Resources/checkmark.png"));
	   myThrees.setIcon(new ImageIcon("Resources/checkmark.png"));
	   myFours.setIcon(new ImageIcon("Resources/checkmark.png"));
	   myFives.setIcon(new ImageIcon("Resources/checkmark.png"));
	   mySixes.setIcon(new ImageIcon("Resources/checkmark.png"));
	   mySmlStrt.setIcon(new ImageIcon("Resources/checkmark.png"));
	   myLrgStrt.setIcon(new ImageIcon("Resources/checkmark.png"));
	   myChance.setIcon(new ImageIcon("Resources/checkmark.png"));
	   my3ofKind.setIcon(new ImageIcon("Resources/checkmark.png"));
	   my4ofKind.setIcon(new ImageIcon("Resources/checkmark.png"));
	   myYahtzee.setIcon(new ImageIcon("Resources/checkmark.png"));
	   myFullHouse.setIcon(new ImageIcon("Resources/checkmark.png"));
    }
   
   /**
    * <p>This method is called on to set the dice image icons based on the current dice face values.<p>
    * 
    * @param dice face value (Integer)
    */
   public ImageIcon returnDieImage(int faceValue) 
   {
	   ImageIcon dieIcon = null;
	   ImageIcon dieONE, dieTWO, dieTRHEE, dieFOUR, dieFIVE, dieSIX;
	   
	   dieONE = new ImageIcon("Resources/dice-1.png");
	   dieTWO = new ImageIcon("Resources/dice-2.png");
	   dieTRHEE = new ImageIcon("Resources/dice-3.png");
	   dieFOUR = new ImageIcon("Resources/dice-4.png");
	   dieFIVE = new ImageIcon("Resources/dice-5.png");
	   dieSIX = new ImageIcon("Resources/dice-6.png");
	   
	   switch(faceValue)
	   {
	      case 1: dieIcon = dieONE; break;
	      case 2: dieIcon = dieTWO; break;
	      case 3: dieIcon = dieTRHEE; break;
	      case 4: dieIcon = dieFOUR; break;
	      case 5: dieIcon = dieFIVE; break;
	      case 6: dieIcon = dieSIX; break;
	      default: break;
	   }
	   return dieIcon;
   }
   
   /**
    * <p>Simple utility method for hiding all the category buttons from the view.<p>
    */
   public void hideButtonsFromView()
   {
	   myOnes.setVisible(false);
	   myTwos.setVisible(false);
	   myThrees.setVisible(false);
	   myFours.setVisible(false);
	   myFives.setVisible(false);
	   mySixes.setVisible(false);
	   my3ofKind.setVisible(false);
	   my4ofKind.setVisible(false);
	   myChance.setVisible(false);
	   myFullHouse.setVisible(false);
	   myYahtzee.setVisible(false);
	   myLrgStrt.setVisible(false);
	   mySmlStrt.setVisible(false);
   }
   
   /**
    * <p>Simple utility method for showing all the category buttons in the view.<p>
    */
   public void showButtonsFromView()
   {
	   myOnes.setVisible(true);
	   myTwos.setVisible(true);
	   myThrees.setVisible(true);
	   myFours.setVisible(true);
	   myFives.setVisible(true);
	   mySixes.setVisible(true);
	   my3ofKind.setVisible(true);
	   my4ofKind.setVisible(true);
	   myChance.setVisible(true);
	   myFullHouse.setVisible(true);
	   myYahtzee.setVisible(true);
	   myLrgStrt.setVisible(true);
	   mySmlStrt.setVisible(true);
   }

   /**
    * <p>Use this method to set the roll buttons text after the game has started.<p>
    * @param button text string
    */
   public void setBtnText(String btnText) 
   {
	  myRollbtn.setText(btnText);
   }
   
   /**
    * <p>Use this method to keep dice false until game has started.<p>
    * @param boolean for toggling
    */
   public void setDiceEnabled(boolean b) 
   {
	  if(b)
	  {
		  for(int i = 0; i < myDieBtns.length; i++)
		   {
			  myDieBtns[i].setEnabled(true);
		   }
	  }
   }
}