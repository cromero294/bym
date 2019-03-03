package es.uam.eps.bmi.search.vsm;

import java.io.IOException;
import java.util.*;

import es.uam.eps.bmi.search.index.Index;
import es.uam.eps.bmi.search.index.structure.Posting;
import es.uam.eps.bmi.search.index.structure.PostingsList;
import es.uam.eps.bmi.search.index.structure.own.PostingCollection;
import es.uam.eps.bmi.search.ranking.SearchRanking;
import es.uam.eps.bmi.search.ranking.impl.RankingImpl;

public class DocBasedVSMEngine extends AbstractVSMEngine {

	public DocBasedVSMEngine(Index index) {
		super(index);
	}

	@Override
	public SearchRanking search(String query, int cutoff) throws IOException {
		String[] palabras_query = this.parse(query);
		
		PriorityQueue<PostingCollection> heap_coseno = new PriorityQueue<PostingCollection>(palabras_query.length);
		
		RankingImpl ranking = new RankingImpl(index, cutoff);
		List<PostingCollection> lista_query = new ArrayList<PostingCollection>(palabras_query.length);
		
		for(String palabra : palabras_query) {
			PostingsList postings = this.index.getPostings(palabra);
			PriorityQueue<Posting> heap_ordena = new PriorityQueue<Posting>();
			
			for(Posting post : postings) {
				heap_ordena.add(post);
			}
			
			lista_query.add(new PostingCollection(index.getDocFreq(palabra), heap_ordena.poll(), heap_ordena.iterator()));
		}
		
		for(PostingCollection collection : lista_query) {
			heap_coseno.add(collection);
		}
		
		PostingCollection collection = null;
		double score = 0;
		int docID_anterior= heap_coseno.peek().getPost().getDocID();
		Posting control = null;
		
		while(heap_coseno.size() > 0) {
			collection = heap_coseno.poll();
			if (collection.getPost().getDocID() != docID_anterior){
				score /= index.getDocNorm(docID_anterior);
				ranking.add(docID_anterior, score);
				score = AbstractVSMEngine.tfidf(collection.getPost().getFreq(), collection.getDocFreq(), index.numDocs());
			}else{
				score += AbstractVSMEngine.tfidf(collection.getPost().getFreq(), collection.getDocFreq(), index.numDocs());
			}
			
			docID_anterior = collection.getPost().getDocID();
			
			control = collection.nextPost();
			
			if(control != null)
				heap_coseno.add(collection);
			
		}
		
		score /= index.getDocNorm(docID_anterior);
		ranking.add(docID_anterior, score);
		
		return ranking;
	}

}
