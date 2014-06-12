package ananas.waymq.tools.counter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.LSSerializerFilter;
import org.xml.sax.SAXException;

public class RecLoader {

	public List<BasePaymentItem> loadHtmlRec(File file) {
		try {

			Document doc = this.loadDoc(file);

			// date
			myTable table_meta = this.findTable(doc, "meta");
			String date = this.loadDate(table_meta);

			// payment
			myTable table_payment = this.findTable(doc, "payment");
			List<BasePaymentItem> plist = this.loadPaymentList(table_payment);

			// cost
			myTable table_cost = this.findTable(doc, "cost");
			double cost = this.loadTotalCost(table_cost);

			// compute
			double people = 0;
			for (BasePaymentItem pi : plist) {
				people += pi.weight;
			}
			final double cpp = cost / people;
			for (BasePaymentItem pi : plist) {
				pi.date = date;
				pi.cost = pi.weight * cpp;
			}

			// print
			System.out.println();
			System.out.println("Date: " + date);
			System.out.println("Cost: " + cost);
			for (BasePaymentItem pi : plist)
				System.out.println("    pi: " + pi);
			System.out.println();

			return plist;

		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	private String loadDate(myTable table) {
		int cnt = table.countRow();
		for (int row = cnt - 1; row >= 0; row--) {
			String key = table.get(row, 0);
			String val = table.get(row, 1);
			if ("Date".equalsIgnoreCase(key)) {
				return val;
			}
		}
		return "";
	}

	private double loadTotalCost(myTable table) {
		final int cnt = table.countRow();
		String s = table.get(cnt - 1, 3);
		if (s != null)
			return Double.parseDouble(s);
		return 0;
	}

	private List<BasePaymentItem> loadPaymentList(myTable table) {

		List<BasePaymentItem> plist = new ArrayList<BasePaymentItem>();
		final int cnt_row = table.countRow();
		for (int r = 1; r < cnt_row; r++) {

			String nick = table.get(r, 0);
			String id = table.get(r, 1);
			String weight = table.get(r, 2);
			String amount = table.get(r, 3);

			myPaymentItem pi = new myPaymentItem();
			pi.nickname = nick;
			pi.set_id(id);
			pi.set_weight(weight);
			pi.set_amount(amount);
			plist.add(pi);
		}

		return plist;
	}

	private myTable findTable(Document doc, String className) {
		NodeList list = doc.getElementsByTagName("table");
		final int len = list.getLength();
		for (int i = 0; i < len; i++) {
			Node node = list.item(i);
			if (node instanceof Element) {
				Element ele = (Element) node;
				String className2 = ele.getAttribute("class");
				if (className.equals(className2)) {
					return new myTable(ele);
				}
			}
		}
		return null;
	}

	private Document loadDoc(File file) throws ParserConfigurationException,
			SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(file);
		return doc;
	}

	class MyFormatFilter implements LSSerializerFilter {

		@Override
		public short acceptNode(Node node) {
			final int type = node.getNodeType();
			switch (type) {
			case Node.TEXT_NODE:
				// return LSSerializerFilter.FILTER_SKIP;
			default:
				return LSSerializerFilter.FILTER_ACCEPT;
			}
		}

		@Override
		public int getWhatToShow() {
			return LSSerializerFilter.SHOW_ALL;
		}
	}

	class myRow {

		final List<String> _cell_list;

		public myRow(Element tr) {
			this._cell_list = new ArrayList<String>();
		}

		public void addData(String s) {
			this._cell_list.add(s);
		}
	}

	class myTable {

		// private final Element _table_element;
		private final List<myRow> _row_list;

		public myTable(Element ele) {
			this._row_list = new ArrayList<myRow>();
			// this._table_element = ele;
			this.scanTable(ele);
		}

		public String get(int row, int col) {
			if ((0 <= row) && (row < this._row_list.size())) {
				myRow r = this._row_list.get(row);
				if ((0 <= col) && (col < r._cell_list.size())) {
					return r._cell_list.get(col);
				}
			}
			return null;
		}

		public int countRow() {
			return this._row_list.size();
		}

		private void scanTable(Element table) {
			NodeList chs = table.getChildNodes();
			final int len = chs.getLength();
			for (int i = 0; i < len; i++) {
				Node ch = chs.item(i);
				if (ch instanceof Element) {
					Element ele = (Element) ch;
					String tag = ele.getTagName();
					if ("tr".equalsIgnoreCase(tag)) {
						this.scanRow(ele);
					}
				}
			}
		}

		private void scanRow(Element tr) {

			myRow row = new myRow(tr);
			this._row_list.add(row);

			NodeList list = tr.getChildNodes();
			final int len = list.getLength();
			for (int i = 0; i < len; i++) {
				Node ch = list.item(i);
				if (!(ch instanceof Element))
					continue;
				Element cell = (Element) ch;
				String tag = cell.getTagName();
				if ("td".equalsIgnoreCase(tag)) {
					String s = this.getTextIn(cell);
					row.addData(s);
				}
			}

		}

		private String getTextIn(Element cell) {
			return cell.getTextContent();
		}

	}

	class myPaymentItem extends BasePaymentItem {

		public String toString() {
			StringBuilder sb = new StringBuilder();

			sb.append("\t date:");
			sb.append(this.date);

			sb.append("\t id:");
			sb.append(id);
			sb.append("\t name:");
			sb.append(nickname);
			sb.append("\t weight:");
			sb.append(weight);

			sb.append("\t amount:");
			sb.append(amount);

			sb.append("\t cost:");
			sb.append(this.cost);

			return sb.toString();
		}

		public void set_amount(String s) {
			this.amount = Double.parseDouble(s);
		}

		public void set_weight(String s) {
			this.weight = Double.parseDouble(s);
		}

		public void set_id(String s) {
			StringBuilder sb = new StringBuilder();
			char[] chs = s.toCharArray();
			for (char ch : chs) {
				if (('0' <= ch) && (ch <= '9'))
					sb.append(ch);
			}
			this.id = Long.parseLong(sb.toString());
		}

	}

}
