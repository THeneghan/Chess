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
            }}

    }

    public void activate_square_pink(VirtualSquare[][] myarray, Integer i, Integer j, Integer orig_y, Integer orig_x, String piece) {
        myarray[i][j].Background=Color.pink;
        myarray[i][j].activating_piece = piece;
        myarray[i][j].activating_Square_ = myarray[orig_y][orig_x];
        myarray[orig_y][orig_x].piece_color=piece_color_func(myarray[orig_y][orig_x].Text);
    }


    public void black_pawn(VirtualSquare[][] myarray) {
        if (this.Background!=Color.pink) {
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


    public void checkmate(Square[][] myarray) {
        Integer count=0;
        Integer count1=0;
        VirtualSquare[][] virtualarray = new VirtualSquare[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square val= myarray[i][j];
                VirtualSquare virt_square = new VirtualSquare(val.y,val.x, val.alge_notation,val.activating_piece,val.original_color,val.getText(),val.getBackground());
            }}
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (virtualarray[i][j].piece_color=="Black"){
                    System.out.println(virtualarray[i][j].Text);
                    System.out.println(virtualarray[i][j].alge_notation);
                    Integer count3=0;
                    VirtualSquare[][] copy_virtualarray = virtualarray.clone();
                    VirtualSquare.activate_black_piece_virtual(copy_virtualarray,copy_virtualarray[i][j]);
                    for (int i1 = 0; i1 < 8; i1++) {
                        for (int j1 = 0; j1 < 8; j1++) {
                            if (copy_virtualarray[i1][j1].Background==Color.pink) {
                                count3=count3+1;
                                VirtualSquare[][] copy_copy_myarray = copy_virtualarray.clone();
                                // pink_square2(copy_copy_myarray, i1,j1);
                            }
                        }
                    }
                    System.out.println(count3);
                    count = count+count3;
                    //outp on for loop
                }
            }
        }
        System.out.println(count);
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

    public static void pink_square2(VirtualSquare[][] myarray, Integer i, Integer j){

        myarray[i][j].Text=(myarray[i][j].activating_piece);
        myarray[i][j].piece_color=piece_color_func(myarray[i][j].Text);
        myarray[i][j].Background=(myarray[i][j].original_color);
        myarray[i][j].activating_Square_.Text=(null);
        myarray[i][j].activating_piece=null;
        myarray[i][j].activating_Square_=null;
        //check_loop(myarray);
        //update_board(myarray);

    }

    public void white_pawn(VirtualSquare[][] myarray) {
        if (this.Background!=Color.pink) {
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
                    if (myarray[i][this.x].piece_color == null) {
                        activate_square_pink(myarray, i,this.x,orig_y,orig_x,"\u2659 ");
                    }
                    else {break;}
                }
            } else {
                for (int i = this.y + 1; i <= this.y + 1; i = i + 1) {
                    if (i==8 | myarray[i][this.x].piece_color == "White"  ) {break;}

                    if (myarray[i][this.x].piece_color != "Black") {
                        activate_square_pink(myarray, i,this.x,orig_y,orig_x,"\u2659 ");
                    }
                }

            }}
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
            piece.white_pawn(myarray);
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
            piece.black_pawn(myarray);
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
        System.out.println("here");
        outp(myarray, piece);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (myarray[i][j].Background==Color.pink)
                {System.out.println("pink");
                    System.out.println(myarray[i][j].activating_piece);}

            }}
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (myarray[i][j].Background==Color.pink && Objects.equals(myarray[i][j].Text, "\u265A ")){
                    System.out.println("Black is in check");
                    update_board(myarray);
                    //sends it as black and white
                }
                else if (myarray[i][j].Background==Color.pink && Objects.equals(myarray[i][j].Text, "\u2654 ")){
                    System.out.println("White is in check");
                }

            }}
        update_board(myarray);

    }

//    public void check_loop(VirtualSquare[][] myarray) {
//
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                check(myarray,myarray[i][j]);
//            }}
//    }

    public void pink_square(VirtualSquare[][] myarray, VirtualSquare piece){

        piece.Text=(piece.activating_piece);
        piece.piece_color=piece_color_func(piece.Text);
        piece.Background=(piece.original_color);
        piece.activating_Square_.Text=(null);
        piece.activating_piece=null;
        piece.activating_Square_=(null);

        //check_loop(myarray);
        //update_board(myarray);


    }

    public static void activate_black_piece_virtual(VirtualSquare[][] myarray, VirtualSquare piece) {
        if(Objects.equals(piece.Text, "\u265F ")) {
            piece.black_pawn(myarray);
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


//    Color original_color;
//    VirtualSquare activating_Square_;
//    String Text;
//    String piece_color;
//    Color Background;

