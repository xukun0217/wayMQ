package ananas.waymq.tools.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import ananas.waymq.tools.counter.Counter;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1376550593188346831L;

	interface CMD {
		String file_import = "file_import";
		String file_count = "file_count";
	}

	public static void show_it() {
		MainFrame frame = new MainFrame();
		frame.onCreate();
		frame.setVisible(true);
	}

	private JFileChooser _file_chooser;

	private void onCreate() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(this.getClass().getName());
		this.setSize(800, 600);
		this.setJMenuBar(this.createMenuBar());
	}

	private JMenuBar createMenuBar() {
		JMenuBar mb = new JMenuBar();
		JMenu menu;
		// file
		menu = new JMenu("File");
		mb.add(menu);
		this.addMenuItem(menu, CMD.file_import, null);
		this.addMenuItem(menu, CMD.file_count, null);
		// end
		return mb;
	}

	private void addMenuItem(JMenu menu, String cmd, String text) {
		if (cmd == null)
			cmd = "cmd_default";
		if (text == null)
			text = cmd;
		if (text.length() < 1)
			text = "unnamed";
		JMenuItem item = new JMenuItem();
		item.setActionCommand(cmd);
		item.setText(text);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String cmd = arg0.getActionCommand();
				MainFrame.this.onCommand(cmd);
			}
		});
		menu.add(item);
	}

	protected void onCommand(String cmd) {
		System.out.println("onCommand: " + cmd);
		if (cmd == null) {
		} else if (cmd.equals(CMD.file_count)) {
			this.do_cmd_count();
		} else if (cmd.equals(CMD.file_import)) {
			this.do_cmd_import();
		}
	}

	private void do_cmd_count() {
		Counter counter = new Counter();
		counter.doAll();
		System.out.println("Done.");
	}

	private void do_cmd_import() {
		JFileChooser fc = this._file_chooser;
		if (fc == null) {
			fc = new JFileChooser();
			this._file_chooser = fc;
		}
		int result = fc.showOpenDialog(this);
		if (result != JFileChooser.APPROVE_OPTION)
			return;
		File file = fc.getSelectedFile();
		System.out.println("import " + file);

	}

	private MainFrame() {
	}

}
