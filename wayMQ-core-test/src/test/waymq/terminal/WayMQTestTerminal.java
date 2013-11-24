package test.waymq.terminal;

import java.util.Properties;

import ananas.blueprint4.terminal.Terminal;
import ananas.blueprint4.terminal.TerminalFactory;
import ananas.blueprint4.terminal.loader.CommandLoader;
import ananas.lib.util.PropertiesLoader;

public class WayMQTestTerminal {

	public static void main(String[] arg) {
		(new WayMQTestTerminal()).test();
	}

	private void test() {

		this.doInit();

		// create terminal
		TerminalFactory tf = TerminalFactory.Agent.newInstance();
		Terminal t = tf.newTerminal(null);

		// load commands

		CommandLoader ldr = t.getCommandLoaderFactory().newLoader(t);
		ldr.load("resource:///test/waymq/terminal/commands.xml");

		// set properties for terminal
		long now = System.currentTimeMillis();
		String prompt = "login_" + now + "# ";
		String welcome = "welcome to " + this + "\nnow, time is " + now + "\n";
		String bye = "The terminal is end!";
		Properties prop = t.getProperties();
		prop.setProperty("terminal.welcome", welcome);
		prop.setProperty("terminal.bye", bye);
		prop.setProperty("terminal.prompt", prompt);

		t.getRunnable().run();

	}

	private void doInit() {

		PropertiesLoader.Util.loadPropertiesToSystem(this, "system.properties");

	}
}
