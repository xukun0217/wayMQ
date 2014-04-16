package ananas.waymq.web;

import ananas.sf4lib.server.ServiceContext;
import ananas.sf4lib.server.jrp.JRPControllerManager;
import ananas.sf4lib.server.plugin.Plugin;
import ananas.sf4lib.server.plugin.PluginFactory;
import ananas.sf4lib.server.plugin.PluginInfo;
import ananas.sf4lib.server.store.StoreDAOManager;
import ananas.waymq.web.controller.EventController;
import ananas.waymq.web.controller.GroupController;
import ananas.waymq.web.controller.WaymqController;
import ananas.waymq.web.model.EventHODAO;
import ananas.waymq.web.model.GroupHODAO;

public class WaymqPlugin implements PluginInfo, PluginFactory {

	@Override
	public String getDiscription() {
		return this.getName();
	}

	@Override
	public PluginFactory getFactory() {
		return this;
	}

	@Override
	public String getName() {
		return "WayMQ";
	}

	@Override
	public Class<?> getPluginClass() {
		return MyPlugin.class;
	}

	@Override
	public Plugin createPlugin() {
		return new MyPlugin();
	}

	class MyPlugin implements Plugin {

		@Override
		public PluginInfo getInfo() {
			return WaymqPlugin.this;
		}

		@Override
		public void load(ServiceContext sc) {

			JRPControllerManager cm = sc.getControllerManager();
			StoreDAOManager daoman = sc.getStoreDAOManager();

			// class to controller
			cm.putController("WAYMQ", WaymqController.class);
			cm.putController("Event", EventController.class);
			cm.putController("Group", GroupController.class);

			// dao
			daoman.put(new EventHODAO());
			daoman.put(new GroupHODAO());

		}

		@Override
		public void unload(ServiceContext sc) {
		}
	}

}
