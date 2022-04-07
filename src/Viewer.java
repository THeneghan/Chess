import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Objects;

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
        JFrame board = new JFrame("My Chess GUI");
        board.setSize(300,300);
        board.setLayout(new FlowLayout(FlowLayout.CENTER));
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 400));
        panel.setBackground(Color.CYAN);
        panel.setLayout(new GridLayout(8,8,0,0));
        Square[][] myarray = new Square[8][8];
        Integer y_coord=7;
        for (Integer number: numbers) {
            Integer x_coord=0;
            for (String letter:letters) {
                Integer int1 = color_assist.get(letter);
                Integer int_sum = int1 + number;
                String string_number = number.toString();
                String notation = letter + string_number;
                if (int_sum %2 ==0) {
                    Square square = new Square(null,Color.WHITE, y_coord, x_coord, notation);
                    square.original_color= Color.WHITE;
                    myarray[y_coord][x_coord]=square;


                panel.add(square);}
                else {
                    Square square = new Square(null,Color.BLACK, y_coord,x_coord, notation);
                    square.original_color=Color.BLACK;
                    myarray[y_coord][x_coord]=square;
                    panel.add(square);
                }
                x_coord=x_coord+1;
            }
            y_coord=y_coord-1;
        }
        HashMap<String,String > starting_board= Square.starting_positions();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String key = myarray[i][j].alge_notation;
                String chess_piece =starting_board.get(key);
                myarray[i][j].setText(chess_piece);
                myarray[i][j].piece_color= Square.piece_color_func(myarray[i][j].getText());
                if (Objects.equals(myarray[i][j].piece_color, "White")) {
                    myarray[i][j].activateable = Boolean.TRUE;
                }
                else if (Objects.equals(myarray[i][j].piece_color, "Black")) {
                    myarray[i][j].activateable = Boolean.FALSE;
                }
                Square square =myarray[i][j];
                square.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        square.outp(myarray,square);
                    }
                });
            }
        }
        board.add(panel);
        board.setVisible(true);
    }
}