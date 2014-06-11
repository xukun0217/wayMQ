package ananas.waymq.tools;

import ananas.waymq.tools.gui.MainFrame;

public class WaymqTools {

	public static void main(String[] arg) {
		boolean old_style = true;
		if (old_style) {
			EventRawDataProc proc = new EventRawDataProc();
			javax.swing.SwingUtilities.invokeLater(proc);
		} else {
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					MainFrame.show_it();
				}
			});
		}
	}
}
