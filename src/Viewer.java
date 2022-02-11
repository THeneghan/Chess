import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Viewer {

    public static void main(String[] args) {
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H"};
        Integer[] numbers = {8,7,6,5,4,3,2,1};
        HashMap<String,Integer> color_assist = new HashMap<>();
        color_assist.put("A",0);
        color_assist.put("B",1);
        color_assist.put("C",0);
        color_assist.put("D",1);
        color_assist.put("E",0);
        color_assist.put("F",1);
        color_assist.put("G",0);
        color_assist.put("H",1);







        JFrame brd = new JFrame("My Chess GUI");
        brd.setSize(300,300);
        brd.setLayout(new FlowLayout(FlowLayout.CENTER));
        brd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pnl = new JPanel();

        //pnl.setSize(100,100);
        pnl.setPreferredSize(new Dimension(400, 400));
        pnl.setBackground(Color.CYAN);
        pnl.setLayout(new GridLayout(8,8,0,0));
        Square[][] myarray = new Square[8][8];
        Integer y_coord=7;

        for (Integer number: numbers) {
            Integer x_coord=0;
            for (String letter:letters) {
                Integer int1 = color_assist.get(letter);
                Integer int_sum = int1 + number;
                String string_number = number.toString();
                String notation = letter + string_number;
                System.out.println(notation);
                System.out.println(y_coord.toString() + " " + x_coord.toString());
                if (int_sum %2 ==0) {
                    Square square = new Square(null,Color.WHITE, y_coord, x_coord, notation);
                    square.original_color= Color.WHITE;
                    myarray[y_coord][x_coord]=square;
                    square.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            square.outp(myarray);
                        }
                    });
                pnl.add(square);}
                else {
                    Square square = new Square(null,Color.BLACK, y_coord,x_coord, notation);
                    square.original_color=Color.BLACK;
                    myarray[y_coord][x_coord]=square;
                    square.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println("this");
                            square.outp(myarray);
                        }
                    });
                    pnl.add(square);
                }
                x_coord=x_coord+1;
            }
            y_coord=y_coord-1;
        }

        HashMap<String,String > starting_board=Square.starting_positions();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String key = myarray[i][j].alge_notation;
                String chess_piece =starting_board.get(key);
                myarray[i][j].setText(chess_piece);

            }
        }
        brd.add(pnl);
        brd.setVisible(true);
    }
}