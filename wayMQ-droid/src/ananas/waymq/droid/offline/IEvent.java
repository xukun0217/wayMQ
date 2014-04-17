package ananas.waymq.droid.offline;

import java.io.File;

public interface IEvent {

	long createTime();

	String title();

	String content();

	File eventDirectory();

}
