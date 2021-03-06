package com.eixox;

import java.util.ArrayList;

public class PairList<TKey, TValue> extends ArrayList<Pair<TKey, TValue>> {

	private static final long serialVersionUID = -2103168918011047330L;

	public PairList() {
	}

	public PairList(int capacity) {
		super(capacity);
	}

	public final Pair<TKey, TValue> add(TKey key, TValue value) {
		final Pair<TKey, TValue> pair = new Pair<TKey, TValue>(key, value);
		super.add(pair);
		return pair;
	}

	public final TKey getKey(int ordinal) {
		return super.get(ordinal).key;
	}

	public final TValue getValue(int ordinal) {
		return super.get(ordinal).value;
	}

	public int getOrdinal(TKey key) {
		int s = this.size();
		for (int i = 0; i < s; i++)
			if (key.equals(get(i).key))
				return i;
		return -1;
	}

}
