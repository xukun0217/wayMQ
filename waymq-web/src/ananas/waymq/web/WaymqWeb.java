package ananas.waymq.web;

import ananas.sf4lib.server.ServiceContext;
import ananas.sf4lib.server.plugin.PluginManager;
import ananas.sf4lib.webserver.DefaultServiceAgent;
import ananas.sf4lib.webserver.ServiceAgent;

public class WaymqWeb {

	public static ServiceAgent getAgent() {
		ServiceAgent agent = new DefaultServiceAgent();
		String name = WaymqPlugin.class.getName();
		ServiceContext sc = agent.getServiceContext();
		PluginManager pm = sc.getPluginManager();
		pm.registerPlugin(name);
		sc.loadPlugin();
		return agent;
	}

}
