import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Objects;

public class Square extends JButton{
    Integer y;
    Integer x;
    String alge_notation;
    String activating_piece;
    Color original_color;
    Square activating_Square_;
    String piece_color = piece_color_func(this.getText());

    static String piece_color_func(String val) {
        if (Objects.equals(val, "\u2659 ") || Objects.equals(val, "\u2654 ") || Objects.equals(val, "\u2655 ")
                || Objects.equals(val, "\u2656 ") || Objects.equals(val, "\u2657 ") || Objects.equals(val, "\u2658 ")) {
            return "White";
        }
        else if(Objects.equals(val, "\u265F ") || Objects.equals(val, "\u265A ") || Objects.equals(val, "\u265B ")
                || Objects.equals(val, "\u265C ") || Objects.equals(val, "\u265D ") || Objects.equals(val, "\u265E ")) {
            return "Black";
        }
        else {return null;}
    }

    public void enh_white_check(Square[][] myarray) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                white_check(myarray,myarray[i][j]);

            }}
    }

    public void pink_square(Square[][] myarray, Square piece){

        piece.setText(piece.activating_piece);
        piece.piece_color=piece_color_func(piece.getText());
        piece.setBackground(piece.original_color);
        piece.activating_Square_.setText(null);
        piece.activating_piece=null;
        update_board(myarray);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                white_check(myarray,myarray[i][j]);
            }}
    }

    public void update_board(Square[][] myarray) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                myarray[i][j].setBackground(myarray[i][j].original_color);
                myarray[i][j].piece_color=piece_color_func(myarray[i][j].getText());
            }}

    }

    public void blank(Square[][] myarray) {
        if (this.getBackground()!=Color.pink) {
            update_board(myarray);
        }
        else { pink_square(myarray, this); }
    }

    public void activate_square_pink(Square[][] myarray, Integer i, Integer j, Integer orig_y, Integer orig_x, String piece) {
        myarray[i][j].setBackground(Color.pink);
        myarray[i][j].activating_piece = piece;
        myarray[i][j].activating_Square_ = myarray[orig_y][orig_x];
        myarray[orig_y][orig_x].piece_color=piece_color_func(myarray[orig_y][orig_x].getText());
    }

    public void white_pawn(Square[][] myarray) {
        if (this.getBackground()!=Color.pink) {
            update_board(myarray);
            Integer orig_x = this.x;
            Integer orig_y = this.y;
            for (int i = this.y + 1; i <= this.y + 1; i = i + 1) {
                if (i==8) {break;}
                if (this.x - 1 >= 0 && (myarray[i][this.x - 1].piece_color == "Black")) {
                    activate_square_pink(myarray, i,this.x-1,orig_y,orig_x,"\u2659 ");
                }
            }
            for (int i = this.y + 1; i <= this.y + 1; i = i + 1) {
                if (i==8) {break;}
                if (this.x + 1 < 8 && (myarray[i][this.x + 1].piece_color == "Black")) {
                    activate_square_pink(myarray, i,this.x+1,orig_y,orig_x,"\u2659 ");
                }
            }
            if (this.alge_notation.contains("2")) {
                for (int i = this.y + 1; i <= this.y + 2; i = i + 1) {
                    if (myarray[i][this.x].piece_color != "Black") {
                        activate_square_pink(myarray, i,this.x,orig_y,orig_x,"\u2659 ");
                    }
                }
            } else {
                for (int i = this.y + 1; i <= this.y + 1; i = i + 1) {
                    if (i==8) {break;}

                    if (myarray[i][this.x].piece_color != "Black") {
                        activate_square_pink(myarray, i,this.x,orig_y,orig_x,"\u2659 ");
                    }
                }

            }}
        else{ pink_square(myarray, this);}
        }

    public void bishop(Square[][] myarray, String colour, String opp_colour) {
        if (this.getBackground()!=Color.pink){
            update_board(myarray);


        Integer orig_x = this.x;
        Integer orig_y = this.y;

        for (int i = 1; i <= 8; i = i + 1) {
            Integer less_than_8y=orig_y+i;
            Integer less_than_8x=orig_x+i;
            if (less_than_8y < 8 && less_than_8x <8){
                if (myarray[orig_y+i][orig_x+i].piece_color!=null) {
                    if (myarray[orig_y+i][orig_x+i].piece_color==opp_colour) {
                        activate_square_pink(myarray, orig_y+i,orig_x+i,orig_y,orig_x,colour);
                        break;
                    }
                    break;}
                activate_square_pink(myarray, orig_y+i,orig_x+i,orig_y,orig_x,colour);
                myarray[i][this.x].activating_Square_=myarray[orig_y][orig_x];

            }

        }
        for (int i = 1; i <= 8; i = i + 1) {
            Integer less_than_8x=orig_x+i;

            Integer more_than_0y = orig_y-i;

            if (less_than_8x < 8 && more_than_0y >=0){
                if (myarray[orig_y-i][orig_x+i].piece_color!=null) {
                    if (myarray[orig_y-i][orig_x+i].piece_color==opp_colour) {
                        activate_square_pink(myarray, orig_y-i,orig_x+i,orig_y,orig_x,colour);

                        break;
                    }
                    break;}
                activate_square_pink(myarray, orig_y-i,orig_x+i,orig_y,orig_x,colour);

            }

        }
        for (int i = 1; i <= 8; i = i + 1) {

            Integer more_than_0x = orig_x-i;
            Integer more_than_0y = orig_y-i;

            if (more_than_0x >= 0 && more_than_0y >=0){

                if (myarray[orig_y-i][orig_x-i].piece_color!=null) {

                    if (myarray[orig_y-i][orig_x-i].piece_color==opp_colour) {
                        activate_square_pink(myarray, orig_y-i,orig_x-i,orig_y,orig_x,colour);
                    }
                    break;}
                activate_square_pink(myarray, orig_y-i,orig_x-i,orig_y,orig_x,colour);
            }
        }
        for (int i = 1; i <= 8; i = i + 1) {
            Integer less_than_8y=orig_y+i;
            Integer more_than_0x = orig_x-i;
            if (less_than_8y < 8 && more_than_0x >=0){
                if (myarray[orig_y+i][orig_x-i].piece_color!=null) {
                    if (myarray[orig_y+i][orig_x-i].piece_color==opp_colour) {
                        activate_square_pink(myarray, orig_y+i,orig_x-i,orig_y,orig_x,colour);
                    }
                    break;
                }
                activate_square_pink(myarray, orig_y+i,orig_x-i,orig_y,orig_x,colour);
            }

        }}
        else{ pink_square(myarray, this);}

    }

    public void king(Square[][] myarray, String Colour, String opp_colour) {
        update_board(myarray);
        Integer orig_x = this.x;
        Integer orig_y = this.y;
        Integer less_than_8y=orig_y+1;
        Integer less_than_8x=orig_x+1;
        Integer more_than_0x = orig_x-1;
        Integer more_than_0y = orig_y-1;
            if (less_than_8y < 8 && less_than_8x <8){
                if (myarray[orig_y+1][orig_x+1].piece_color!=opp_colour) {
                    activate_square_pink(myarray, orig_y+1,orig_x+1,orig_y,orig_x,Colour);
                }}


            if (less_than_8x < 8 && more_than_0y >=0){
                if (myarray[orig_y-1][orig_x+1].piece_color!=opp_colour) {
                    activate_square_pink(myarray, orig_y-1,orig_x+1,orig_y,orig_x,Colour);
                    }
        }

            if (more_than_0x >= 0 && more_than_0y >=0){

                if (myarray[orig_y-1][orig_x-1].piece_color!=opp_colour) {
                    activate_square_pink(myarray, orig_y-1,orig_x-1,orig_y,orig_x,Colour);
                    }

        }

            if (less_than_8y < 8 && more_than_0x >=0){
                if (myarray[orig_y+1][orig_x-1].piece_color!=opp_colour) {
                    activate_square_pink(myarray, orig_y+1,orig_x-1,orig_y,orig_x,Colour);
                }}

            if (less_than_8y < 8){
                if (myarray[orig_y+1][orig_x].piece_color!=opp_colour) {
                    activate_square_pink(myarray, orig_y+1,orig_x,orig_y,orig_x,Colour);
                }

        }

            if (less_than_8x < 8){
                if (myarray[orig_y][orig_x+1].piece_color!=opp_colour) {
                    activate_square_pink(myarray, orig_y,orig_x+1,orig_y,orig_x,Colour);
                }

        }
            if (more_than_0x >= 0){
                if (myarray[orig_y][orig_x-1].piece_color!=opp_colour) {
                    activate_square_pink(myarray, orig_y,orig_x-1,orig_y,orig_x,Colour);
                }
                    }

            if (more_than_0y >= 0){
                if (myarray[orig_y-1][orig_x].piece_color!=opp_colour) {
                    activate_square_pink(myarray, orig_y-1,orig_x,orig_y,orig_x,Colour);
                    }

                }

            }

    public void black_pawn(Square[][] myarray) {
        if (this.getBackground()!=Color.pink) {
            update_board(myarray);
            Integer orig_x = this.x;
            Integer orig_y = this.y;
            for (int i = this.y-1; i >= this.y-1; i = i - 1) {
                if (i+1==0) {break;}


                if (this.x -1>=0 && (Objects.equals(myarray[i][this.x - 1].piece_color, "White"))) {
                    activate_square_pink(myarray, i,this.x-1,orig_y,orig_x,"\u265F ");
                }

                if (this.x +1<8 && (myarray[i][this.x+1].piece_color=="White")) {
                    activate_square_pink(myarray, i,this.x+1,orig_y,orig_x,"\u265F ");
                }
            }
            if (this.alge_notation.contains("7")) {
                for (int i = this.y-1; i >= this.y-2; i = i - 1) {
                    if (myarray[i][this.x].piece_color!="White") {
                        activate_square_pink(myarray, i,this.x,orig_y,orig_x,"\u265F ");
                        }
                }
            }
            else {
                for (int i = this.y-1; i >= this.y-1; i = i - 1) {
                    if (i+1==0) {break;}
                    if (myarray[i][this.x].piece_color==null) {
                        activate_square_pink(myarray, i,this.x,orig_y,orig_x,"\u265F ");
                        }
                }

            }

        }

        else{ pink_square(myarray, this);}


    }

    public void rook(Square[][] myarray, String Colour, String opp_colour) {
        if (this.getBackground()!=Color.pink) {
            update_board(myarray);
            Integer orig_x = this.x;
            Integer orig_y = this.y;
            for (int i = 1; i <= 8; i = i + 1) {
                Integer less_than_8y = orig_y + i;
                if (less_than_8y < 8) {
                    if (myarray[orig_y + i][orig_x].piece_color != null) {
                        if (myarray[orig_y + i][orig_x].piece_color == opp_colour) {
                            activate_square_pink(myarray, orig_y+i,orig_x,orig_y,orig_x,Colour);
                            break;
                        }
                        break;
                    }
                    activate_square_pink(myarray, orig_y+i,orig_x,orig_y,orig_x,Colour);
                }
            }
            for (int i = 1; i <= 8; i = i + 1) {
                Integer less_than_8x = orig_x + i;
                if (less_than_8x < 8) {
                    if (myarray[orig_y][orig_x + i].piece_color != null) {
                        if (myarray[orig_y][orig_x + i].piece_color == opp_colour) {
                            activate_square_pink(myarray, orig_y,orig_x+i,orig_y,orig_x,Colour);
                            break;
                        }
                        break;
                    }
                    activate_square_pink(myarray, orig_y,orig_x+i,orig_y,orig_x,Colour);
                }
            }
            for (int i = 1; i <= 8; i = i + 1) {
                Integer more_than_0x = orig_x - i;
                if (more_than_0x >= 0) {
                    if (myarray[orig_y][orig_x - i].piece_color != null) {
                        if (myarray[orig_y][orig_x - i].piece_color == opp_colour) {
                            activate_square_pink(myarray, orig_y,orig_x-i,orig_y,orig_x,Colour);
                        }
                        break;
                    }
                    activate_square_pink(myarray, orig_y,orig_x-i,orig_y,orig_x,Colour);
                }
            }
            for (int i = 1; i <= 8; i = i + 1) {
                Integer more_than_0y = orig_y - i;

                if (more_than_0y >= 0) {
                    if (myarray[orig_y - i][orig_x].piece_color != null) {
                        if (myarray[orig_y - 1][orig_x].piece_color == opp_colour) {
                            activate_square_pink(myarray, orig_y-i,orig_x,orig_y,orig_x,Colour);
                            break;
                        }
                        break;
                    }
                    activate_square_pink(myarray, orig_y-i,orig_x,orig_y,orig_x,Colour);
                }
            }
//
        }
        else{ pink_square(myarray, this);}

    }

    public void knight(Square[][] myarray, String Colour, String opp_colour) {
        if (this.getBackground()!=Color.pink) {
            update_board(myarray);
            Integer orig_x = this.x;
            Integer orig_y = this.y;
            if (orig_y+2 <= 7 && orig_x+1 <= 7 && myarray[orig_y+2][orig_x+1].piece_color!=opp_colour) {
                activate_square_pink(myarray, orig_y+2,orig_x+1,orig_y,orig_x,Colour);
            }
            if (orig_y+2 <= 7 && orig_x-1 >= 0 && myarray[orig_y+2][orig_x-1].piece_color!=opp_colour) {
                activate_square_pink(myarray, orig_y+2,orig_x-1,orig_y,orig_x,Colour);
            }
            if (orig_y-2 >= 0 && orig_x+1 <= 7 && myarray[orig_y-2][orig_x+1].piece_color!=opp_colour) {
                activate_square_pink(myarray, orig_y-2,orig_x+1,orig_y,orig_x,Colour);
            }
            if (orig_y-2 >= 0 && orig_x-1 >= 0 && myarray[orig_y-2][orig_x-1].piece_color!=opp_colour) {
                activate_square_pink(myarray, orig_y-2,orig_x-1,orig_y,orig_x,Colour);
            }
            if (orig_y+1 <= 7 && orig_x+2 <= 7 && myarray[orig_y+1][orig_x+2].piece_color!=opp_colour) {
                activate_square_pink(myarray, orig_y+1,orig_x+2,orig_y,orig_x,Colour);
            }
            if (orig_y-1 >= 0 && orig_x+2 <= 7 && myarray[orig_y-1][orig_x+2].piece_color!=opp_colour) {
                activate_square_pink(myarray, orig_y-1,orig_x+2,orig_y,orig_x,Colour);
            }
            if (orig_y+1 <= 7 && orig_x-2 >= 0 && myarray[orig_y+1][orig_x-2].piece_color!=opp_colour) {
                activate_square_pink(myarray, orig_y+1,orig_x-2,orig_y,orig_x,Colour);
            }
            if (orig_y-1 >= 0 && orig_x-2 >= 0 && myarray[orig_y-1][orig_x-2].piece_color!=opp_colour) {
                activate_square_pink(myarray, orig_y-1,orig_x-2,orig_y,orig_x,Colour);
            }}

        else{ pink_square(myarray, this);}

    }

    public void queen(Square[][] myarray, String colour, String opp_colour) {
        if (this.getBackground()!=Color.pink){
            update_board(myarray);
            Integer orig_x = this.x;
            Integer orig_y = this.y;
            for (int i = 1; i <= 8; i = i + 1) {
                Integer less_than_8y=orig_y+i;
                Integer less_than_8x=orig_x+i;
                if (less_than_8y < 8 && less_than_8x <8){
                    if (myarray[orig_y+i][orig_x+i].piece_color!=null) {
                        if (myarray[orig_y+i][orig_x+i].piece_color==opp_colour) {
                            activate_square_pink(myarray, orig_y+i,orig_x+i,orig_y,orig_x,colour);
                            break;
                        }
                        break;}
                    activate_square_pink(myarray, orig_y+i,orig_x+i,orig_y,orig_x,colour);
                    myarray[i][this.x].activating_Square_=myarray[orig_y][orig_x];

                }

            }
            for (int i = 1; i <= 8; i = i + 1) {
                Integer less_than_8x=orig_x+i;
                Integer more_than_0y = orig_y-i;

                if (less_than_8x < 8 && more_than_0y >=0){
                    if (myarray[orig_y-i][orig_x+i].piece_color!=null) {
                        if (myarray[orig_y-i][orig_x+i].piece_color==opp_colour) {
                            activate_square_pink(myarray, orig_y-i,orig_x+i,orig_y,orig_x,colour);
                            break;
                        }
                        break;}
                    activate_square_pink(myarray, orig_y-i,orig_x+i,orig_y,orig_x,colour);

                }

            }
            for (int i = 1; i <= 8; i = i + 1) {

                Integer more_than_0x = orig_x-i;
                Integer more_than_0y = orig_y-i;

                if (more_than_0x >= 0 && more_than_0y >=0){

                    if (myarray[orig_y-i][orig_x-i].piece_color!=null) {
                        if (myarray[orig_y-i][orig_x-i].piece_color==opp_colour) {
                            activate_square_pink(myarray, orig_y-i,orig_x,orig_y,orig_x,colour);
                        }
                        break;}
                    activate_square_pink(myarray, orig_y-i,orig_x-i,orig_y,orig_x,colour);
                }

            }
            for (int i = 1; i <= 8; i = i + 1) {
                Integer less_than_8y=orig_y+i;
                Integer more_than_0x = orig_x-i;
                if (less_than_8y < 8 && more_than_0x >=0){
                    if (myarray[orig_y+i][orig_x-i].piece_color!=null) {
                        if (myarray[orig_y+i][orig_x-i].piece_color==opp_colour) {
                            activate_square_pink(myarray, orig_y+i,orig_x-i,orig_y,orig_x,colour);}
                        break;
                    }
                    activate_square_pink(myarray, orig_y+i,orig_x-i,orig_y,orig_x,colour);
                }

            }

            for (int i = 1; i <= 8; i = i + 1) {
                Integer less_than_8y=orig_y+i;
                if (less_than_8y < 8){
                    if (myarray[orig_y+i][orig_x].piece_color!=null) {
                        if (myarray[orig_y+i][orig_x].piece_color==opp_colour) {
                            activate_square_pink(myarray, orig_y+i,orig_x,orig_y,orig_x,colour);
                            break;
                        }
                        break;}
                    activate_square_pink(myarray, orig_y+i,orig_x,orig_y,orig_x,colour);
                }
            }
            for (int i = 1; i <= 8; i = i + 1) {
                Integer less_than_8x=orig_x+i;
                if (less_than_8x < 8){
                    if (myarray[orig_y][orig_x+i].piece_color!=null) {
                        if (myarray[orig_y][orig_x+i].piece_color==opp_colour) {
                            activate_square_pink(myarray, orig_y,orig_x+i,orig_y,orig_x,colour);
                            break;}
                        break;}
                    activate_square_pink(myarray, orig_y,orig_x+i,orig_y,orig_x,colour);
                }
            }
            for (int i = 1; i <= 8; i = i + 1) {
                Integer more_than_0x = orig_x-i;
                if (more_than_0x >= 0){
                    if (myarray[orig_y][orig_x-i].piece_color!=null) {
                        if (myarray[orig_y][orig_x-i].piece_color==opp_colour) {
                            activate_square_pink(myarray, orig_y,orig_x-i,orig_y,orig_x,colour);
                                                                                }
                        break;}
                    activate_square_pink(myarray, orig_y,orig_x-i,orig_y,orig_x,colour);
                }
            }
            for (int i = 1; i <= 8; i = i + 1) {

                Integer more_than_0y = orig_y-i;

                if (more_than_0y >= 0){

                    if (myarray[orig_y-i][orig_x].piece_color!=null) {
                        if (myarray[orig_y-i][orig_x].piece_color==opp_colour) {
                            activate_square_pink(myarray, orig_y-i,orig_x,orig_y,orig_x,colour);
                            break;
                        }
                        break;
                    }
                    activate_square_pink(myarray, orig_y-i,orig_x,orig_y,orig_x,colour);
                }
            }}
        else{ pink_square(myarray, this);}


    }


    public void outp(Square[][] myarray) {
        if (this.getText() == null){
            this.blank(myarray);}
        else if(Objects.equals(this.getText(), "\u2659 ")) {
            this.white_pawn(myarray);
        }
        else if(Objects.equals(this.getText(), "\u2657 ")) {
            this.bishop(myarray,"\u2657 ","Black");
        }
        else if (Objects.equals(this.getText(), "\u2656 ")) {
            this.rook(myarray,"\u2656 ","Black");
        }
        else if (Objects.equals(this.getText(), "\u2658 ")) {
            this.knight(myarray,"\u2658 ","White");
        }
        else if (Objects.equals(this.getText(), "\u2655 ")) {//White
            this.queen(myarray,"\u2655 ", "Black" );
        }
        else if (Objects.equals(this.getText(), "\u2654 ")) {
            this.king(myarray,"\u2654 ","White");
        }
        else if(Objects.equals(this.getText(), "\u265F ")) {
            this.black_pawn(myarray);
        }
        else if(Objects.equals(this.getText(), "\u265C ")) {
            this.rook(myarray,"\u265C ","White");
        }
        else if(Objects.equals(this.getText(), "\u265E ")) {
            this.knight(myarray,"\u265E ", "Black" );
        }
        else if(Objects.equals(this.getText(), "\u265D ")) {
            this.bishop(myarray,"\u265D ","White");
        }
        else if(Objects.equals(this.getText(), "\u265B ")) {
            this.queen(myarray,"\u265B ", "White");}

        else if (Objects.equals(this.getText(), "\u265A ")) {
            this.king(myarray,"\u265A ","Black");
        }
        }

    public void white_checkmate(Square[][] myarray) {
        //arrives as board with pink as activated by checking piece
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                myarray[i][j].setBackground(myarray[i][j].original_color);
//board is returned to black and white
            }
        }
        Integer howmany=0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Objects.equals(myarray[i][j].piece_color, "Black")) {
                    Square[][] copy_myarray = myarray.clone();
                    Square square_of_interest = copy_myarray[i][j];
                    if (Objects.equals(square_of_interest.getText(), "\u265F ")) {
                        square_of_interest.black_pawn(copy_myarray);
                    } else if (Objects.equals(square_of_interest.getText(), "\u265D ")) {
                        square_of_interest.bishop(copy_myarray,"\u265D ","White");
                    } else if (Objects.equals(square_of_interest.getText(), "\u265C ")) {
                        square_of_interest.rook(copy_myarray,"\u265C ","White");
                    } else if (Objects.equals(square_of_interest.getText(), "\u265E ")) {
                        square_of_interest.knight(copy_myarray,"\u265E ","White");
                    } else if (Objects.equals(square_of_interest.getText(), "\u265B ")) {
                        square_of_interest.queen(copy_myarray,"\u265B ","White");
                    }
                    //board will now have pink squares due to black activation
                    //the following acitvates the
                    howmany = howmany + 1;
                    for (int i1 = 0; i1 < 8; i1++) {
                        for (int j1 = 0; j1 < 8; j1++) {
                            if (copy_myarray[i1][j1].getBackground()==Color.pink) {
                                Square[][] copy_copy_myarray = copy_myarray.clone();

                                Square square_of_interest2 = copy_myarray[i1][j1];
                                if (square_of_interest2.getText() == null){
                                    square_of_interest2.blank(copy_copy_myarray );
                                    for (int i2 = 0; i2 < 8; i2++) {
                                        for (int j2 = 0; j2 < 8; j2++) {
                                            prelim_white_check(copy_copy_myarray,copy_copy_myarray[i2][j2]);

                                        }}
                                }
                                else if(Objects.equals(square_of_interest2.getText(), "\u2659 ")) {
                                    square_of_interest2.white_pawn(copy_copy_myarray );
                                    for (int i2 = 0; i2 < 8; i2++) {
                                        for (int j2 = 0; j2 < 8; j2++) {
                                            prelim_white_check(copy_copy_myarray,copy_copy_myarray[i2][j2]);

                                        }}

                                }
                                else if(Objects.equals(square_of_interest2.getText(), "\u2657 ")) {
                                    square_of_interest2.bishop(copy_copy_myarray,"\u2657 ","Black" );
                                    for (int i2 = 0; i2 < 8; i2++) {
                                        for (int j2 = 0; j2 < 8; j2++) {
                                            prelim_white_check(copy_copy_myarray,copy_copy_myarray[i2][j2]);

                                        }}
                                }
                                else if (Objects.equals(square_of_interest2.getText(), "\u2656 ")) {
                                    square_of_interest2.rook(copy_copy_myarray,"\u2656 ","Black");
                                    for (int i2 = 0; i2 < 8; i2++) {
                                        for (int j2 = 0; j2 < 8; j2++) {
                                            prelim_white_check(copy_copy_myarray,copy_copy_myarray[i2][j2]);

                                        }}
                                }
                                else if (Objects.equals(square_of_interest2.getText(), "\u2658 ")) {
                                    square_of_interest2.knight(copy_copy_myarray,"\u2658 ","Black");
                                    for (int i2 = 0; i2 < 8; i2++) {
                                        for (int j2 = 0; j2 < 8; j2++) {
                                            prelim_white_check(copy_copy_myarray,copy_copy_myarray[i2][j2]);

                                        }}
                                }
                                else if (Objects.equals(square_of_interest2.getText(), "\u2655 ")) {
                                    square_of_interest2.queen(copy_copy_myarray,"\u2655 ", "Black" );
                                    for (int i2 = 0; i2 < 8; i2++) {
                                        for (int j2 = 0; j2 < 8; j2++) {
                                            prelim_white_check(copy_copy_myarray,copy_copy_myarray[i2][j2]);

                                        }}
                                }
                                else if (Objects.equals(square_of_interest2.getText(), "\u2654 ")) {
                                    square_of_interest2.king(copy_copy_myarray,"\u2654 ","Black");
                                    for (int i2 = 0; i2 < 8; i2++) {
                                        for (int j2 = 0; j2 < 8; j2++) {
                                            prelim_white_check(copy_copy_myarray,copy_copy_myarray[i2][j2]);
                                        }}
                                }


                            }
                        }
                    }
                }
            }
        }
  }

    public void prelim_white_check(Square[][] myarray, Square piece) {
        if (Objects.equals(piece.getText(), "\u2659 ")) {
            piece.white_pawn(myarray);
        } else if (Objects.equals(piece.getText(), "\u2657 ")) {
            piece.bishop(myarray,"\u2657 ","Black");
        } else if (Objects.equals(piece.getText(), "\u2656 ")) {
            piece.rook(myarray,"\u2656 ","Black");
        } else if (Objects.equals(piece.getText(), "\u2658 ")) {
            this.knight(myarray,"\u2658 ","Black");
        } else if (Objects.equals(piece.getText(), "\u2655 ")) {
            this.queen(myarray, "\u2655 ", "Black");
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (myarray[i][j].getBackground()==Color.pink && Objects.equals(myarray[i][j].getText(), "\u265A ")){
                    System.out.println("CHECK AGAIN");
                }
                else if (myarray[i][j].getBackground()==Color.pink && !Objects.equals(myarray[i][j].getText(), "\u265A ")) {
                    System.out.println("Not a check");

                }

            }}
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                myarray[i][j].setBackground(myarray[i][j].original_color);
                myarray[i][j].piece_color=piece_color_func(myarray[i][j].piece_color);
            }}


    }

    public void white_check(Square[][] myarray, Square piece) {
//         if (Objects.equals(piece.getText(), "\u2659 ")) {
//            piece.white_pawn(myarray);
//        } else if (Objects.equals(piece.getText(), "\u2657 ")) {
//            piece.white_bishop(myarray);
//        } else if (Objects.equals(piece.getText(), "\u2656 ")) {
//            piece.white_rook(myarray);
//        } else if (Objects.equals(piece.getText(), "\u2658 ")) {
//            this.white_knight(myarray);
//        } else if (Objects.equals(piece.getText(), "\u2655 ")) {
//            this.white_queen(myarray);
//        }
//
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                if (myarray[i][j].getBackground()==Color.pink && myarray[i][j].getText()=="\u265A "){
//                    System.out.println("CHECK");
//                    white_checkmate(myarray);
//
//                }
//
//            }}
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                myarray[i][j].setBackground(myarray[i][j].original_color);
//                myarray[i][j].piece_color=piece_color_func(myarray[i][j].piece_color);
//            }}
//

    }

    static HashMap<String,String> starting_positions() {
        HashMap<String,String> starting_layout = new HashMap<String,String>();
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H"};
        Integer[] numbers = {8,7,6,5,4,3,2,1};

        for (Integer number: numbers) {
            for (String letter:letters) {
                String string_number = number.toString();
                String notation = letter + string_number;
                starting_layout.put(notation,null);
            }
        }
        starting_layout.put("A1","\u2656 ");
        starting_layout.put("B1","\u2658 ");
        starting_layout.put("C1","\u2657 ");
        starting_layout.put("D1","\u2655 ");
        starting_layout.put("E1","\u2654 ");
        starting_layout.put("F1","\u2657 ");
        starting_layout.put("G1","\u2658 ");
        starting_layout.put("H1","\u2656 ");
        starting_layout.put("A2","\u2659 ");
        starting_layout.put("B2","\u2659 ");
        starting_layout.put("C2","\u2659 ");
        starting_layout.put("D2","\u2659 ");
        starting_layout.put("E2","\u2659 ");
        starting_layout.put("F2","\u2659 ");
        starting_layout.put("G2","\u2659 ");
        starting_layout.put("H2","\u2659 ");
        starting_layout.put("A7","\u265F ");
        starting_layout.put("B7","\u265F ");
        starting_layout.put("C7","\u265F ");
        starting_layout.put("D7","\u265F ");
        starting_layout.put("E7","\u265F ");
        starting_layout.put("F7","\u265F ");
        starting_layout.put("G7","\u265F ");
        starting_layout.put("H7","\u265F ");
        starting_layout.put("A8","\u265C ");
        starting_layout.put("B8","\u265E ");
        starting_layout.put("C8","\u265D ");
        starting_layout.put("D8","\u265B ");
        starting_layout.put("E8","\u265A ");
        starting_layout.put("F8","\u265D ");
        starting_layout.put("G8","\u265E ");
        starting_layout.put("H8","\u265C ");
        return starting_layout;
    }

    Square(String word,Color colour, Integer Y_coord, Integer X_coord, String alge_notation) {
        setText(word);
        setBackground(colour);
        this.y = Y_coord;
        this.x = X_coord;
        this.alge_notation = alge_notation;
    }

}
