package ananas.waymq.tools.counter;

import java.io.File;

public class Counter {

	private File _rec_dir;

	public void doFindDir() {
		DirFinder df = new DirFinder();
		df.find();
		this._rec_dir = df.getResultDir();

		System.out.println("Done.");
	}

	public void doLoadRec() {
		// TODO Auto-generated method stub

	}

}
