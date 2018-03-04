package kroki.app.action.style;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import kroki.app.KrokiMockupToolApp;
import kroki.app.model.SelectionModel;
import kroki.app.utils.ImageResource;
import kroki.app.utils.StringResource;
import kroki.app.view.Canvas;
import kroki.profil.VisibleElement;

/**
 *
 * @author Vladan Marsenić (vladan.marsenic@gmail.com)
 */
public class FontBoldAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
    public FontBoldAction() {
        ImageIcon smallIcon = new ImageIcon(ImageResource.getImageResource("action.bold.smallImage"));
        putValue(SMALL_ICON, smallIcon);
        //putValue(NAME, StringResource.getStringResource("action.openColorText.name"));
        putValue(SHORT_DESCRIPTION, StringResource.getStringResource("action.bold.description"));
    }

    public void actionPerformed(ActionEvent e) {
        Canvas c = KrokiMockupToolApp.getInstance().getTabbedPaneController().getCurrentTabContent();
        if (c == null) {
            return;
        }
        SelectionModel selectionModel = c.getSelectionModel();

        for (VisibleElement visibleElement : selectionModel.getVisibleElementList()) {
            Font oldFont = visibleElement.getComponent().getFont();
            if (oldFont.isBold()) {
                visibleElement.getComponent().setFont(new Font(oldFont.getFamily(), Font.PLAIN, oldFont.getSize()));
            } else {
                visibleElement.getComponent().setFont(new Font(oldFont.getFamily(), Font.BOLD, oldFont.getSize()));
            }
            visibleElement.update();
        }
        c.repaint();
    }
}
