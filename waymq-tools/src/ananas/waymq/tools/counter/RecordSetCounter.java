package ananas.waymq.tools.counter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ananas.waymq.tools.counter.RecordSet.myAccount;

public class RecordSetCounter {

	interface Show {

		boolean bpi = true;

	}

	public RecordSetCounter() {
		System.out.println();
	}

	public void count(RecordSet rs) {

		this.printBPI_H();

		List<Long> keys = new ArrayList<Long>(rs._accounts.keySet());
		Collections.sort(keys);
		for (Long key : keys) {
			myAccount val = rs._accounts.get(key);

			Long id = val.getId();
			String name = val.getName();
			List<BasePaymentItem> list = val.listBasePaymentItems();
			myReport report = this.makeReport(list);
			this.printReport(id, name, report);
		}
	}

	private void printReport(Long id, String name, myReport report) {
		final String tab = "\t";
		final StringBuilder sb = new StringBuilder();
		sb.append(id);
		sb.append(tab);
		sb.append(name);
		sb.append(tab);
		sb.append(report.amount);
		sb.append(tab);
		sb.append(report.cost);
		sb.append(tab);
		sb.append(report.getBalance());
		System.out.println(sb);
		if (Show.bpi)
			System.out.println();
	}

	private myReport makeReport(List<BasePaymentItem> list) {
		myReport rep = new myReport();
		double cost = 0;
		double amount = 0;
		for (BasePaymentItem item : list) {
			this.printBPI(item);
			cost += item.cost;
			amount += item.amount;
			// = item.weight ;
		}
		rep.cost = cost;
		rep.amount = amount;
		return rep;
	}

	private void printBPI_H() {
		final String tab = "\t";
		final StringBuilder sb = new StringBuilder();
		sb.append("date");
		sb.append(tab);
		sb.append("id");
		sb.append(tab);
		sb.append("nickname");
		sb.append(tab);
		sb.append("weight");
		sb.append(tab);
		sb.append("amount");
		sb.append(tab);
		sb.append("cost");
		if (Show.bpi)
			System.out.println(sb);
	}

	private void printBPI(BasePaymentItem item) {
		final String tab = "\t";
		final StringBuilder sb = new StringBuilder();
		sb.append(item.date);
		sb.append(tab);
		sb.append(item.id);
		sb.append(tab);
		sb.append(item.nickname);
		sb.append(tab);
		sb.append(item.weight);
		sb.append(tab);
		sb.append(item.amount);
		sb.append(tab);
		sb.append(item.cost);
		if (Show.bpi)
			System.out.println(sb);
	}

	class myReport {

		public double amount;
		public double cost;

		public double getBalance() {
			return amount - cost;
		}
	}

}
