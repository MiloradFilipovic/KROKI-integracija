
package graphedit.actions.pallete;

import java.awt.Cursor;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import graphedit.app.MainFrame;
import graphedit.app.MainFrame.ToolSelected;
import graphedit.model.components.Link;
import graphedit.state.LinkState;
import graphedit.state.State;
import graphedit.util.ResourceLoader;
import graphedit.view.GraphEditView;
import graphedit.view.GraphEditView.GraphEditController;

public class RealizationLinkButtonAction extends AbstractAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RealizationLinkButtonAction(){
	
		putValue(SMALL_ICON, new ResourceLoader().loadImageIcon("realization.png"));
		putValue(SHORT_DESCRIPTION, "Realization link");
		putValue(NAME, "Realization link");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		GraphEditView view = MainFrame.getInstance().getCurrentView();
		if (view == null) {
			return;
		}

		//ako se preslo iz povezivanje, mora da se ocisti
		State state=MainFrame.getInstance().getCurrentView().getCurrentState();
		state.clearEverything();
		
		GraphEditController context = view.getCurrentState().getController();
		view.getSelectionModel().removeAllSelectedElements();
		view.getSelectionModel().setSelectedLink(null);
		view.getSelectionModel().setSelectedNode(null);
		state=MainFrame.getInstance().getCurrentView().getModel().getLinkState();
		state.setView(view);
		state.setController(context);
		((LinkState)state).setLinkType(Link.LinkType.REALIZATION); 
		MainFrame.getInstance().setStatusTrack(state.toString());
		context.setCurrentState(state);

		view.setSelectedTool(ToolSelected.REALIZATION);
		view.requestFocusInWindow();
		
		view.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		view.repaint();
	}
	

}

