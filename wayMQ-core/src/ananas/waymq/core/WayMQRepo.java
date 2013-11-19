package ananas.waymq.core;

import ananas.lib.io.vfs.VFile;
import ananas.objectbox.IBox;
import ananas.waymq.api.IMember;

public interface WayMQRepo {

	VFile getRepoDirectory();

	IBox getObjectBox();

	boolean exists();

	boolean create();

	boolean open();

	IMember getRoot();

}
