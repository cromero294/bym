package es.uam.eps.bmi.search.vsm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import es.uam.eps.bmi.search.index.Index;
import es.uam.eps.bmi.search.index.structure.Posting;
import es.uam.eps.bmi.search.index.structure.PostingsList;
import es.uam.eps.bmi.search.ranking.SearchRanking;
import es.uam.eps.bmi.search.ranking.impl.RankingImpl;

public class TermBasedVSMEngine extends AbstractVSMEngine {

	public TermBasedVSMEngine(Index idx) {
		super(idx);
	}

	@Override
	public SearchRanking search(String query, int cutoff) throws IOException {
		String[] palabras_query = this.parse(query);
		// String[] palabras_query = query.toLowerCase().split("\\s+");
		
		HashMap<Integer, Double> diccionario_id_frec = new HashMap<Integer, Double>();
		
		double freq = 0;
		int numDocumentos = index.numDocs();
		
		for(String palabra : palabras_query) {
			PostingsList postings = this.index.getPostings(palabra);
			
			for(Posting post : postings) {
				freq = AbstractVSMEngine.tfidf(post.getFreq(), index.getDocFreq(palabra), numDocumentos);
				
				if(diccionario_id_frec.containsKey(post.getDocID())) {
					freq += diccionario_id_frec.get(post.getDocID());
				}
				
				diccionario_id_frec.put(post.getDocID(), freq);
			}
		}
		
		RankingImpl ranking = new RankingImpl(index, cutoff);
		
		for(Map.Entry<Integer, Double> entry : diccionario_id_frec.entrySet()) {
			ranking.add(entry.getKey(), entry.getValue()/index.getDocNorm(entry.getKey()));
		}
		
		return ranking;
	}
}
