package GUI;

import com.FileManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedHashMap;

/**
 *A class which represents the highscore menu window
 */
public class HighscoreMenu {
    JFrame frame;
    JPanel header;
    JLabel highscoreTitle;
    LinkedHashMap<String, Integer> highscores;
    FileManager fileManager;
    JScrollPane highscorePane;
    JTable highscoreTable;

    /**
     * Default constructor of the HighscoreMenu class, which creates the window and fills it up with relevant information
     * @throws IOException
     */
    public HighscoreMenu() throws IOException {
        fileManager = new FileManager();
        setUpFrame();
        setUpHeader();
        setUpHighscoreList();
    }

    /**
     * Sets up the main frame for the HighscoreMenu window
     */
    private void setUpFrame() {
        frame = new JFrame();
        frame.setTitle("Buzz! Highscores");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(720, 820);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.darkGray);
        frame.setVisible(true);
    }

    /**
     * Sets up the header for the Highscore Menu window
     */
    private void setUpHeader() {
        header = new JPanel();
        header.setLayout(new BorderLayout());
        header.setBackground(Color.darkGray);
        highscoreTitle = new JLabel();
        highscoreTitle.setText("Highscores:");
        highscoreTitle.setForeground(Color.white);
        highscoreTitle.setHorizontalAlignment(JLabel.CENTER);
        highscoreTitle.setFont(new Font("Arial Black", Font.BOLD, 24));
        header.add(highscoreTitle, BorderLayout.CENTER);

        frame.add(header, BorderLayout.NORTH);

    }

    /**
     * Sets up a JTable containing all of the highscores currently saved in the <code>highscores.txt</code> file
     * and then displays the JTable
     */
    private void setUpHighscoreList() {
        highscores = fileManager.getHighscoresFromFile();

        String[][] tableData = new String[highscores.size()][2];
        int index = 0;
        for(String i: highscores.keySet()) {
            tableData[index][0] = i;
            tableData[index][1] = highscores.get(i).toString();
            index++;
        }

        String[] columns = {"Player", "Score"};
        highscoreTable = new JTable(tableData, columns) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        highscoreTable.setFocusable(false);
        highscoreTable.setCellSelectionEnabled(false);
        highscorePane = new JScrollPane(highscoreTable);

        frame.add(highscorePane, BorderLayout.CENTER);
        frame.pack();

    }


}
