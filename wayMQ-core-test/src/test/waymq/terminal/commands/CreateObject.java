package test.waymq.terminal.commands;

import test.waymq.terminal.MyGlobal;
import ananas.blueprint4.terminal.Command;
import ananas.blueprint4.terminal.ExecuteContext;
import ananas.waymq.api.IDocument;
import ananas.waymq.api.IPhone;

public class CreateObject implements Command {

	@Override
	public void execute(ExecuteContext context) {

		MyGlobal global = new MyGlobal(context);
		IDocument doc = global.getDocument();

		String[] param = context.getParameters();
		String type = param[0];

		IPhone phone = doc.newPhone("13012345678");

		System.out.println("" + phone);

	}

}
