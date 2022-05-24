import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Objects;

public class Square extends JButton {
    Integer y;
    Integer x;
    String alge_notation;
    String activating_piece;
    Color original_color;
    Square activating_Square_;
    String piece_color = piece_color_func(this.getText());
    Boolean activateable;
    Boolean text_activateable;

    Square(String word, Color colour, Integer Y_coord, Integer X_coord, String alge_notation) {
        setText(word);
        setBackground(colour);
        this.y = Y_coord;
        this.x = X_coord;
        this.alge_notation = alge_notation;
    }

    static String piece_color_func(String val) {
        if (Objects.equals(val, "\u2659 ") || Objects.equals(val, "\u2654 ") || Objects.equals(val, "\u2655 ")
                || Objects.equals(val, "\u2656 ") || Objects.equals(val, "\u2657 ") || Objects.equals(val, "\u2658 ")) {
            return "White";
        } else if (Objects.equals(val, "\u265F ") || Objects.equals(val, "\u265A ") || Objects.equals(val, "\u265B ")
                || Objects.equals(val, "\u265C ") || Objects.equals(val, "\u265D ") || Objects.equals(val, "\u265E ")) {
            return "Black";
        } else {
            return null;
        }
    }

    static HashMap<String, String> starting_positions() {
        HashMap<String, String> starting_layout = new HashMap<String, String>();
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H"};
        Integer[] numbers = {8, 7, 6, 5, 4, 3, 2, 1};

        for (Integer number : numbers) {
            for (String letter : letters) {
                String string_number = number.toString();
                String notation = letter + string_number;
                starting_layout.put(notation, null);
            }
        }
        starting_layout.put("A1", "\u2656 ");
        starting_layout.put("B1", "\u2658 ");
        starting_layout.put("C1", "\u2657 ");
        starting_layout.put("D1", "\u2655 ");
        starting_layout.put("E1", "\u2654 ");
        starting_layout.put("F1", "\u2657 ");
        starting_layout.put("G1", "\u2658 ");
        starting_layout.put("H1", "\u2656 ");
        starting_layout.put("A2", "\u2659 ");
        starting_layout.put("B2", "\u2659 ");
        starting_layout.put("C2", "\u2659 ");
        starting_layout.put("D2", "\u2659 ");
        starting_layout.put("E2", "\u2659 ");
        starting_layout.put("F2", "\u2659 ");
        starting_layout.put("G2", "\u2659 ");
        starting_layout.put("H2", "\u2659 ");
        starting_layout.put("A7", "\u265F ");
        starting_layout.put("B7", "\u265F ");
        starting_layout.put("C7", "\u265F ");
        starting_layout.put("D7", "\u265F ");
        starting_layout.put("E7", "\u265F ");
        starting_layout.put("F7", "\u265F ");
        starting_layout.put("G7", "\u265F ");
        starting_layout.put("H7", "\u265F ");
        starting_layout.put("A8", "\u265C ");
        starting_layout.put("B8", "\u265E ");
        starting_layout.put("C8", "\u265D ");
        starting_layout.put("D8", "\u265B ");
        starting_layout.put("E8", "\u265A ");
        starting_layout.put("F8", "\u265D ");
        starting_layout.put("G8", "\u265E ");
        starting_layout.put("H8", "\u265C ");
        return starting_layout;
    }

    public void pink_square(Square[][] myarray, Square piece) {

        piece.setText(piece.activating_piece);
        piece.piece_color = piece_color_func(piece.getText());
        piece.setBackground(piece.original_color);
        piece.activateable = Boolean.TRUE;
        piece.text_activateable = null;
        piece.activating_Square_.setText(null);
        piece.activating_Square_.activateable = null;
        piece.activating_piece = null;
        piece.activating_Square_ = null;
        switch_player(myarray,piece);
        Boolean val = virtual_board_check(myarray);
        //New addition
        checkmate_check(myarray, piece, val);
    }

    public void checkmate_check(Square[][] myarray, Square piece, Boolean val) {
        Integer counter=0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                outp(myarray,myarray[i][j]);
                for (int i1 = 0; i1 < 8; i1++) {
                    for (int j1 = 0; j1 < 8; j1++) {
                        if (myarray[i1][j1].getBackground()==Color.pink) {
                            counter++;
                        }
                    }}
                clean_board(myarray);

            }}
        System.out.println(counter);
        if (counter==0 && val == Boolean.TRUE) {
            JFrame parent = new JFrame("Checkmate");
            parent.setSize(300,300);
            parent.setLayout(new FlowLayout(FlowLayout.CENTER));


            String winner = piece_color + " " + "wins";
            JLabel label1 = new JLabel(winner);
            JPanel panel1 = new JPanel();
            JButton button1 = new JButton();
            JButton button2 = new JButton();
            button1.setText("Click me to play again!");
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    HashMap<String,String > starting_board1= starting_positions();
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            String key = myarray[i][j].alge_notation;
                            String chess_piece = starting_board1.get(key);
                            myarray[i][j].setText(chess_piece);
                            myarray[i][j].piece_color = Square.piece_color_func(myarray[i][j].getText());
                            if (Objects.equals(myarray[i][j].piece_color, "White")) {
                                myarray[i][j].activateable = Boolean.TRUE;
                            } else if (Objects.equals(myarray[i][j].piece_color, "Black")) {
                                myarray[i][j].activateable = Boolean.FALSE;
                            }
                        }}
                    //myarray


               parent.dispose(); }
            }
            );
            button2.setText("Click me to quit!");
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            parent.add(label1);
            panel1.add(button1);
            panel1.add(button2);
            //panel1.add(label1);
            parent.add(panel1);
            parent.pack();

            parent.setVisible(true);

            //System.exit(0);
        }

        else if (counter==0 && val == Boolean.FALSE) {
            JFrame parent = new JFrame("Stalemate");
            parent.setSize(300,300);
            parent.setLayout(new FlowLayout(FlowLayout.CENTER));
            String winner = "Stalemate";
            JLabel label1 = new JLabel(winner);
            JPanel panel1 = new JPanel();
            JButton button1 = new JButton();
            JButton button2 = new JButton();
            button1.setText("Click me to play again!");
            button1.addActionListener(new ActionListener() {
                                          @Override
                                          public void actionPerformed(ActionEvent e) {

                                              HashMap<String,String > starting_board1= starting_positions();
                                              for (int i = 0; i < 8; i++) {
                                                  for (int j = 0; j < 8; j++) {
                                                      String key = myarray[i][j].alge_notation;
                                                      String chess_piece = starting_board1.get(key);
                                                      myarray[i][j].setText(chess_piece);
                                                      myarray[i][j].piece_color = Square.piece_color_func(myarray[i][j].getText());
                                                      if (Objects.equals(myarray[i][j].piece_color, "White")) {
                                                          myarray[i][j].activateable = Boolean.TRUE;
                                                      } else if (Objects.equals(myarray[i][j].piece_color, "Black")) {
                                                          myarray[i][j].activateable = Boolean.FALSE;
                                                      }
                                                  }}
                                              //myarray


                                              parent.dispose(); }
                                      }
            );
            button2.setText("Click me to quit!");
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            parent.add(label1);
            panel1.add(button1);
            panel1.add(button2);
            //panel1.add(label1);
            parent.add(panel1);
            parent.pack();

            parent.setVisible(true);

            //System.exit(0);
        }

        clean_board(myarray);

    }

    public static void clean_board(Square[][] myarray) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                myarray[i][j].setBackground(myarray[i][j].original_color);
                myarray[i][j].piece_color = piece_color_func(myarray[i][j].getText());
                myarray[i][j].activating_piece = null;
                myarray[i][j].activating_Square_ = null;
                if (myarray[i][j].text_activateable == Boolean.TRUE) {
                    myarray[i][j].text_activateable = null;
                    myarray[i][j].activateable = Boolean.FALSE;
                }

            }
        }

    }

    public static void switch_player(Square[][] myarray, Square piece) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                myarray[i][j].setBackground(myarray[i][j].original_color);
                myarray[i][j].piece_color = piece_color_func(myarray[i][j].getText());
                myarray[i][j].activating_piece = null;
                myarray[i][j].activating_Square_ = null;
                myarray[i][j].text_activateable = null;
                //Far too naive, why it isn't working
                if (Objects.equals(myarray[i][j].piece_color, piece.piece_color)) {
                    myarray[i][j].activateable = Boolean.FALSE;
                } else if (!Objects.equals(myarray[i][j].piece_color, piece.piece_color)) {
                    myarray[i][j].activateable = Boolean.TRUE;
                }

            }
        }

    }

    public void blank(Square[][] myarray) {
        if (this.getBackground() != Color.pink) {
            clean_board(myarray);
        } else {
            pink_square(myarray, this);
        }
    }

    public void activate_square_pink(Square[][] myarray, Integer i, Integer j, Integer orig_y, Integer orig_x, String piece) {
        myarray[i][j].setBackground(Color.pink);
        myarray[i][j].activating_piece = piece;
        myarray[i][j].activating_Square_ = myarray[orig_y][orig_x];
        //myarray[i][j].activateable = Boolean.TRUE;
        if (myarray[i][j].getText() != null) {
            myarray[i][j].text_activateable = Boolean.TRUE;
            myarray[i][j].activateable = Boolean.TRUE;
        }

        myarray[orig_y][orig_x].piece_color = piece_color_func(myarray[orig_y][orig_x].getText());
    }

    public void pawn(Square[][] myarray, String colour) {
        if (this.getBackground() != Color.pink) {
            if (this.activateable == null) {System.out.println("Equal to null");}
            clean_board(myarray);
            Integer orig_x = this.x;
            Integer orig_y = this.y;

            if (colour == "Black") {
                for (int i = this.y - 1; i >= this.y - 1; i = i - 1) {
                    if (i + 1 == 0) {
                        break;
                    }
                    if (this.x - 1 >= 0 && (Objects.equals(myarray[i][this.x - 1].piece_color, "White"))) {
                        activate_square_pink(myarray, i, this.x - 1, orig_y, orig_x, "\u265F ");
                    }

                    if (this.x + 1 < 8 && (myarray[i][this.x + 1].piece_color == "White")) {
                        activate_square_pink(myarray, i, this.x + 1, orig_y, orig_x, "\u265F ");
                    }
                }
                if (this.alge_notation.contains("7")) {
                    for (int i = this.y - 1; i >= this.y - 2; i = i - 1) {
                        if (myarray[i][this.x].piece_color == null) {
                            activate_square_pink(myarray, i, this.x, orig_y, orig_x, "\u265F ");
                        }
                        else {break;}
                    }
                } else {
                    for (int i = this.y - 1; i >= this.y - 1; i = i - 1) {
                        if (i + 1 == 0) {
                            break;
                        }
                        if (myarray[i][this.x].piece_color == null) {
                            activate_square_pink(myarray, i, this.x, orig_y, orig_x, "\u265F ");
                        }
                    }

                }
            } else if (colour == "White") {
                for (int i = this.y + 1; i <= this.y + 1; i = i + 1) {
                    if (i == 8) {
                        break;
                    }
                    if (this.x - 1 >= 0 && (myarray[i][this.x - 1].piece_color == "Black")) {
                        activate_square_pink(myarray, i, this.x - 1, orig_y, orig_x, "\u2659 ");
                    }
                }
                for (int i = this.y + 1; i <= this.y + 1; i = i + 1) {
                    if (i == 8) {
                        break;
                    }
                    if (this.x + 1 < 8 && (myarray[i][this.x + 1].piece_color == "Black")) {
                        activate_square_pink(myarray, i, this.x + 1, orig_y, orig_x, "\u2659 ");
                    }
                }
                if (this.alge_notation.contains("2")) {
                    for (int i = this.y + 1; i <= this.y + 2; i = i + 1) {
                        if (myarray[i][this.x].piece_color == null) {
                            activate_square_pink(myarray, i, this.x, orig_y, orig_x, "\u2659 ");
                        } else {
                            break;
                        }
                    }
                } else {
                    for (int i = this.y + 1; i <= this.y + 1; i = i + 1) {
                        if (i == 8) { // | myarray[i][this.x].piece_color == "White"
                            break;
                        }

                        if (myarray[i][this.x].piece_color == null) {
                            activate_square_pink(myarray, i, this.x, orig_y, orig_x, "\u2659 ");
                        }
                    }

                }
            }

            self_check_stopper(myarray, piece_color);

        } else {
            pink_square(myarray, this);
        }
    }

    public void bishop(Square[][] myarray, String colour, String opp_colour) {
        if (this.getBackground() != Color.pink) {
            clean_board(myarray);
            Integer orig_x = this.x;
            Integer orig_y = this.y;
            for (int i = 1; i <= 8; i = i + 1) {
                Integer less_than_8y = orig_y + i;
                Integer less_than_8x = orig_x + i;
                if (less_than_8y < 8 && less_than_8x < 8) {
                    if (myarray[orig_y + i][orig_x + i].piece_color != null) {
                        if (myarray[orig_y + i][orig_x + i].piece_color == opp_colour) {
                            activate_square_pink(myarray, orig_y + i, orig_x + i, orig_y, orig_x, colour);
                            break;
                        }
                        break;
                    }
                    activate_square_pink(myarray, orig_y + i, orig_x + i, orig_y, orig_x, colour);
                    myarray[i][this.x].activating_Square_ = myarray[orig_y][orig_x];

                }

            }
            for (int i = 1; i <= 8; i = i + 1) {
                Integer less_than_8x = orig_x + i;

                Integer more_than_0y = orig_y - i;

                if (less_than_8x < 8 && more_than_0y >= 0) {
                    if (myarray[orig_y - i][orig_x + i].piece_color != null) {
                        if (myarray[orig_y - i][orig_x + i].piece_color == opp_colour) {
                            activate_square_pink(myarray, orig_y - i, orig_x + i, orig_y, orig_x, colour);
                            break;
                        }
                        break;
                    }
                    activate_square_pink(myarray, orig_y - i, orig_x + i, orig_y, orig_x, colour);

                }

            }
            for (int i = 1; i <= 8; i = i + 1) {

                Integer more_than_0x = orig_x - i;
                Integer more_than_0y = orig_y - i;

                if (more_than_0x >= 0 && more_than_0y >= 0) {

                    if (myarray[orig_y - i][orig_x - i].piece_color != null) {

                        if (myarray[orig_y - i][orig_x - i].piece_color == opp_colour) {
                            activate_square_pink(myarray, orig_y - i, orig_x - i, orig_y, orig_x, colour);
                        }
                        break;
                    }
                    activate_square_pink(myarray, orig_y - i, orig_x - i, orig_y, orig_x, colour);
                }
            }
            for (int i = 1; i <= 8; i = i + 1) {
                Integer less_than_8y = orig_y + i;
                Integer more_than_0x = orig_x - i;
                if (less_than_8y < 8 && more_than_0x >= 0) {
                    if (myarray[orig_y + i][orig_x - i].piece_color != null) {
                        if (myarray[orig_y + i][orig_x - i].piece_color == opp_colour) {
                            activate_square_pink(myarray, orig_y + i, orig_x - i, orig_y, orig_x, colour);
                        }
                        break;
                    }
                    activate_square_pink(myarray, orig_y + i, orig_x - i, orig_y, orig_x, colour);
                }

            }
            self_check_stopper(myarray, piece_color);

        } else {
            pink_square(myarray, this);
        }

    }

    public void king(Square[][] myarray, String Colour, String opp_colour) {
        clean_board(myarray);
        Integer orig_x = this.x;
        Integer orig_y = this.y;
        Integer less_than_8y = orig_y + 1;
        Integer less_than_8x = orig_x + 1;
        Integer more_than_0x = orig_x - 1;
        Integer more_than_0y = orig_y - 1;
        if (less_than_8y < 8 && less_than_8x < 8) {
            if (myarray[orig_y + 1][orig_x + 1].piece_color != opp_colour) {
                activate_square_pink(myarray, orig_y + 1, orig_x + 1, orig_y, orig_x, Colour);
            }
        }


        if (less_than_8x < 8 && more_than_0y >= 0) {
            if (myarray[orig_y - 1][orig_x + 1].piece_color != opp_colour) {
                activate_square_pink(myarray, orig_y - 1, orig_x + 1, orig_y, orig_x, Colour);
            }
        }

        if (more_than_0x >= 0 && more_than_0y >= 0) {

            if (myarray[orig_y - 1][orig_x - 1].piece_color != opp_colour) {
                activate_square_pink(myarray, orig_y - 1, orig_x - 1, orig_y, orig_x, Colour);
            }

        }

        if (less_than_8y < 8 && more_than_0x >= 0) {
            if (myarray[orig_y + 1][orig_x - 1].piece_color != opp_colour) {
                activate_square_pink(myarray, orig_y + 1, orig_x - 1, orig_y, orig_x, Colour);
            }
        }

        if (less_than_8y < 8) {
            if (myarray[orig_y + 1][orig_x].piece_color != opp_colour) {
                activate_square_pink(myarray, orig_y + 1, orig_x, orig_y, orig_x, Colour);
            }

        }

        if (less_than_8x < 8) {
            if (myarray[orig_y][orig_x + 1].piece_color != opp_colour) {
                activate_square_pink(myarray, orig_y, orig_x + 1, orig_y, orig_x, Colour);
            }

        }
        if (more_than_0x >= 0) {
            if (myarray[orig_y][orig_x - 1].piece_color != opp_colour) {
                activate_square_pink(myarray, orig_y, orig_x - 1, orig_y, orig_x, Colour);
            }
        }

        if (more_than_0y >= 0) {
            if (myarray[orig_y - 1][orig_x].piece_color != opp_colour) {
                activate_square_pink(myarray, orig_y - 1, orig_x, orig_y, orig_x, Colour);
            }

        }
        self_check_stopper(myarray, piece_color);


    }

    public void rook(Square[][] myarray, String colour, String opp_colour) {
        if (this.getBackground() != Color.pink) {
            clean_board(myarray);
            Integer orig_x = this.x;
            Integer orig_y = this.y;
            for (int i = 1; i <= 8; i = i + 1) {
                Integer less_than_8y = orig_y + i;
                if (less_than_8y < 8) {
                    if (myarray[orig_y + i][orig_x].piece_color != null) {
                        if (myarray[orig_y + i][orig_x].piece_color == opp_colour) {
                            activate_square_pink(myarray, orig_y + i, orig_x, orig_y, orig_x, colour);
                            break;
                        }
                        break;
                    }
                    activate_square_pink(myarray, orig_y + i, orig_x, orig_y, orig_x, colour);
                }
            }
            for (int i = 1; i <= 8; i = i + 1) {
                Integer less_than_8x = orig_x + i;
                if (less_than_8x < 8) {
                    if (myarray[orig_y][orig_x + i].piece_color != null) {
                        if (myarray[orig_y][orig_x + i].piece_color == opp_colour) {
                            activate_square_pink(myarray, orig_y, orig_x + i, orig_y, orig_x, colour);
                            break;
                        }
                        break;
                    }
                    activate_square_pink(myarray, orig_y, orig_x + i, orig_y, orig_x, colour);
                }
            }
            for (int i = 1; i <= 8; i = i + 1) {
                Integer more_than_0x = orig_x - i;
                if (more_than_0x >= 0) {
                    if (myarray[orig_y][orig_x - i].piece_color != null) {
                        if (myarray[orig_y][orig_x - i].piece_color == opp_colour) {
                            activate_square_pink(myarray, orig_y, orig_x - i, orig_y, orig_x, colour);
                        }
                        break;
                    }
                    activate_square_pink(myarray, orig_y, orig_x - i, orig_y, orig_x, colour);
                }
            }
            for (int i = 1; i <= 8; i = i + 1) {
                Integer more_than_0y = orig_y - i;

                if (more_than_0y >= 0) {
                    if (myarray[orig_y - i][orig_x].piece_color != null) {
                        if (myarray[orig_y - i][orig_x].piece_color == opp_colour) {
                            activate_square_pink(myarray, orig_y - i, orig_x, orig_y, orig_x, colour);
                            break;
                        }
                        break;
                    }
                    activate_square_pink(myarray, orig_y - i, orig_x, orig_y, orig_x, colour);
                }
            }
            self_check_stopper(myarray, piece_color);

//
        } else {
            pink_square(myarray, this);
        }

    }

    public void knight(Square[][] myarray, String colour, String opp_colour) {
        if (this.getBackground() != Color.pink) {
            clean_board(myarray);
            Integer orig_x = this.x;
            Integer orig_y = this.y;
            if (orig_y + 2 <= 7 && orig_x + 1 <= 7 && myarray[orig_y + 2][orig_x + 1].piece_color != opp_colour) {
                activate_square_pink(myarray, orig_y + 2, orig_x + 1, orig_y, orig_x, colour);
            }
            if (orig_y + 2 <= 7 && orig_x - 1 >= 0 && myarray[orig_y + 2][orig_x - 1].piece_color != opp_colour) {
                activate_square_pink(myarray, orig_y + 2, orig_x - 1, orig_y, orig_x, colour);
            }
            if (orig_y - 2 >= 0 && orig_x + 1 <= 7 && myarray[orig_y - 2][orig_x + 1].piece_color != opp_colour) {
                activate_square_pink(myarray, orig_y - 2, orig_x + 1, orig_y, orig_x, colour);
            }
            if (orig_y - 2 >= 0 && orig_x - 1 >= 0 && myarray[orig_y - 2][orig_x - 1].piece_color != opp_colour) {
                activate_square_pink(myarray, orig_y - 2, orig_x - 1, orig_y, orig_x, colour);
            }
            if (orig_y + 1 <= 7 && orig_x + 2 <= 7 && myarray[orig_y + 1][orig_x + 2].piece_color != opp_colour) {
                activate_square_pink(myarray, orig_y + 1, orig_x + 2, orig_y, orig_x, colour);
            }
            if (orig_y - 1 >= 0 && orig_x + 2 <= 7 && myarray[orig_y - 1][orig_x + 2].piece_color != opp_colour) {
                activate_square_pink(myarray, orig_y - 1, orig_x + 2, orig_y, orig_x, colour);
            }
            if (orig_y + 1 <= 7 && orig_x - 2 >= 0 && myarray[orig_y + 1][orig_x - 2].piece_color != opp_colour) {
                activate_square_pink(myarray, orig_y + 1, orig_x - 2, orig_y, orig_x, colour);
            }
            if (orig_y - 1 >= 0 && orig_x - 2 >= 0 && myarray[orig_y - 1][orig_x - 2].piece_color != opp_colour) {
                activate_square_pink(myarray, orig_y - 1, orig_x - 2, orig_y, orig_x, colour);
            }
            self_check_stopper(myarray, piece_color);

        } else {
            pink_square(myarray, this);
        }

    }

    public void queen(Square[][] myarray, String colour, String opp_colour) {
        if (this.getBackground() != Color.pink) {
            clean_board(myarray);
            Integer orig_x = this.x;
            Integer orig_y = this.y;
            for (int i = 1; i <= 8; i = i + 1) {
                Integer less_than_8y = orig_y + i;
                Integer less_than_8x = orig_x + i;
                if (less_than_8y < 8 && less_than_8x < 8) {
                    if (myarray[orig_y + i][orig_x + i].piece_color != null) {
                        if (myarray[orig_y + i][orig_x + i].piece_color == opp_colour) {
                            activate_square_pink(myarray, orig_y + i, orig_x + i, orig_y, orig_x, colour);
                            break;
                        }
                        break;
                    }
                    activate_square_pink(myarray, orig_y + i, orig_x + i, orig_y, orig_x, colour);
                    myarray[i][this.x].activating_Square_ = myarray[orig_y][orig_x];

                }

            }
            for (int i = 1; i <= 8; i = i + 1) {
                Integer less_than_8x = orig_x + i;
                Integer more_than_0y = orig_y - i;

                if (less_than_8x < 8 && more_than_0y >= 0) {
                    if (myarray[orig_y - i][orig_x + i].piece_color != null) {
                        if (myarray[orig_y - i][orig_x + i].piece_color == opp_colour) {
                            activate_square_pink(myarray, orig_y - i, orig_x + i, orig_y, orig_x, colour);
                            break;
                        }
                        break;
                    }
                    activate_square_pink(myarray, orig_y - i, orig_x + i, orig_y, orig_x, colour);

                }

            }
            for (int i = 1; i <= 8; i = i + 1) {

                Integer more_than_0x = orig_x - i;
                Integer more_than_0y = orig_y - i;

                if (more_than_0x >= 0 && more_than_0y >= 0) {

                    if (myarray[orig_y - i][orig_x - i].piece_color != null) {
                        if (myarray[orig_y - i][orig_x - i].piece_color == opp_colour) {
                            activate_square_pink(myarray, orig_y - i, orig_x - i, orig_y, orig_x, colour);
                            //break;
                        }
                        break;
                    }
                    activate_square_pink(myarray, orig_y - i, orig_x - i, orig_y, orig_x, colour);
                }

            }
            for (int i = 1; i <= 8; i = i + 1) {
                Integer less_than_8y = orig_y + i;
                Integer more_than_0x = orig_x - i;
                if (less_than_8y < 8 && more_than_0x >= 0) {
                    if (myarray[orig_y + i][orig_x - i].piece_color != null) {
                        if (myarray[orig_y + i][orig_x - i].piece_color == opp_colour) {
                            activate_square_pink(myarray, orig_y + i, orig_x - i, orig_y, orig_x, colour);
                        }
                        break;
                    }
                    activate_square_pink(myarray, orig_y + i, orig_x - i, orig_y, orig_x, colour);
                }

            }

            for (int i = 1; i <= 8; i = i + 1) {
                Integer less_than_8y = orig_y + i;
                if (less_than_8y < 8) {
                    if (myarray[orig_y + i][orig_x].piece_color != null) {
                        if (myarray[orig_y + i][orig_x].piece_color == opp_colour) {
                            activate_square_pink(myarray, orig_y + i, orig_x, orig_y, orig_x, colour);
                            break;
                        }
                        break;
                    }
                    activate_square_pink(myarray, orig_y + i, orig_x, orig_y, orig_x, colour);
                }
            }
            for (int i = 1; i <= 8; i = i + 1) {
                Integer less_than_8x = orig_x + i;
                if (less_than_8x < 8) {
                    if (myarray[orig_y][orig_x + i].piece_color != null) {
                        if (myarray[orig_y][orig_x + i].piece_color == opp_colour) {
                            activate_square_pink(myarray, orig_y, orig_x + i, orig_y, orig_x, colour);
                            break;
                        }
                        break;
                    }
                    activate_square_pink(myarray, orig_y, orig_x + i, orig_y, orig_x, colour);
                }
            }
            for (int i = 1; i <= 8; i = i + 1) {
                Integer more_than_0x = orig_x - i;
                if (more_than_0x >= 0) {
                    if (myarray[orig_y][orig_x - i].piece_color != null) {
                        if (myarray[orig_y][orig_x - i].piece_color == opp_colour) {
                            activate_square_pink(myarray, orig_y, orig_x - i, orig_y, orig_x, colour);
                        }
                        break;
                    }
                    activate_square_pink(myarray, orig_y, orig_x - i, orig_y, orig_x, colour);
                }
            }
            for (int i = 1; i <= 8; i = i + 1) {

                Integer more_than_0y = orig_y - i;

                if (more_than_0y >= 0) {

                    if (myarray[orig_y - i][orig_x].piece_color != null) {
                        if (myarray[orig_y - i][orig_x].piece_color == opp_colour) {
                            activate_square_pink(myarray, orig_y - i, orig_x, orig_y, orig_x, colour);
                            break;
                        }
                        break;
                    }
                    activate_square_pink(myarray, orig_y - i, orig_x, orig_y, orig_x, colour);
                }
            }
            self_check_stopper(myarray, piece_color);

        } else {
            pink_square(myarray, this);
        }


    }

    public void outp(Square[][] myarray, Square piece) {
        if (piece.getText() == null) {
            piece.blank(myarray);
        } else if (Objects.equals(piece.getText(), "\u2659 ") & piece.activateable == Boolean.TRUE) {
            piece.pawn(myarray, "White");
        } else if (Objects.equals(piece.getText(), "\u2657 ") & piece.activateable == Boolean.TRUE) {
            piece.bishop(myarray, "\u2657 ", "Black");
        } else if (Objects.equals(piece.getText(), "\u2656 ") & piece.activateable == Boolean.TRUE) {
            piece.rook(myarray, "\u2656 ", "Black");
        } else if (Objects.equals(piece.getText(), "\u2658 ") & piece.activateable == Boolean.TRUE) {
            piece.knight(myarray, "\u2658 ", "White");
        } else if (Objects.equals(piece.getText(), "\u2655 ") & piece.activateable == Boolean.TRUE) {//White
            piece.queen(myarray, "\u2655 ", "Black");
        } else if (Objects.equals(piece.getText(), "\u2654 ") & piece.activateable == Boolean.TRUE) {
            piece.king(myarray, "\u2654 ", "White");
        } else if (Objects.equals(piece.getText(), "\u265F ") & piece.activateable == Boolean.TRUE) {
            piece.pawn(myarray, "Black");
        } else if (Objects.equals(piece.getText(), "\u265C ") & piece.activateable == Boolean.TRUE) {
            piece.rook(myarray, "\u265C ", "White");
        } else if (Objects.equals(piece.getText(), "\u265E ") & piece.activateable == Boolean.TRUE) {
            piece.knight(myarray, "\u265E ", "Black");
        } else if (Objects.equals(piece.getText(), "\u265D ") & piece.activateable == Boolean.TRUE) {
            piece.bishop(myarray, "\u265D ", "White");
        } else if (Objects.equals(piece.getText(), "\u265B ") & piece.activateable == Boolean.TRUE) {
            piece.queen(myarray, "\u265B ", "White");
        } else if (Objects.equals(piece.getText(), "\u265A ") & piece.activateable == Boolean.TRUE) {
            piece.king(myarray, "\u265A ", "Black");
        }

    }

    public Boolean virtual_board_check(Square[][] myarray) {
        VirtualSquare[][] virt_array = new VirtualSquare[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square real_square = myarray[i][j];
                VirtualSquare virt_square = new VirtualSquare(real_square.y, real_square.x,
                        real_square.alge_notation, real_square.activating_piece, real_square.original_color,
                        real_square.getText(), real_square.getBackground());
                virt_array[i][j] = virt_square;
            }
        }
        Boolean val = VirtualSquare.check_loop(virt_array);
        if (val==Boolean.TRUE) {
            return Boolean.TRUE;
        }
        else {return Boolean.FALSE;}


    }

    public void self_check_stopper(Square[][] myarray, String color) {
        VirtualSquare[][] virt_array = new VirtualSquare[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square real_square = myarray[i][j];
                VirtualSquare virt_square = new VirtualSquare(real_square.y, real_square.x,
                        real_square.alge_notation, real_square.activating_piece, real_square.original_color,
                        real_square.getText(), real_square.getBackground());
                virt_array[i][j] = virt_square;
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                VirtualSquare virt_square2 = virt_array[i][j];
                if (virt_square2.Background == Color.PINK) {
                    VirtualSquare[][] copy_virtualarray = new VirtualSquare[8][8];
                    for (int i1 = 0; i1 < 8; i1++) {
                        for (int j1 = 0; j1 < 8; j1++) {
                            VirtualSquare copy_vs = new VirtualSquare(virt_array[i1][j1].y,virt_array[i1][j1].x,virt_array[i1][j1].alge_notation,
                                    virt_array[i1][j1].activating_piece,virt_array[i1][j1].original_color,virt_array[i1][j1].Text,virt_array[i1][j1].Background);
                            copy_virtualarray[i1][j1]=copy_vs;

                        }
                    }
                    Integer acti_x = myarray[i][j].activating_Square_.x;
                    Integer acti_y = myarray[i][j].activating_Square_.y;
                    copy_virtualarray[i][j].activating_Square_= copy_virtualarray[acti_y][acti_x];
                    VirtualSquare.outp(copy_virtualarray,copy_virtualarray[i][j]);
                    VirtualSquare.update_board(copy_virtualarray);
                    //VirtualSquare.self_check_loop1(copy_virtualarray);
                    for (int i1 = 0; i1 < 8; i1++) {
                        for (int j1 = 0; j1 < 8; j1++) {
                            VirtualSquare mysquare = copy_virtualarray[i1][j1];
                            wipe_pink(myarray,VirtualSquare.selfcheck(copy_virtualarray,mysquare, color),i,j);
                        }
                    }
                }
            }
        }
    }

    public void wipe_pink(Square[][] myarray,Integer[] vals, Integer i, Integer j) {
        Integer[] check_array = {-1,-1};
        //System.out.println(vals[0]);
        if (vals[0] != -1) {
            System.out.println("Black is in self check here");
            Integer p = vals[0];
            Integer q = vals[1];
            Square acti_square = myarray[i][j].activating_Square_;
            myarray[i][j].setBackground(myarray[i][j].original_color);
            myarray[i][j].setBackground(myarray[i][j].original_color);
            if (acti_square != null) {
            acti_square.setBackground(Color.CYAN);}
            myarray[i][j].activating_piece = null;
            myarray[i][j].activating_Square_ = null;
            myarray[i][j].piece_color = piece_color_func(myarray[i][j].getText());
            //myarray[i][j].text_activateable=Boolean.TRUE;
            //switch_player(myarray);
            //acti_square.activateable=null;
            //acti_square.text_activateable=null;


        }


            //special_update_board(myarray);

        }




    public void virtual_board_creation(Square[][] myarray) {
        VirtualSquare[][] virt_array = new VirtualSquare[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square real_square = myarray[i][j];
                VirtualSquare virt_square = new VirtualSquare(real_square.y, real_square.x,
                        real_square.alge_notation, real_square.activating_piece, real_square.original_color,
                        real_square.getText(), real_square.getBackground());
                virt_array[i][j] = virt_square;
            }
        }
    }
}





