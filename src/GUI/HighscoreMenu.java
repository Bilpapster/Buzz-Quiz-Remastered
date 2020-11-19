package GUI;

import com.FileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

/**
 *A class which represents the highscore menu window
 */
public class HighscoreMenu implements ActionListener {
    JFrame frame;
    JPanel header;
    JLabel highscoreTitle;
    JButton sortingBtn;
    LinkedHashMap<String, Integer> highscores;
    FileManager fileManager;
    JScrollPane highscorePane;
    JTable highscoreTable;
    String[][] tableData;
    String sorting = "descending";
    String[] columns = {"Player", "Score"};


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
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.darkGray);
        frame.setVisible(true);
        frame.pack();
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
        setUpSortingBtn();

        frame.add(header, BorderLayout.NORTH);

    }

    private void setUpSortingBtn() {
        sortingBtn = new JButton();
        sortingBtn.setText("\uD83E\uDC6B");
        sortingBtn.addActionListener(this::actionPerformed);
        header.add(sortingBtn, BorderLayout.WEST);
    }

    /**
     * Sets up a JTable containing all of the highscores currently saved in the <code>highscores.txt</code> file
     * and then displays the JTable
     */
    private void setUpHighscoreList() {
        highscores = fileManager.getHighscoresFromFile();

        tableData = new String[highscores.size()][2];
        int index = 0;
        for(String i: highscores.keySet()) {
            tableData[index][0] = i;
            tableData[index][1] = highscores.get(i).toString();
            index++;
        }

        highscoreTable = new JTable(tableData, columns) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        highscoreTable.setFocusable(false);
        highscoreTable.setCellSelectionEnabled(false);
        highscoreTable.getTableHeader().setReorderingAllowed(false);
        highscoreTable.setFillsViewportHeight(true);
        highscoreTable.setDragEnabled(false);
        highscorePane = new JScrollPane(highscoreTable);

        frame.add(highscorePane, BorderLayout.CENTER);
        frame.pack();

    }

    private void changeSorting() {
        if (sorting.equals("descending")) {
            sortingBtn.setText("\uD83E\uDC61");
            sorting = "ascending";
            Arrays.sort(tableData, Comparator.comparingInt(o -> Integer.parseInt(o[1])));
        } else {
            sortingBtn.setText("\uD83E\uDC6B");
            sorting = "descending";
            Arrays.sort(tableData, (a,b) -> Integer.parseInt(b[1])- Integer.parseInt(a[1]));
        }
        for(int i = 0; i < tableData.length; i++) {
            highscoreTable.getModel().setValueAt(tableData[i][0], i, 0);
            highscoreTable.getModel().setValueAt(tableData[i][1], i, 1);
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sortingBtn)
            changeSorting();
    }
}
