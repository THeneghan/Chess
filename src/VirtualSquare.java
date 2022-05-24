import java.awt.*;
import java.util.Objects;

public class VirtualSquare {
    Integer y;
    Integer x;
    String alge_notation;
    String activating_piece;
    Color original_color;
    VirtualSquare activating_Square_;
    String Text;
    String piece_color;
    Color Background;

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

    public static void update_board(VirtualSquare[][] myarray) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                myarray[i][j].Background = (myarray[i][j].original_color);
                myarray[i][j].piece_color=piece_color_func(myarray[i][j].Text);
                myarray[i][j].activating_Square_=null;
                myarray[i][j].activating_piece=null;

            }}

    }

    public void activate_square_pink(VirtualSquare[][] myarray, Integer i, Integer j, Integer orig_y, Integer orig_x, String piece) {
        myarray[i][j].Background=Color.pink;
        myarray[i][j].activating_piece = piece;
        myarray[i][j].activating_Square_ = myarray[orig_y][orig_x];
        myarray[orig_y][orig_x].piece_color=piece_color_func(myarray[orig_y][orig_x].Text);
    }

    public void blank(VirtualSquare[][] myarray) {
        if (this.Background!=Color.pink) {
            update_board(myarray);
        }
        else { pink_square(myarray, this); }
    }

    public void bishop(VirtualSquare[][] myarray, String colour, String opp_colour) {
        if (this.Background!=Color.pink){
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

    public void pawn(VirtualSquare[][] myarray, String colour) {
        if (this.Background!=Color.pink) {
            update_board(myarray);
            Integer orig_x = this.x;
            Integer orig_y = this.y;

            if (colour == "Black") {

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
                        if (myarray[i][this.x].piece_color==null) {
                            activate_square_pink(myarray, i,this.x,orig_y,orig_x,"\u265F ");
                        }
                        else {break;}
                    }
                }
                else {
                    for (int i = this.y-1; i >= this.y-1; i = i - 1) {
                        if (i+1==0) {break;}
                        if (myarray[i][this.x].piece_color==null) {
                            activate_square_pink(myarray, i,this.x,orig_y,orig_x,"\u265F ");
                        }
                    }

                }}

            else if(colour == "White") {

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
                        if (myarray[i][this.x].piece_color == null) {
                            activate_square_pink(myarray, i,this.x,orig_y,orig_x,"\u2659 ");
                        }
                        else {break;}
                    }
                } else {
                    for (int i = this.y + 1; i <= this.y + 1; i = i + 1) {
                        if (i==8) {break;} // | myarray[i][this.x].piece_color == "White"

                        if (myarray[i][this.x].piece_color == null) {
                            activate_square_pink(myarray, i,this.x,orig_y,orig_x,"\u2659 ");
                        }
                    }

                }
            }

        }
        else{ pink_square(myarray, this);}


    }

    public void rook(VirtualSquare[][] myarray, String Colour, String opp_colour) {
        if (this.Background!=Color.pink) {
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
                        if (myarray[orig_y - i][orig_x].piece_color == opp_colour) {
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

    public void knight(VirtualSquare[][] myarray, String Colour, String opp_colour) {
        if (this.Background!=Color.pink) {
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

    public void queen(VirtualSquare[][] myarray, String colour, String opp_colour) {
        if (this.Background!=Color.pink){
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
                            //break;
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

    public void king(VirtualSquare[][] myarray, String Colour, String opp_colour) {
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

    public static void outp(VirtualSquare[][] myarray, VirtualSquare piece) {
        if (piece.Text == null){
            piece.blank(myarray);}
        else if(Objects.equals(piece.Text, "\u2659 ")) {
            piece.pawn(myarray, "White");
        }
        else if(Objects.equals(piece.Text, "\u2657 ")) {
            piece.bishop(myarray,"\u2657 ","Black");
        }
        else if (Objects.equals(piece.Text, "\u2656 ")) {
            piece.rook(myarray,"\u2656 ","Black");
        }
        else if (Objects.equals(piece.Text, "\u2658 ")) {
            piece.knight(myarray,"\u2658 ","White");
        }
        else if (Objects.equals(piece.Text, "\u2655 ")) {//White
            piece.queen(myarray,"\u2655 ", "Black" );
        }
        else if (Objects.equals(piece.Text, "\u2654 ")) {
            piece.king(myarray,"\u2654 ","White");
        }
        else if(Objects.equals(piece.Text, "\u265F ")) {
            piece.pawn(myarray, "Black");
        }
        else if(Objects.equals(piece.Text, "\u265C ")) {
            piece.rook(myarray,"\u265C ","White");
        }
        else if(Objects.equals(piece.Text, "\u265E ")) {
            piece.knight(myarray,"\u265E ", "Black" );
        }
        else if(Objects.equals(piece.Text, "\u265D ")) {
            piece.bishop(myarray,"\u265D ","White");
        }
        else if(Objects.equals(piece.Text, "\u265B ")) {
            piece.queen(myarray,"\u265B ", "White");}

        else if (Objects.equals(piece.Text, "\u265A ")) {
            piece.king(myarray,"\u265A ","Black");
        }
    }

    public static void check(VirtualSquare[][] myarray, VirtualSquare piece) {
        outp(myarray, piece);
        for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                if (myarray[i][j].Background==Color.pink && Objects.equals(myarray[i][j].Text, "\u265A ")){
                    System.out.println("Black is in check");
                    update_board(myarray);
                }
                else if (myarray[i][j].Background==Color.pink && Objects.equals(myarray[i][j].Text, "\u2654 ")){
                    System.out.println("White is in check");
                    update_board(myarray);
                }
            }}
        update_board(myarray);
    }

    public static void check_loop(VirtualSquare[][] myarray) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                VirtualSquare mysquare = myarray[i][j];
                check(myarray,mysquare);
            }}

    }

//    public static void self_check_loop1(VirtualSquare[][] myarray) {
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                VirtualSquare mysquare = myarray[i][j];
//                selfcheck(myarray,mysquare, colour);
//            }}
//
//    }

    public static Integer[] selfcheck(VirtualSquare[][] myarray, VirtualSquare piece, String colour) {
        outp(myarray, piece);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (colour == "Black") {
                if (myarray[i][j].Background==Color.pink && Objects.equals(myarray[i][j].Text, "\u265A ")){
                    Integer[] wipes = {piece.y, piece.x};
                    update_board(myarray);
                    return wipes;
                }
            }
            else if(colour == "White") {
                    if (myarray[i][j].Background==Color.pink && Objects.equals(myarray[i][j].Text, "\u2654 ")){
                        Integer[] wipes = {piece.y, piece.x};
                        update_board(myarray);
                        return wipes;
                    }

                }
            }}
        update_board(myarray);
        Integer[] dump ={-1,-1};
        return dump;
    }








    public void pink_square(VirtualSquare[][] myarray, VirtualSquare piece){

        piece.Text=(piece.activating_piece);
        piece.piece_color=piece_color_func(piece.Text);
        piece.Background=(piece.original_color);
        piece.activating_Square_.Text=null;

        piece.activating_piece=null;
        piece.activating_Square_=(null);


        //check_loop(myarray);
        update_board(myarray);


    }

    VirtualSquare(Integer y1,Integer x1, String alge_not,String acti_piece, Color ori_col, String txt, Color bkgrd) {
        this.y= y1;
        this.x= x1;
        this.alge_notation= alge_not;
        this.activating_piece = acti_piece;
        this.original_color = ori_col;
        this.Text = txt;
        this.piece_color = piece_color_func(this.Text);
        this.Background = bkgrd;
    }

}


