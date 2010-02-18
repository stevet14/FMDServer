package de.hdtconsulting.yahoo.finance.csv.format;

import static de.hdtconsulting.yahoo.finance.csv.format.YTag.a;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.a2;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.a5;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.b;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.b2;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.b3;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.b4;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.b6;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.c;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.c1;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.c3;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.c6;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.c8;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.d;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.d1;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.d2;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.e;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.e1;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.e7;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.e8;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.e9;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.f6;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.g;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.g1;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.g3;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.g4;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.g5;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.g6;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.h;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.i;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.i5;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.j;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.j1;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.j3;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.j4;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.j5;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.j6;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.k;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.k1;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.k2;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.k3;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.k4;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.k5;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.l;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.l1;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.l2;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.l3;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.m;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.m2;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.m3;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.m4;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.m5;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.m6;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.m7;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.m8;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.n;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.n4;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.o;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.p;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.p1;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.p2;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.p5;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.p6;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.q;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.r;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.r1;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.r2;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.r5;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.r6;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.r7;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.s;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.s1;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.s7;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.t1;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.t6;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.t7;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.t8;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.v;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.v1;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.v7;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.w;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.w1;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.w4;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.x;
import static de.hdtconsulting.yahoo.finance.csv.format.YTag.y;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdtconsulting.yahoo.finance.csv.exception.UnknownTagException;

/**
 * Format
 * @author hdt
 *
 */
public class YFormat {

	public YFormat() {

		YTag tag;

		// SYMBOL always on first position!
		tag = new YTag(s);
		tagList.add(tag);
		//
		tag = new YTag(c8);
		tagList.add(tag);
		tag = new YTag(g3);
		tagList.add(tag);
		tag = new YTag(a);
		tagList.add(tag);
		tag = new YTag(b2);
		tagList.add(tag);
		tag = new YTag(a5);
		tagList.add(tag);
		tag = new YTag(a2);
		tagList.add(tag);
		tag = new YTag(b);
		tagList.add(tag);
		tag = new YTag(b3);
		tagList.add(tag);
		tag = new YTag(b6);
		tagList.add(tag);
		tag = new YTag(b4);
		tagList.add(tag);
		tag = new YTag(c1);
		tagList.add(tag);
		tag = new YTag(c);
		tagList.add(tag);
		tag = new YTag(m5);
		tagList.add(tag);
		tag = new YTag(m6);
		tagList.add(tag);
		tag = new YTag(m7);
		tagList.add(tag);
		tag = new YTag(m8);
		tagList.add(tag);
		tag = new YTag(k4);
		tagList.add(tag);
		tag = new YTag(k5);
		tagList.add(tag);
		tag = new YTag(j5);
		tagList.add(tag);
		tag = new YTag(j6);
		tagList.add(tag);
		tag = new YTag(k2);
		tagList.add(tag);
		tag = new YTag(p2);
		tagList.add(tag);
		tag = new YTag(c6);
		tagList.add(tag);
		tag = new YTag(c3);
		tagList.add(tag);
		tag = new YTag(r1);
		tagList.add(tag);
		tag = new YTag(d);
		tagList.add(tag);
		tag = new YTag(y);
		tagList.add(tag);
		tag = new YTag(e);
		tagList.add(tag);
		tag = new YTag(j4);
		tagList.add(tag);
		tag = new YTag(e7);
		tagList.add(tag);
		tag = new YTag(e9);
		tagList.add(tag);
		tag = new YTag(e8);
		tagList.add(tag);
		tag = new YTag(q);
		tagList.add(tag);
		tag = new YTag(f6);
		tagList.add(tag);
		tag = new YTag(k);
		tagList.add(tag);
		tag = new YTag(h);
		tagList.add(tag);
		tag = new YTag(l2);
		tagList.add(tag);
		tag = new YTag(g4);
		tagList.add(tag);
		tag = new YTag(g1);
		tagList.add(tag);
		tag = new YTag(g5);
		tagList.add(tag);
		tag = new YTag(g6);
		tagList.add(tag);
		tag = new YTag(v1);
		tagList.add(tag);
		tag = new YTag(v7);
		tagList.add(tag);
		tag = new YTag(d1);
		tagList.add(tag);
		tag = new YTag(l1);
		tagList.add(tag);
		tag = new YTag(k3);
		tagList.add(tag);
		tag = new YTag(t1);
		tagList.add(tag);
		tag = new YTag(l);
		tagList.add(tag);
		tag = new YTag(k1);
		tagList.add(tag);
		tag = new YTag(g);
		tagList.add(tag);
		tag = new YTag(l3);
		tagList.add(tag);
		tag = new YTag(j);
		tagList.add(tag);
		tag = new YTag(j1);
		tagList.add(tag);
		tag = new YTag(j3);
		tagList.add(tag);
		tag = new YTag(i);
		tagList.add(tag);
		tag = new YTag(m4);
		tagList.add(tag);
		tag = new YTag(m3);
		tagList.add(tag);
		tag = new YTag(n);
		tagList.add(tag);
		tag = new YTag(n4);
		tagList.add(tag);
		tag = new YTag(o);
		tagList.add(tag);
		tag = new YTag(i5);
		tagList.add(tag);
		tag = new YTag(r);
		tagList.add(tag);
		tag = new YTag(r2);
		tagList.add(tag);
		tag = new YTag(r5);
		tagList.add(tag);
		tag = new YTag(p);
		tagList.add(tag);
		tag = new YTag(p6);
		tagList.add(tag);
		tag = new YTag(r6);
		tagList.add(tag);
		tag = new YTag(r7);
		tagList.add(tag);
		tag = new YTag(p1);
		tagList.add(tag);
		tag = new YTag(p5);
		tagList.add(tag);
		tag = new YTag(w);
		tagList.add(tag);
		tag = new YTag(m);
		tagList.add(tag);
		tag = new YTag(m2);
		tagList.add(tag);
		tag = new YTag(s1);
		tagList.add(tag);
		tag = new YTag(s7);
		tagList.add(tag);
		tag = new YTag(x);
		tagList.add(tag);
		tag = new YTag(t8);
		tagList.add(tag);
		tag = new YTag(t7);
		tagList.add(tag);
		tag = new YTag(d2);
		tagList.add(tag);
		tag = new YTag(t6);
		tagList.add(tag);
		tag = new YTag(w1);
		tagList.add(tag);
		tag = new YTag(w4);
		tagList.add(tag);
		tag = new YTag(v);
		tagList.add(tag);
		// ERROR_INDICATION always on last position
		tag = new YTag(e1);
		tagList.add(tag);
		//

		for (YTag tag1 : this.tagList) {
			tagMap.put(tag1.getCode(), tag1);
		}

	}

	/**
	 * get UrlFormatParameter
	 * @return format parameter for URL construction
	 */
	public String getUrlFormatParameter() {

		StringBuffer sb = new StringBuffer();
		for (YTag tag : tagList) {
			if (tag.isEnabled()) {
				sb.append(tag.getCode());
			}
		}
		return sb.toString();

	}

	/**
	 * get enabled tags
	 * @return list of enabled tags
	 */
	public ArrayList<YTag> getEnabledTags() {
		ArrayList<YTag> list = new ArrayList<YTag>();
		for (YTag tag : this.tagList) {
			if (tag.isEnabled()) {
				list.add(tag);
			}
		}
		return list;
	}

	private ArrayList<YTag> tagList = new ArrayList<YTag>();

	private HashMap<String, YTag> tagMap = new HashMap<String, YTag>();

	/**
	 * enable tag 
	 * 
	 * @param code
	 */
	public void setStatusOn(String code) {

		YTag tag = this.tagMap.get(code);
		if (tag != null) {
			tag.setEnabled(true);
		} else {
			throw new UnknownTagException();
		}

	}

	/**
	 * disable tag
	 * @param code
	 */
	public void setStatusOff(String code) {

		YTag tag = this.tagMap.get(code);
		if (tag != null) {
			tag.setEnabled(false);
		} else {
			throw new UnknownTagException();
		}

	}

}
