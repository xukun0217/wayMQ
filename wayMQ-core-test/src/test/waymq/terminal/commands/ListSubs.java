package test.waymq.terminal.commands;

import java.io.PrintStream;
import java.util.Enumeration;

import test.waymq.terminal.MyGlobal;
import ananas.blueprint4.terminal.Command;
import ananas.blueprint4.terminal.ExecuteContext;
import ananas.waymq.api.IDocument;
import ananas.waymq.api.IElement;
import ananas.waymq.api.IMember;
import ananas.waymq.api.IUser;

public class ListSubs implements Command {

	@Override
	public void execute(ExecuteContext context) {

		MyGlobal global = new MyGlobal(context);
		IDocument doc = global.getDocument();
		IUser root = doc.getRoot();
		PrintStream out = context.getTerminal().getOutput();

		Enumeration<IElement> iter = doc.objects();
		for (; iter.hasMoreElements();) {
			IElement ele = iter.nextElement();
			out.println("    " + ele.getId() + " : "
					+ ele.getClass().getSimpleName());
		}
	}

}
