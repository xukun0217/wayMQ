package ananas.waymq.core.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ananas.lib.io.vfs.VFile;
import ananas.waymq.core.EventId;
import ananas.waymq.core.IEvent;
import ananas.waymq.core.IJoinship;
import ananas.waymq.core.IMember;
import ananas.waymq.core.IMutableSnapshot;
import ananas.waymq.core.IOrgRepo;
import ananas.waymq.core.Log;
import ananas.waymq.core.LogMaker;
import ananas.waymq.core.MemberId;
import ananas.waymq.core.Money;
import ananas.waymq.core.Point;
import ananas.waymq.core.logs.DefaultLogFactory;

class RepoImpl implements IOrgRepo {

	private final IMutableSnapshot _snap;
	private final LogMaker _log_gen;
	private final VFile _repo_dir;
	private final List<Log> _log_list;

	private boolean _is_mod;

	public RepoImpl(VFile repoDir) {
		this._repo_dir = repoDir;
		this._snap = new DefaultMutableSnap();
		this._log_list = new Vector<Log>();
		this._log_gen = new DefaultLogFactory();
	}

	@Override
	public boolean exists() {
		return this._repo_dir.exists();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void check() {
		VFile dir = this._repo_dir;
		if (!dir.exists()) {
			throw new RuntimeException("error : the repo dir is not exists : "
					+ dir);
		}
		if (!dir.isDirectory()) {
			throw new RuntimeException("error : the repo path is not a dir : "
					+ dir);
		}
	}

	@Override
	public void setModified(boolean isMod) {
		this._is_mod = isMod;
	}

	@Override
	public boolean isModified() {
		return this._is_mod;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

	@Override
	public IMember[] listMembers() {
		return this._snap.listMembers();
	}

	@Override
	public IJoinship[] listJoinship(IEvent event) {
		return this._snap.listJoinship(event);
	}

	@Override
	public IEvent[] listEvents() {
		return this._snap.listEvents();
	}

	@Override
	public IMember getMember(MemberId id) {
		return this._snap.getMember(id);
	}

	@Override
	public IEvent getEvent(EventId id) {
		return this._snap.getEvent(id);
	}

	@Override
	public Log addMember(IMember newMember) {
		Log log = this._log_gen.addMember(newMember);
		return this.__exe_log(log);
	}

	@Override
	public Log addEvent(IEvent newEvent) {
		Log log = this._log_gen.addEvent(newEvent);
		return this.__exe_log(log);
	}

	@Override
	public Log join(IMember member, IEvent event) {
		Log log = this._log_gen.join(member, event);
		return this.__exe_log(log);
	}

	@Override
	public Log makeSnapshot() {
		Log log = this._log_gen.makeSnapshot()

		;
		return this.__exe_log(log);
	}

	@Override
	public Log charge(IMember member, Money money) {
		Log log = this._log_gen.charge(member, money);
		return this.__exe_log(log);
	}

	@Override
	public Log charge(IMember member, Point point) {
		Log log = this._log_gen.charge(member, point);
		return this.__exe_log(log);
	}

	@Override
	public Log pay(IMember member, Money money) {
		Log log = this._log_gen.pay(member, money);
		return this.__exe_log(log);
	}

	@Override
	public Log pay(IMember member, Point point) {
		Log log = this._log_gen.pay(member, point);
		return this.__exe_log(log);
	}

	@Override
	public Log cancel(IMember member, IEvent event) {
		Log log = this._log_gen.cancel(member, event);
		return this.__exe_log(log);
	}

	@Override
	public Log closeEvent(IEvent event) {
		Log log = this._log_gen.closeEvent(event);
		return this.__exe_log(log);
	}

	private Log __exe_log(Log log) {
		log.execute(this._snap);
		this._log_list.add(log);
		this._is_mod = true;

		System.out.println("" + log.getCommandName());
		Map<String, String> map = log.getProperties();
		List<String> list = new ArrayList<String>(map.keySet());
		java.util.Collections.sort(list);
		for (String key : list) {
			String value = map.get(key);
			System.out.println("    " + key + " = " + value);
		}

		return log;
	}

	@Override
	public VFile getRepoDirectory() {
		return this._repo_dir;
	}
}
