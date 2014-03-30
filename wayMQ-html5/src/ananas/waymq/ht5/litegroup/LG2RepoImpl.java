package ananas.waymq.ht5.litegroup;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class LG2RepoImpl implements LG2Repo {

	private final static Agent _agent;

	static {
		_agent = new Agent();
	}

	public static LG2Repo getInst() {
		return _agent.getInst();
	}

	private static class Agent {

		private LG2Repo _inst;

		public synchronized LG2Repo getInst() {
			if (_inst == null) {
				_inst = new LG2RepoImpl();
			}
			return _inst;
		}
	}

	private File _path;

	@Override
	public File getPath() {
		if (this._path == null) {
			this._path = this.__findPath();
		}
		return this._path;
	}

	private File __findPath() {
		try {
			URL loc = this.getClass().getProtectionDomain().getCodeSource()
					.getLocation();
			File p = new File(loc.toURI());
			for (; p != null; p = p.getParentFile()) {
				File file = new File(p, ".waymq");
				if (file.exists() && file.isFile()) {
					p = file;
					break;
				}
			}
			if (p == null) {
				throw new RuntimeException(
						"cannot find conf file '.waymq' in the path : " + loc);
			}
			InputStream in = new FileInputStream(p);
			Properties prop = new Properties();
			prop.load(in);
			in.close();
			String repo = prop.getProperty("repo");
			if (repo == null) {
				throw new RuntimeException("need property named of 'repo'");
			}
			System.out.println("open repo at : " + repo);
			return new File(repo);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
