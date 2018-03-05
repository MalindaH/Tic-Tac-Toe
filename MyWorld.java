import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 * Name: Malinda (Linhui Huang)
 * Course: CS30S
 * Teacher: Mr. Hardman
 * Lab #1, Program #1
 * Date Last Modified: March 5th, 2018
 */
public class MyWorld extends World
{
    private boolean playerOneTurn = true;
    private boolean messageShown = false;
    
    private String playerOneName;
    private String playerTwoName;
    
    private String[][] board = new String[3][3];
    
    private GreenfootImage background = new GreenfootImage( "background.jpg" );
    
    /**
     * MyWorld is the constructor for objects of class MyWorld
     * 
     * @param There are no parameters
     * @return an object of type MyWorld
     */
    public MyWorld()
    {    
        // Create a new world with 3x3 cells with a cell size of 600x600 pixels.
        super(600, 600, 1); 
        
        background.scale( getWidth(), getHeight() );
        
        setBackground( background );
        
        drawLines();
        for( int r = 0; r < board.length; r++ )
        {
            for( int c = 0; c < board[r].length; c++ )
            {
                board[r][c] = "";
            }
        }
    }
    
    /**
     * drawLines draws the 3x3 grid that the players can place their X's and O's in
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void drawLines()
    {
        getBackground().setColor( Color.WHITE );
        
        for( int i = 200; i < getWidth(); i += 200 )
        {
            getBackground().drawLine( i, 0, i, getHeight() );
            getBackground().drawLine( 0, i, getWidth(), i );
        }
        
        Greenfoot.start();
    }
    
    /**
     * started asks and stores Player One's and Player Two's names
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public void started()
    {
        playerOneName = JOptionPane.showInputDialog( null, "Please enter Player One's name:", "Player One Name", JOptionPane.QUESTION_MESSAGE );
        playerTwoName = JOptionPane.showInputDialog( null, "Please enter Player Two's name:", "Player Two Name", JOptionPane.QUESTION_MESSAGE );
    }
    
    /**
     * act is the code that is run on every iteration of the act cycle
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public void act()
    {
        displayBoard();
        Greenfoot.delay(10);

        if( checkPlayerOneWin() == true )
        {
            JOptionPane.showMessageDialog( null, "Congratulations Player One!", "Player One Wins", JOptionPane.PLAIN_MESSAGE );
            Greenfoot.stop();
        }
        else if( checkPlayerTwoWin() == true )
        {
            JOptionPane.showMessageDialog( null, "Congratulations Player Two!", "Player Two Wins", JOptionPane.PLAIN_MESSAGE );
            Greenfoot.stop();
        }
        else if( checkBoardFilled() == true )
        {
            JOptionPane.showMessageDialog( null, "It is a draw!", "Draw", JOptionPane.PLAIN_MESSAGE );
            Greenfoot.stop();
        }
        else if( messageShown == false )
        {
            showTurn();
            messageShown = true;
        }
        checkMouseClick();
    }
    
    /**
     * showTurn shows messages for Player One's and Player Two's turns
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void showTurn()
    {
        if( playerOneTurn == true )
        {
            if( messageShown == false )
            {
                JOptionPane.showMessageDialog( null, playerOneName + ", please select where you want to place your X", "Player One's Turn", JOptionPane.PLAIN_MESSAGE );
            }
        }
        else
        {
            if( messageShown == false )
            {
                JOptionPane.showMessageDialog( null, playerTwoName + ", please select where you want to place your O", "Player Two's Turn", JOptionPane.PLAIN_MESSAGE );
            }
        }
    }
    
    /**
     * checkmouseClick checks where Player One and Two want to place their X's and O's
     * and tells them to select a different spot if the spot is already filled
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void checkMouseClick()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        
        int rowNumber;
        int columnNumber;
        
        if( Greenfoot.mouseClicked(this) )
        {
            columnNumber = mouse.getX() / ( getHeight() / 3 );
            rowNumber = mouse.getY() / ( getHeight() / 3 );
            
            if( board[rowNumber][columnNumber] == "" )
            {
                if( playerOneTurn == true )
                {
                    board[rowNumber][columnNumber] = "X";
                    
                    playerOneTurn = false;
                    messageShown = false;
                }
                else
                {
                    board[rowNumber][columnNumber] = "O";
                    
                    playerOneTurn = true;
                    messageShown = false;
                }
            }
            else
            {
                JOptionPane.showMessageDialog( null, "This spot is not empty! Select a different spot!", "Spot not available", JOptionPane.PLAIN_MESSAGE );
            }
        }
        
    }
    
    /**
     * displayBoard displays the X's and O's that Player One and Two have placed
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void displayBoard()
    {
        GreenfootImage toDisplay;
        
        for( int r = 0; r < board.length; r ++ )
        {
            for( int c = 0; c < board[r].length; c ++ )
            {
                toDisplay = new GreenfootImage( board[r][c], 100, Color.WHITE, new Color(0,0,0,0) );  
                getBackground().drawImage( toDisplay, c * getWidth()/3 + (getWidth()/3 - toDisplay.getWidth() )/2 , r * getHeight()/3 + (getHeight()/3 - toDisplay.getHeight() )/2 );
            }
        }
    }
    
    /**
     * checkPlayerOneWin checks whether Player One wins with three X’s in a row, column, or diagonal
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
     private boolean checkPlayerOneWin()
    {
        boolean checkPlayerOneWin = false;
        
        if( board[0][0] == "X" && board[0][1] == "X" && board[0][2] == "X" )
        {
            checkPlayerOneWin = true;
        }
        else if( board[1][0] == "X" && board[1][1] == "X" && board[1][2] == "X" )
        {
            checkPlayerOneWin = true;
        }
        else if( board[2][0] == "X" && board[2][1] == "X" && board[2][2] == "X" )
        {
            checkPlayerOneWin = true;
        }
        else if( board[0][0] == "X" && board[1][0] == "X" && board[2][0] == "X" )
        {
            checkPlayerOneWin = true;
        }
        else if( board[0][1] == "X" && board[1][1] == "X" && board[2][1] == "X" )
        {
            checkPlayerOneWin = true;
        }
        else if( board[0][2] == "X" && board[1][2] == "X" && board[2][2] == "X" )
        {
            checkPlayerOneWin = true;
        }
        else if( board[0][0] == "X" && board[1][1] == "X" && board[2][2] == "X" )
        {
            checkPlayerOneWin = true;
        }
        else if( board[0][2] == "X" && board[1][1] == "X" && board[2][0] == "X" )
        {
            checkPlayerOneWin = true;
        }
        return checkPlayerOneWin;
    }
    
    /**
     * checkPlayerTwoWin checks whether Player One wins with three O’s in a row, column, or diagonal
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private boolean checkPlayerTwoWin()
    {
        boolean checkPlayerTwoWin = false;
        
        if( board[0][0] == "O" && board[0][1] == "O" && board[0][2] == "O" )
        {
            checkPlayerTwoWin = true;
        }
        else if( board[1][0] == "O" && board[1][1] == "O" && board[1][2] == "O" )
        {
            checkPlayerTwoWin = true;
        }
        else if( board[2][0] == "O" && board[2][1] == "O" && board[2][2] == "O" )
        {
            checkPlayerTwoWin = true;
        }
        else if( board[0][0] == "O" && board[1][0] == "O" && board[2][0] == "O" )
        {
            checkPlayerTwoWin = true;
        }
        else if( board[0][1] == "O" && board[1][1] == "O" && board[2][1] == "O" )
        {
            checkPlayerTwoWin = true;
        }
        else if( board[0][2] == "O" && board[1][2] == "O" && board[2][2] == "O" )
        {
            checkPlayerTwoWin = true;
        }
        else if( board[0][0] == "O" && board[1][1] == "O" && board[2][2] == "O" )
        {
            checkPlayerTwoWin = true;
        }
        else if( board[0][2] == "O" && board[1][1] == "O" && board[2][0] == "O" )
        {
            checkPlayerTwoWin = true;
        }
        return checkPlayerTwoWin;
    }
    
    /**
     * checkBoardFilled checks whether each part of the board is filled
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private boolean checkBoardFilled()
    {
        boolean boardFilled = true;
        for( int r = 0; boardFilled == true && r < board.length; r ++ )
        {
            for( int c = 0; boardFilled == true && c < board[r].length; c ++ )
            {
                if( board[r][c] == "" )
                {
                    boardFilled = false;
                }
            }
        }
        return boardFilled;
    }
}





















