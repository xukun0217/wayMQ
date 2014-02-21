package ananas.waymq.droid.api;

import java.util.Properties;

public interface IMember {

	Object getId();

	String getName();

	String getPhone();

	String[] getKeyWords();

	Properties toProperties();

}
