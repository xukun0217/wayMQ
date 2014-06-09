package ananas.waymq.tools;

import ananas.waymq.tools.gui.MainFrame;

public class WaymqTools {

	public static void main(String[] arg) {

		// EventRawDataProc proc = new EventRawDataProc();
		// javax.swing.SwingUtilities.invokeLater(proc);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				MainFrame.show_it();

			}
		});

	}
}
