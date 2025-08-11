import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class TicTacToe
{
    int boardWidht = 600;
    int boardHeight = 650; //50px for the text panel on top

    JFrame frame = new JFrame("Tic-Tac-Toe"); //title of the window
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JButton [][] board = new JButton[3][3]; //creating button array 
    String playerX = "X";
    String playerO = "O";
    String currentplayer = playerX; //first turn of playerX

    boolean gameOver = false;
    int turns = 0;

    TicTacToe()
    {
        frame.setVisible(true);
        frame.setSize(boardWidht, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());  //border layout for buttons

        textLabel.setBackground(Color.gray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER); //putting our text in the center 
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true); //atomicreference array method is used to set value of elements at index to new value.


        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH); //putting text on the north of the window means (top)


        boardPanel.setLayout(new GridLayout(3,3)); //creating grid layout for 3 columns and 3 rows
        boardPanel.setBackground(Color.black);
        frame.add(boardPanel);


        // creating nested for loop 

        for(int r = 0; r < 3; r++) //for loop for rows
        {
            for(int c = 0; c < 3; c++) //for loop for columns
            {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile); //creating button in the panel

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("arial",Font.BOLD,120));
                tile.setFocusable(false);
               // tile.setText(currentplayer);


                tile.addActionListener(new ActionListener()  //using actionlistner for handle event like buttons etc.
                {
                   public void actionPerformed(ActionEvent e) //creating object of action listner
                    {

                        if (gameOver) return;

                        JButton tile = (JButton) e.getSource();
                        if(tile.getText() == "")
                        {
                            tile.setText(currentplayer);
                            turns++;
                            checkWinner();
                            if(!gameOver){
                                currentplayer = currentplayer == playerX ? playerO : playerX;
                                textLabel.setText(currentplayer + "'s turn."); 
                            }
                              
                        }   
                    }    
                });


            }
        }

       

    }

    void checkWinner()
    {
        // horizontal
        for(int r=0; r<3; r++)
        {
            if(board[r][0].getText() == "") continue;

            if(board[r][0].getText() == board[r][1].getText() &&
               board[r][1].getText() == board[r][2].getText())
               {
                for(int i = 0; i <3; i++)
                {
                    setWinner(board[r][i]);
                }
                gameOver = true;
                return;
            }
        }
        // vertical
        for(int c=0; c<3; c++)
        {
            if(board[0][c].getText() == "") continue;

            if(board[0][c].getText() == board[1][c].getText() &&
               board[1][c].getText() == board[2][c].getText())
               {
                for(int i=0; i<3; i++)
                {
                    setWinner(board[i][c]);
                }
                gameOver = true;
                return;
            }
        }
        //diagonally
        if(board[0][0].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][2].getText() &&
            board[0][0].getText() != ""){
        
            for( int i=0; i<3; i++)
            {
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }
        //anti-diagnally
        if(board[0][2].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][0].getText() &&
            board[0][2].getText() != ""){
                setWinner(board[0][2]);
                setWinner(board[1][1]);
                setWinner(board[2][0]);
                gameOver = true;
                return;
        }
        // if match tie
        if(turns == 9)
        {
            for(int r=0; r<3; r++)
            {
                for(int c=0; c<3; c++)
                {
                    setTie(board[r][c]);

                }
            }
            gameOver = true;
        }
    }
    void setWinner(JButton tile)
    {
        tile.setForeground(Color.black);
        tile.setBackground(Color.green);
        textLabel.setText(currentplayer + " is the winner!");

    }

    void setTie(JButton tile)
    {
        tile.setForeground(Color.black);
        tile.setBackground(Color.red);
        textLabel.setText("tie!");
    }
}
