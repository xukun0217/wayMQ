package ananas.waymq.tools.counter;

import java.io.File;

public class Counter {

	private File _rec_dir;

	public void doFindDir() {
		DirFinder df = new DirFinder();
		df.find();
		this._rec_dir = df.getResultDir();
	}

	public void doLoadRec() {

		HtmlFinder hf = new HtmlFinder();
		// RecLoader ldr = new RecLoader();
		hf.findHTML(this._rec_dir, new HtmlFinder.Callback() {

			@Override
			public void onFile(File file) {
				// TODO Auto-generated method stub

				System.out.println("find HTML " + file);
			}

			@Override
			public void onDir(File dir) {
				// TODO Auto-generated method stub
			}
		});

	}

	public void doAll() {
		this.doFindDir();
		this.doLoadRec();
	}

}
