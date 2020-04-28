package dictionary;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.List;

import static java.awt.event.KeyEvent.*;

public class ProgFrame extends JFrame {

    JButton b_randpl, b_randen;
    JButton b_trypl, b_hintpl, b_tryen, b_hinten;
    JLabel l_randpl_word, l_randpl_result, l_randen_word, l_randen_result, l_hintpl, l_hinten;
    JLabel l_add, l_rm;
    HintTextField t_randpl, t_randen;

    JButton b_restart, b_add, b_rm;
    HintTextField t_add1,  t_add2, t_rm;

    Dict basic = new Dict();

    String tmp_random_word, tmp_answer, tmp_result, tmp_hint;
    int tmp_id, use_hint=-1;

    JTable table;
    HintTextField t_tabpl, t_taben;
    JLabel l_tab;
    JButton b_tab, b_tab_all;

    JToggleButton toggleButton;

    JScrollPane jScrollPane1 = new JScrollPane();

    public ProgFrame() {
        basic.connect_hib();

        setSize(900, 600);
        setTitle("lang app");
        setLayout(null);

        //searchInMainDictionary("arsenic", "en","");
        //rand PL

        b_randpl = new JButton("Polish -> English (F1)");
        b_randpl.setBounds(20, 50, 180, 25);
        add(b_randpl);

        l_randpl_word = new JLabel("");
        l_randpl_word.setBounds(210, 50, 100, 25);
        add(l_randpl_word);

        l_randpl_result = new JLabel("");
        l_randpl_result.setBounds(600, 50, 110, 25);
        add(l_randpl_result);

        t_randpl = new HintTextField("");
        t_randpl.setPlaceholder("Write the answer");
        t_randpl.setBounds(330, 50, 200, 25);
        add(t_randpl);

        b_trypl = new JButton("Try");
        b_trypl.setBounds(535, 50, 60, 25);
        add(b_trypl);

        b_hintpl = new JButton("Hint");
        b_hintpl.setBounds(330, 20, 60, 25);
        add(b_hintpl);

        l_hintpl = new JLabel("");
        l_hintpl.setBounds(395, 20, 100, 25);
        add(l_hintpl);

        //rand EN

        b_randen = new JButton("English -> Polish (F2)");
        b_randen.setBounds(20, 140, 180, 25);
        add(b_randen);

        l_randen_word = new JLabel("");
        l_randen_word.setBounds(210, 140, 100, 25);
        add(l_randen_word);

        l_randen_result = new JLabel("");
        l_randen_result.setBounds(600, 140, 110, 25);
        add(l_randen_result);

        t_randen = new HintTextField("");
        t_randen.setPlaceholder("Write the answer");
        t_randen.setBounds(330, 140, 200, 25);
        add(t_randen);

        b_tryen = new JButton("Try");
        b_tryen.setBounds(535, 140, 60, 25);
        add(b_tryen);

        b_hinten = new JButton("Hint");
        b_hinten.setBounds(330, 110, 60, 25);
        add(b_hinten);

        // LABELS
        
        l_hinten = new JLabel("");
        l_hinten.setBounds(395, 110, 100, 25);
        add(l_hinten);

        l_add = new JLabel("");
        l_add.setBounds(510, 210, 200, 25);
        add(l_add);

        l_rm = new JLabel("");
        l_rm.setBounds(340, 250, 200, 25);
        add(l_rm);

        //restart button
        b_restart = new JButton("Restart");
        b_restart.setBounds(805, 5, 80, 25);
        add(b_restart);

        //add button

        b_add = new JButton("Add");
        b_add.setBounds(440, 210, 60, 25);
        add(b_add);

        t_add1 = new HintTextField("");
        t_add1.setPlaceholder("Type the word in PL");
        t_add1.setBounds(20, 210, 200, 25);
        add(t_add1);

        t_add2 = new HintTextField("");
        t_add2.setPlaceholder("Type the word in EN");
        t_add2.setBounds(230, 210, 200, 25);
        add(t_add2);

        //rm button

        b_rm = new JButton("Remove");
        b_rm.setBounds(230, 250, 100, 25);
        add(b_rm);

        t_rm = new HintTextField("");
        t_rm.setPlaceholder("Type id");
        t_rm.setBounds(20, 250, 200, 25);
        add(t_rm);

        // table fields

        t_tabpl = new HintTextField("");
        t_taben = new HintTextField("");
        t_tabpl.setPlaceholder("Type the word in PL");
        t_taben.setPlaceholder("Type the word in EN");
        t_tabpl.setBounds(20,320, 200, 25);
        t_taben.setBounds(230,320, 200, 25);
        add(t_tabpl);add(t_taben);

        b_tab = new JButton("Search");
        b_tab.setBounds(440, 320, 80, 25 );
        add(b_tab);

        l_tab = new JLabel("");
        l_tab.setBounds(20, 340, 200, 25);
        add(l_tab);

        b_tab_all = new JButton("Show All");
        b_tab_all.setBounds(530, 320, 100, 25);
        add(b_tab_all);

        // toggle change dict

        toggleButton = new JToggleButton("Basic dict");
        toggleButton.setBounds(640, 320, 100, 25);
        add(toggleButton);

        // button rand pl ACTION_PERFORMED

        b_randpl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                randWord("pl");
            }
        });

        b_hintpl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tmp_hint == null) {
                    tmp_hint = basic.loadHint(tmp_answer);
                } else {
                    tmp_hint = basic.loadHint(tmp_answer, tmp_hint);
                }
                l_hintpl.setText(tmp_hint.replace("", " "));
                use_hint = 0;
            }
        });

        b_trypl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tryPL();
            }
        });

        // button rand en

        b_randen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                randWord("en");
            }
        });

        b_hinten.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tmp_hint == null) {
                    tmp_hint = basic.loadHint(tmp_answer);
                } else {
                    tmp_hint = basic.loadHint(tmp_answer, tmp_hint);
                }
                l_hinten.setText(tmp_hint.replace("", " "));
                use_hint = 0;
            }
        });

        b_tryen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tryEN();
            }
        });

        // buttons restart add rm
        b_restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tmp_random_word=null; tmp_answer=null; tmp_result=null; tmp_hint=null;
                tmp_id=0; use_hint=-1;
                b_trypl.setEnabled(true); b_hintpl.setEnabled(true); b_randpl.setEnabled(true); t_randpl.setEnabled(true);
                b_tryen.setEnabled(true); b_hinten.setEnabled(true); b_randen.setEnabled(true); t_randen.setEnabled(true);
                l_randen_result.setText(""); l_randpl_result.setText(""); l_hinten.setText(""); l_hintpl.setText(""); l_randen_word.setText(""); l_randpl_word.setText("");
                t_randpl.setText(""); t_randen.setText("");
            }
        });

        b_add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTranslation();
            }
        });


        b_rm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rmTranslation();
            }
        });

        b_tab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tabSearch();
            }
        });

        b_tab_all.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAllTranslations();
            }
        });

        toggleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(toggleButton.isSelected()){
                    toggleButton.setForeground(Color.RED);
                    toggleButton.setText("Main dict");
                    b_tab_all.setEnabled(false);
                }else{
                    toggleButton.setForeground(Color.BLACK);
                    toggleButton.setText("Basic dict");
                    b_tab_all.setEnabled(true);
                }
            }
        });


        /* HOTKEYS */
        // try pl en Enter

        t_randpl.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(VK_ENTER, 0), "Enter");
        Action action_try1 = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                tryPL();
            }
        };
        t_randpl.getActionMap().put("Enter", action_try1);

        t_randen.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(VK_ENTER, 0), "Enter");
        Action action_try2 = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                tryEN();
            }
        };
        t_randen.getActionMap().put("Enter", action_try2);

        // add

        t_add2.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(VK_ENTER, 0), "Enter");
        Action action = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                addTranslation();

            }
        };
        t_add2.getActionMap().put("Enter", action);

        t_add1.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(VK_ENTER, 0), "Enter");
        Action action1 = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                t_add2.requestFocus();
            }
        };
        t_add1.getActionMap().put("Enter", action1);

        // remove

        t_rm.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(VK_ENTER, 0), "Enter");
        Action action_rm = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                rmTranslation();
            }
        };
        t_rm.getActionMap().put("Enter", action_rm);

        // rand pl -> en, en -> pl

        b_randpl.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(VK_F1, 0), "rand");
        Action action_randpl = new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                randWord("pl");
            }
        };
        b_randpl.getActionMap().put("rand", action_randpl);

        b_randen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(VK_F2, 0), "rand");
        Action action_randen = new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                randWord("en");
            }
        };
        b_randen.getActionMap().put("rand", action_randen);

        t_tabpl.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(VK_ENTER, 0), "Enter");
        Action actionTab = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                t_taben.requestFocus();
            }
        };
        t_tabpl.getActionMap().put("Enter", actionTab);

        t_taben.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(VK_ENTER, 0), "Enter");
        Action actionTabB = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                tabSearch();
            }
        };
        t_taben.getActionMap().put("Enter", actionTabB);



    }

    /* FUNCTIONS *//* FUNCTIONS *//* FUNCTIONS *//* FUNCTIONS */
    /* FUNCTIONS *//* FUNCTIONS *//* FUNCTIONS *//* FUNCTIONS */

    @SuppressWarnings("Duplicates")
    private void randWord(String lang){
        String[] parts = basic.loadRandomWord(lang).split(":");
        tmp_id = Integer.parseInt(parts[0]);
        tmp_random_word = parts[1];
        tmp_answer = parts[2];
        l_hintpl.setText("");
        l_randpl_result.setText("");
        tmp_hint = null;
        use_hint = -1;

        if(lang.equals("pl")) {
            l_hintpl.setText("");
            l_randpl_word.setText(tmp_random_word);
            b_trypl.setEnabled(true);
            b_tryen.setEnabled(false);
            b_hinten.setEnabled(false);
            b_randen.setEnabled(false);
            t_randen.setEnabled(false);
        }else{
            l_hinten.setText("");
            l_randen_word.setText(tmp_random_word);
            b_tryen.setEnabled(true);
            b_trypl.setEnabled(false);
            b_hintpl.setEnabled(false);
            b_randpl.setEnabled(false);
            t_randpl.setEnabled(false);
        }
    }

    @SuppressWarnings("Duplicates")
    private void tryPL(){
        if (t_randpl.getText().equals(tmp_answer)) {
            l_randpl_result.setText("Correct!");
            basic.correctAnswer(tmp_id, use_hint);
            use_hint = 0;
            b_trypl.setEnabled(false);
            b_tryen.setEnabled(true);
            b_hinten.setEnabled(true);
            b_randen.setEnabled(true);
            t_randen.setEnabled(true);
        } else {
            if (basic.wrongAnswer(tmp_id, t_randpl.getText(), "pl")) {
                l_randpl_result.setText("Yep, but I need another meaning.");
            } else {
                l_randpl_result.setText("Nope!");
            }

        }
    }

    @SuppressWarnings("Duplicates")
    private void tryEN(){
        if (t_randen.getText().equals(tmp_answer)) {
            l_randen_result.setText("Correct!");
            basic.correctAnswer(tmp_id, use_hint);
            use_hint = 0;
            b_tryen.setEnabled(false);
            b_trypl.setEnabled(true);
            b_hintpl.setEnabled(true);
            b_randpl.setEnabled(true);
            t_randpl.setEnabled(true);
        } else {
            if (basic.wrongAnswer(tmp_id, t_randen.getText(), "en")) {
                l_randen_result.setText("Yep, but I need another meaning.");
            } else {
                l_randen_result.setText("Nope!");
            }

        }
    }

    private void addTranslation(){
        if(t_add1.getText().equals("") || t_add2.getText().equals("")){
            l_add.setText("Some text field is empty");
        }
        else {
            basic.addTranslation(t_add1.getText(), t_add2.getText());
            l_add.setText("Successfully added");
            t_add1.setText("");
            t_add2.setText("");
        }
    }

    private void rmTranslation(){
        if(t_rm.getText().equals("")){
            l_rm.setText("ID field is empty");
        }
        else {
            try {
                basic.removeTranslation(Integer.parseInt(t_rm.getText()));
                l_rm.setText("Successfully removed");
                t_rm.setText("");
                String id = Integer.toString(tmp_id);
            }catch(Exception e){
                l_rm.setText("Something went wrong");
            }

        }
    }

    @SuppressWarnings("Duplicates")
    private void searchForTranslations(String word, String lang, String w2){
        Vector<String> columns = new Vector<>();
        columns.add("id");columns.add("PL");columns.add("EN");

        Vector<Vector<String>> translations = new Vector<>();
        Vector<String> translation = new Vector<>(3);
        translation.add("0");translation.add("0");translation.add("0");

        List<WordEntity> trans = basic.searchForDetails(word, lang);

        for(WordEntity tr : trans){
            translation.set(0, Integer.toString(tr.id));
            translation.set(1,tr.word_0);
            translation.set(2,tr.word_1);
            translations.add(new Vector<>(translation));
        }


        if(!w2.equals("")){
            Vector<Vector<String>> temp_translations = new Vector<>();
            for(Vector<String> vec : translations){
                if(w2.equals(vec.get(2))){
                    temp_translations.add(vec);
                }
            }
            translations.clear();
            for(Vector<String> vec : temp_translations){
                translations.add(vec);
            }
        }

        table = new JTable(translations, columns){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };

        remove(jScrollPane1);
        jScrollPane1 = new JScrollPane(table);
        jScrollPane1.setBounds(20, 375, 600, 160);
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(260);
        table.getColumnModel().getColumn(2).setPreferredWidth(260);
        add(jScrollPane1);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableGetDetail_MouseClicked(evt);
            }
        });

    }

    @SuppressWarnings("Duplicates")
    private void showAllTranslations(){
        Vector<String> columns = new Vector<>();
        columns.add("id");columns.add("PL");columns.add("EN");

        Vector<Vector<String>> translations = new Vector<>();
        Vector<String> translation = new Vector<>(3);
        translation.add("0");translation.add("0");translation.add("0");

        List<WordEntity> trans = basic.getAllTranslations();

        for(WordEntity tr : trans){
            translation.set(0, Integer.toString(tr.id));
            translation.set(1,tr.word_0);
            translation.set(2,tr.word_1);
            translations.add(new Vector<>(translation));
        }

        table = new JTable(translations, columns){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };

        remove(jScrollPane1);
        jScrollPane1 = new JScrollPane(table);
        jScrollPane1.setBounds(20, 375, 600, 160);
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(260);
        table.getColumnModel().getColumn(2).setPreferredWidth(260);
        add(jScrollPane1);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableGetDetail_MouseClicked(evt);
            }
        });
    }

    private void tableGetDetail_MouseClicked(MouseEvent e){
        int i = table.getSelectedRow();
        TableModel model = table.getModel();
        t_rm.setText(model.getValueAt(i,0).toString());

    }

    private void tabSearch(){
        if(!t_tabpl.getText().equals("") && t_taben.getText().equals("")){
            if (toggleButton.isSelected()) {
                searchInMainDictionary(t_tabpl.getText(), "pl", "");
            } else {
                searchForTranslations(t_tabpl.getText(), "pl", "");
            }
            l_tab.setText("");
        }else if(t_tabpl.getText().equals("") && !t_taben.getText().equals("")){
            if (toggleButton.isSelected()) {
                searchInMainDictionary(t_taben.getText(), "en", "");
            } else {
                searchForTranslations(t_taben.getText(), "en", "");
            }
            l_tab.setText("");
            //table.repaint();
        }else if(!(t_tabpl.getText().equals("") && t_taben.getText().equals(""))){
            if (toggleButton.isSelected()) {
                searchInMainDictionary(t_tabpl.getText(), "pl", t_taben.getText());
            } else {
                searchForTranslations(t_tabpl.getText(), "pl", t_taben.getText());
            }
            l_tab.setText("");
            //table.repaint();
        }else{
            l_tab.setText("Text fields are empty");
        }
    }

    /* FUNCTIONS FOR MAIN DICTIONARY */

    @SuppressWarnings("Duplicates")
    private void searchInMainDictionary(String word, String lang, String w2){
        Vector<String> columns = new Vector<>();
        columns.add("id");columns.add("PL");columns.add("EN");

        Vector<Vector<String>> translations = new Vector<>();
        Vector<String> translation = new Vector<>(3);
        translation.add("0");translation.add("0");translation.add("0");

        List<DictEntity> trans = basic.searchInMainDictionary(word, lang);

        for(DictEntity tr : trans){
            translation.set(0, Integer.toString(tr.id));
            translation.set(1,tr.polish);
            translation.set(2,tr.english);
            translations.add(new Vector<>(translation));
        }


        if(!w2.equals("")){
            Vector<Vector<String>> temp_translations = new Vector<>();
            for(Vector<String> vec : translations){
                if(w2.equals(vec.get(2))){
                    temp_translations.add(vec);
                }
            }
            translations.clear();
            for(Vector<String> vec : temp_translations){
                translations.add(vec);
            }
        }

        table = new JTable(translations, columns){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };

        remove(jScrollPane1);
        jScrollPane1 = new JScrollPane(table);
        jScrollPane1.setBounds(20, 375, 600, 160);
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(260);
        table.getColumnModel().getColumn(2).setPreferredWidth(260);
        add(jScrollPane1);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableGetDetail_MouseClicked_ADD(evt);
            }
        });

    }

    private void tableGetDetail_MouseClicked_ADD(MouseEvent e){
        int i = table.getSelectedRow();
        TableModel model = table.getModel();
        t_add1.setText(model.getValueAt(i,1).toString());
        t_add2.setText(model.getValueAt(i, 2).toString());
    }

}
