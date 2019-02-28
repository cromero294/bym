package es.uam.eps.bmi.search.ranking.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.PriorityQueue;

import es.uam.eps.bmi.search.index.Index;
import es.uam.eps.bmi.search.ranking.SearchRanking;
import es.uam.eps.bmi.search.ranking.SearchRankingDoc;

public class RankingImpl implements SearchRanking{
	private PriorityQueue<SearchRankingDoc> heap;
	private int cutoff;
	private Index index;
	
	public RankingImpl(Index index, int cutoff) {
		this.cutoff = cutoff;
		this.index = index;
		this.heap = new PriorityQueue<SearchRankingDoc>(cutoff);
	}

	@Override
	public Iterator<SearchRankingDoc> iterator() {
		return new RankingIteratorImpl(this.index, this.heap);
	}

	@Override
	public int size() {
		return this.heap.size();
	}

	public void add(int doc, double score) {
		try {
			SearchRankingDoc documento = new RankingDocImpl(this.index, doc, score, this.index.getDocPath(doc));
			
			if (this.heap.size() == cutoff) {
				if (this.heap.peek().getScore() < score) {
					this.heap.poll();
					this.heap.add(documento);
				}
			} else {
				if (score > 0) {
					this.heap.add(documento);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
