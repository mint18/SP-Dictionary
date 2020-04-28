package dictionary;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;

public class Undo extends AbstractAction {
    private UndoManager manager;

    public Undo(UndoManager manager) {
        this.manager = manager;
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            this.manager.undo();
        } catch (CannotRedoException var3) {
            Toolkit.getDefaultToolkit().beep();
        }

    }
}
