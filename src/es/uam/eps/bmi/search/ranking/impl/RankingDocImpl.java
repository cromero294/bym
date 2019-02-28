package es.uam.eps.bmi.search.ranking.impl;

import java.io.IOException;

import es.uam.eps.bmi.search.index.Index;
import es.uam.eps.bmi.search.ranking.SearchRankingDoc;

public class RankingDocImpl extends SearchRankingDoc{

	Index index;
	int docID;
	double score;
	String path;
	
	public RankingDocImpl(Index idx, int docID, double score, String path){
		index = idx;
		this.docID = docID;
		this.score = score;
		this.path = path;
	}
	
	@Override
	public double getScore() {
		return score;
	}

	@Override
	public int getDocID() {
		return docID;
	}

	@Override
	public String getPath() throws IOException {
		return path;
	}

}
