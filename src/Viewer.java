import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Objects;

/** This class acts as the entrypoint to the Chess GUI. */

public class Viewer {

    public static void main(String[] args) {
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H"};
        Integer[] numbers = {8,7,6,5,4,3,2,1};
        HashMap<String,Integer> color_assist = new HashMap<>(); //Created to colour code squares
        color_assist.put("A",0);
        color_assist.put("B",1);
        color_assist.put("C",0);
        color_assist.put("D",1);
        color_assist.put("E",0);
        color_assist.put("F",1);
        color_assist.put("G",0);
        color_assist.put("H",1);
        JFrame board = new JFrame("My Chess GUI"); //Parent component
        board.setSize(300,300);
        board.setLayout(new FlowLayout(FlowLayout.CENTER));
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 400));
        panel.setBackground(Color.CYAN);
        panel.setLayout(new GridLayout(8,8,0,0));
        Square[][] myarray = new Square[8][8]; //Fundamental principle is board is 8x8 array of Square objects
        //Creation of empty board
        int y_coord=7;
        for (Integer number: numbers) {
            int x_coord=0;
            for (String letter:letters) {
                Integer counter = color_assist.get(letter);
                int int_sum = counter + number;
                String string_number = number.toString();
                String notation = letter + string_number;
                Square square;
                if (int_sum %2 ==0) {
                    square = new Square(null, Color.WHITE, y_coord, x_coord, notation);
                    square.original_color= Color.WHITE;
                }
                else {
                    square = new Square(null, Color.BLACK, y_coord, x_coord, notation);
                    square.original_color=Color.BLACK;
                }
                myarray[y_coord][x_coord]=square;
                panel.add(square);
                x_coord=x_coord+1;
            }
            y_coord=y_coord-1;
        }
        //Populate relevant squares with pieces (unicode text) and other fields
        HashMap<String,String > starting_board= Square.starting_positions();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String key = myarray[i][j].algebraic_notation;
                String chess_piece =starting_board.get(key);
                myarray[i][j].setText(chess_piece);
                myarray[i][j].piece_color= Square.piece_color_func(myarray[i][j].getText());
                if (Objects.equals(myarray[i][j].piece_color, "White")) {
                    myarray[i][j].activated = Boolean.TRUE;
                }
                else if (Objects.equals(myarray[i][j].piece_color, "Black")) {
                    myarray[i][j].activated = Boolean.FALSE;
                }
                /*  Cast square to each element of array and add action listener so pushing the square (modified JButton)
                 executes outp method */
                Square square =myarray[i][j];
                square.addActionListener(e -> square.outp(myarray,square));
            }
        }
        board.add(panel);
        board.setVisible(true);
    }
}