package dictionary;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.undo.UndoManager;
import java.awt.*;

public class HintTextField extends JTextField {

        UndoManager manager = new UndoManager();
        String placeholder = "";
        Color phColor = new Color(0, 0, 0);
        boolean bool_placeholder = true;


        public HintTextField(String str) {
            super(str);

            this.setMargin(new Insets(0, 3, 0, 0));

            this.getDocument().addDocumentListener(new DocumentListener() {
                public void removeUpdate(DocumentEvent e) {
                    HintTextField.this.bool_placeholder = HintTextField.this.getText().length() <= 0;
                }

                public void insertUpdate(DocumentEvent e) {
                    HintTextField.this.bool_placeholder = false;
                }

                public void changedUpdate(DocumentEvent de) {
                }
            });

            this.getDocument().addUndoableEditListener(this.manager);
            Action undo = new Undo(this.manager);
            Action redo = new Redo(this.manager);
            this.registerKeyboardAction(undo, KeyStroke.getKeyStroke(90, 2), 0);
            this.registerKeyboardAction(redo, KeyStroke.getKeyStroke(89, 2), 0);

    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(this.phColor.getRed(), this.phColor.getGreen(), this.phColor.getBlue(), 90));
        g.drawString(this.bool_placeholder ? this.placeholder : "", this.getMargin().left, this.getSize().height / 2 + this.getFont().getSize() / 2);
    }
}