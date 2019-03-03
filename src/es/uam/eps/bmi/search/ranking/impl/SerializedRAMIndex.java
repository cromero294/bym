package es.uam.eps.bmi.search.ranking.impl;

import java.io.IOException;
import java.util.Collection;

import es.uam.eps.bmi.search.index.AbstractIndex;
import es.uam.eps.bmi.search.index.structure.PostingsList;

public class SerializedRAMIndex extends AbstractIndex implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public

	@Override
	public int numDocs() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PostingsList getPostings(String term) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getAllTerms() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getTotalFreq(String term) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getDocFreq(String term) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getDocPath(int docID) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
