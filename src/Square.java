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
    String activating_square;
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

    public void blank(Square[][] myarray) {

        if (this.getBackground()!=Color.pink) {
                System.out.println("blank");
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        myarray[i][j].setBackground(myarray[i][j].original_color);

        }}}
        else {System.out.println("pink");
        this.setText(this.activating_piece);
        System.out.println("text is");
        System.out.println(myarray[this.y][this.x].getText());
        this.piece_color=piece_color_func(this.getText());
        this.setBackground(this.original_color);
        System.out.println(this.activating_Square_.alge_notation);
        this.activating_Square_.setText(null);
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    myarray[i][j].setBackground(myarray[i][j].original_color);

                }}

        }
    }

    public void white_pawn(Square[][] myarray) {

        System.out.println("white pawn");
        System.out.println(this.piece_color);
        Integer orig_x = this.x;
        Integer orig_y = this.y;
        System.out.println(this.x);
        System.out.println(this.y);
        System.out.println(this.alge_notation);
        String value = this.alge_notation;
        for (int i = this.y+1; i <= this.y+1; i = i + 1) {

            if (this.x -1>=0 && (myarray[i][this.x-1].piece_color=="Black")) {
                myarray[i][this.x-1].setBackground(Color.pink);
                myarray[i][this.x-1].activating_piece="\u2659 ";
                myarray[i][this.x-1].activating_Square_=myarray[orig_y][orig_x];

            }
        }
        for (int i = this.y+1; i <= this.y+1; i = i + 1) {
            if (this.x +1<=8 && (myarray[i][this.x+1].piece_color=="Black")) {
                myarray[i][this.x+1].setBackground(Color.pink);
                myarray[i][this.x+1].activating_piece="\u2659 ";
                myarray[i][this.x+1].activating_Square_=myarray[orig_y][orig_x];
            }
        }
        if (this.alge_notation.contains("2")) {
            for (int i = this.y+1; i <= this.y+2; i = i + 1) {
                if (myarray[i][this.x].piece_color!="Black") {
                myarray[i][this.x].setBackground(Color.pink);
                myarray[i][this.x].activating_piece="\u2659 ";
                //myarray[i][this.x].activating_square=this.alge_notation;
                myarray[i][this.x].activating_Square_=myarray[orig_y][orig_x];
                System.out.println(myarray[i][this.x].activating_Square_.alge_notation);}
            }
        }
        else {
            for (int i = this.y+1; i <= this.y+1; i = i + 1) {
                if (myarray[i][this.x].piece_color!="Black") {
                myarray[i][this.x].setBackground(Color.pink);
                myarray[i][this.x].activating_piece="\u2659 ";
                //myarray[i][this.x].activating_square=this.alge_notation;
                myarray[i][this.x].activating_Square_=myarray[orig_y][orig_x];
                System.out.println(myarray[i][this.x].activating_Square_.alge_notation);}
            }

        }

    }

    public void white_bishop(Square[][] myarray) {

        Integer orig_x = this.x;
        Integer orig_y = this.y;
        System.out.println("White bishop");
        for (int i = 1; i <= 8; i = i + 1) {
            Integer less_than_8y=orig_y+i;
            Integer less_than_8x=orig_x+i;
            Integer more_than_0y = orig_y-i;
            Integer more_than_0x = orig_x-i;
            if (less_than_8y < 8 && less_than_8x <8){
            myarray[orig_y+i][orig_x+i].setBackground(Color.pink);}
            if (less_than_8y < 8 && more_than_0x >=0){
                myarray[orig_y+i][orig_x-i].setBackground(Color.pink);}




        }
    }

    public void black_pawn(Square[][] myarray) {
        if (this.getBackground()!=Color.pink) {{

        System.out.println(this.piece_color);

        System.out.println("black pawn");}}

        else{{System.out.println("pink");
            this.setText(this.activating_piece);
            System.out.println("text is");
            System.out.println(myarray[this.y][this.x].getText());
            this.piece_color=piece_color_func(this.getText());
            this.setBackground(this.original_color);
            System.out.println(this.activating_Square_.alge_notation);
            this.activating_Square_.setText(null);
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    myarray[i][j].setBackground(myarray[i][j].original_color);

                }}}

        }

    }


    public void outp(Square[][] myarray) {
        if (this.getText() == null){
            this.blank(myarray);}
        else if(Objects.equals(this.getText(), "\u2659 ")) {
            this.white_pawn(myarray);
        }
        else if(Objects.equals(this.getText(), "\u2657 ")) {
            this.white_bishop(myarray);
        }
        else if(Objects.equals(this.getText(), "\u265F ")) {
            this.black_pawn(myarray);
        }

        else {System.out.println("bad luck");
        System.out.println(this.piece_color);
        System.out.println(this.getText());
        if (this.getText() == "\u2659 ") {System.out.println("yes equal why not working");}}
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

    public static void main(String[] args) {


    }



}
