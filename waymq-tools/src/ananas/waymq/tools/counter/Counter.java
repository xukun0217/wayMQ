package ananas.waymq.tools.counter;

import java.io.File;
import java.util.List;

public class Counter {

	private File _rec_dir;

	public void doFindDir() {
		DirFinder df = new DirFinder();
		df.find();
		this._rec_dir = df.getResultDir();
	}

	public void doLoadRec() {

		final RecordSet rs = new RecordSet();

		HtmlFinder hf = new HtmlFinder();
		// RecLoader ldr = new RecLoader();
		hf.findHTML(this._rec_dir, new HtmlFinder.Callback() {

			@Override
			public void onFile(File file) {
				// TODO Auto-generated method stub

				System.out.println("find HTML " + file);
				RecLoader ldr = new RecLoader();
				List<BasePaymentItem> list = ldr.loadHtmlRec(file);
				for (BasePaymentItem item : list) {
					rs.add(item);
				}

			}

			@Override
			public void onDir(File dir) {
				// TODO Auto-generated method stub
			}
		});

		System.out.println(rs);

	}

	public void doAll() {
		this.doFindDir();
		this.doLoadRec();
	}

}
