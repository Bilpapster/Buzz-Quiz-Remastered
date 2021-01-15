package GUI;

import com.FileManagers.FileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

/**
 *A class which represents the highscore menu window
 */
public class HighscoreMenu implements ActionListener {
    JPanel rootPanel;
    JPanel header;
    JLabel highscoreTitle;
    JButton sortingBtn;
    LinkedHashMap<String, Integer> highscores;
    FileManager fileManager;
    JScrollPane highscorePane;
    JTable highscoreTable;
    String[][] tableData;
    String sorting = "descending";
    final String[] columns = {"Player", "Score"};


    /**
     * Default constructor of the HighscoreMenu class, which creates the window and fills it up with relevant information
     */
    public HighscoreMenu(JFrame parentFrame) {
        try {
            fileManager = new FileManager("highscores.txt", "questions.txt");
        } catch(IOException e) {
            e.printStackTrace();
        }
        setUpFrame();
        setUpHeader();
        setUpHighscoreList();

        JDialog frame = new JDialog(parentFrame, "Buzz!: Highscores", true);
        frame.getContentPane().add(rootPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Sets up the main frame for the HighscoreMenu window
     */
    private void setUpFrame() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new BorderLayout());
        rootPanel.setBackground(Color.darkGray);
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

        rootPanel.add(header, BorderLayout.NORTH);
    }

    private void setUpSortingBtn() {
        sortingBtn = new JButton();
        sortingBtn.setText("\uD83E\uDC6B");
        sortingBtn.addActionListener(this);
        sortingBtn.setToolTipText("Sorting");
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

        rootPanel.add(highscorePane, BorderLayout.CENTER);
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
