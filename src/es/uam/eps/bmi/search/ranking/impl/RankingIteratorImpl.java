package es.uam.eps.bmi.search.ranking.impl;

import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;

import es.uam.eps.bmi.search.index.Index;
import es.uam.eps.bmi.search.ranking.SearchRankingDoc;

public class RankingIteratorImpl implements Iterator<SearchRankingDoc> {

	Index index;
	PriorityQueue<SearchRankingDoc> heap;
	
	public RankingIteratorImpl(Index idx, PriorityQueue<SearchRankingDoc> r){
        index = idx;
        heap = r;
        
//        heap = new PriorityQueue<SearchRankingDoc>(r.size(), Collections.reverseOrder());
//        
//        SearchRankingDoc doc = null;
//        
//        while((doc = r.poll()) != null) {
//        	heap.add(doc);
//        }
	}
	
    // Empty result list
    public RankingIteratorImpl() {
        index = null;
        heap = new PriorityQueue<SearchRankingDoc>();
    }
	
	@Override
	public boolean hasNext() {
		return heap.size() != 0;
	}

	@Override
	public SearchRankingDoc next() {
		return this.heap.poll();
	}

}
