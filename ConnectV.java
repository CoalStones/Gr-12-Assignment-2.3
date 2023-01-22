package assignment23;
import java.util.Scanner;

public class ConnectV {
    Scanner input=new Scanner(System.in);
    private int[][] gameBoard=new int[8][8];
    private int column;
    private boolean gameOver,turn;
    private int turns=0;
    
    private void setup(){
        for(int i=0; i<8; i++){
            for(int count=0; count<8; count++){
                gameBoard[i][count]=0;
            }
        }
    }
    
    public void play(){
        setup();
        while(!(gameOver==true)){
            board();
            playerInput();
            if(turns==64)break;
        }
        board();
        if(turns==64)System.out.println("Tie game");
        else if(turn)System.out.println("Player 1 wins");
        else System.out.println("Player 2 wins");
        System.out.println("The game took "+turns+" turns to complete");
    }
    
    private void board(){
        for(int i=7; i>=0; i--){
            for(int count=0; count<8; count++){
                System.out.print(gameBoard[i][count]+" ");
            }
            System.out.println();
        }
        System.out.println("A B C D E F G H");
    }
    
    private void playerInput(){
        String uin;
        if(!turn)System.out.println("Player 1 it is your turn");
        else System.out.println("Player 2 it is your turn");
        
        System.out.println("What column would you like to play in?");
            uin=input.next();
            column=uin.toUpperCase().charAt(0)-65;
            while(column>7){
                System.out.println("That is not a valid play, try again");
                uin=input.next();
                column=uin.toUpperCase().charAt(0)-65;
            }
            boolean full=taken();
            while(full){
                System.out.println("That row is full");
                System.out.println("What column would you like to play in?");
                uin=input.next();
                column=uin.toUpperCase().charAt(0)-65;
                while(column>7){
                System.out.println("That is not a valid play, try again");
                uin=input.next();
                column=uin.toUpperCase().charAt(0)-65;
                }
                full=taken();
            }
            
        turn=boardChange(turn);
        turns++;
        win();
    }
    
    private boolean boardChange(boolean turn){
        if(!turn){
            for(int count=0; count<8; count++){
                boolean taken=toTheGround(count);
                if(!taken){
                    gameBoard[count][column]=1;
                    break;
                }
            }
            turn=true;
        }
        else{
            for(int count=0; count<8; count++){
                boolean taken=toTheGround(count);
                if(!taken){
                    gameBoard[count][column]=2;
                    break;
                }
            }
            turn=false;
        }
        return(turn);
    }
    
    private boolean toTheGround(int count){
        boolean taken=false;
        if(gameBoard[count][column]!=0)taken=true;
        return(taken);
    }
    
    private boolean taken(){
        boolean full=false;
        if(gameBoard[7][column]!=0)full=true;
        return(full);
    }
    
    private void win(){
        for(int count=0; count<=3; count++){
            for(int i=0; i<=5; i++){
             if(gameBoard[count][i]!=0&&gameBoard[count][i]==gameBoard[count+1][i+1]&&gameBoard[count+1][i+1]==gameBoard[count+2][i+2]&&gameBoard[count+2][i+2]==gameBoard[count+1][i+3]&&gameBoard[count+1][i+3]==gameBoard[count][i+4]){
                 gameOver=true;
                 System.out.println(gameOver);
             }
            }
        }
        
        for(int count=7; count>=2; count--){
            for(int i=7; i>=4; i--){
            if(gameBoard[count][i]!=0&&gameBoard[count][i]==gameBoard[count-1][i-1]&&gameBoard[count-1][i-1]==gameBoard[count-2][i-2]&&gameBoard[count-2][i-2]==gameBoard[count-1][i-3]&&gameBoard[count-1][i-3]==gameBoard[count][i-4])gameOver=true;
            }
        }   
    }   
}