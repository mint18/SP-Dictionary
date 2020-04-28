package dictionary;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;

public class Redo extends AbstractAction {
    private UndoManager manager;

    public Redo(UndoManager manager) {
        this.manager = manager;
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            this.manager.redo();
        } catch (CannotRedoException var3) {
            Toolkit.getDefaultToolkit().beep();
        }

    }
}
