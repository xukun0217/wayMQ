package ananas.waymq.droid.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

import ananas.waymq.droid.api.DefaultMember;
import ananas.waymq.droid.api.IMember;
import ananas.waymq.droid.api.IMemberManager;

public class MemberManagerImpl implements IMemberManager {

	private final Map<String, IdSet> _keyword_map;
	private final Map<String, IMember> _member_map;
	private final Set<String> _modified;

	public MemberManagerImpl() {
		this._keyword_map = new Hashtable<String, IdSet>();
		this._member_map = new Hashtable<String, IMember>();
		this._modified = new HashSet<String>();
	}

	class IdSet {

		final Set<String> _set;

		public IdSet() {
			this._set = new HashSet<String>();
		}
	}

	@Override
	public List<IMember> findMembers(String keyword) {
		List<IMember> rlt = new ArrayList<IMember>();
		if (keyword == null) {
			keyword = "";
		}
		IdSet set = this._keyword_map.get(keyword);
		if (set == null) {
			return rlt;
		}
		List<String> ids = new ArrayList<String>(set._set);
		for (String id : ids) {
			IMember mem = this._member_map.get(id);
			if (mem != null)
				rlt.add(mem);
		}
		return rlt;
	}

	@Override
	public IMember addMember(IMember member) {
		final String id = member.getId().toString().trim();
		IMember ret = this._member_map.get(id);
		if (ret == null) {
			ret = member;
			this._modified.add(id);
			this.__do_add(id, member);
		}
		return ret;
	}

	private void __do_add(String id, IMember member) {
		this._member_map.put(id, member);
		String[] kws = member.getKeyWords();
		for (String kw : kws) {
			this.__add_key_word(id, member, kw);
		}
		this.__add_key_word(id, member, "");
		this.__add_key_word(id, member, id);
		this.__add_key_word(id, member, member.getName());
		this.__add_key_word(id, member, member.getPhone());
	}

	private void __add_key_word(String id, IMember member, String keyword) {
		final int len = keyword.length();
		for (int i = Math.min(len, 4); i >= 0; i--) {
			String key = keyword.substring(0, i);
			IdSet set = this._keyword_map.get(key);
			if (set == null) {
				set = new IdSet();
				this._keyword_map.put(key, set);
			}
			set._set.add(id);
		}
	}

	@Override
	public void setModifiedMember(IMember member) {
		String id = member.getId().toString().trim();
		this._modified.add(id);
	}

	@Override
	public void save(File path) {
		try {
			Set<String> set = new HashSet<String>(this._modified);
			for (String id : set) {
				IMember member = this._member_map.get(id);
				if (member != null) {
					File file = Helper.getFileById(path, id);
					Properties prop = member.toProperties();
					Helper.savePropertiesAsZip(file, prop);
				}
			}
			this._modified.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void load(File path) {
		try {
			if (!path.exists())
				return;
			if (!path.isDirectory())
				return;
			File[] list = path.listFiles();
			for (File file : list) {
				if (!file.isDirectory()) {
					Properties prop = Helper.loadPropertiesAsZip(file);
					if (prop != null) {
						IMember member = new DefaultMember(prop);
						this.__do_add(member.getId().toString(), member);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static class Helper {

		public static File getFileById(File path, String id) {
			return new File(path, "member_" + id);
		}

		public static Properties loadPropertiesAsZip(File file)
				throws IOException {

			if (!file.exists())
				return null;
			if (file.isDirectory())
				return null;
			InputStream in = new FileInputStream(file);
			in = new InflaterInputStream(in);
			Properties prop = new Properties();
			prop.load(in);
			in.close();
			return prop;
		}

		public static void savePropertiesAsZip(File file, Properties prop)
				throws IOException {

			File parent = file.getParentFile();
			if (!parent.exists()) {
				parent.mkdirs();
			}
			OutputStream out = new FileOutputStream(file);
			out = new DeflaterOutputStream(out);
			prop.store(out, file.getName());
			out.close();
		}
	}
}
