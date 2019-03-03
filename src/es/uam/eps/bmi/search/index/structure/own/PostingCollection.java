package es.uam.eps.bmi.search.index.structure.own;

import java.util.Iterator;

import es.uam.eps.bmi.search.index.structure.Posting;

public class PostingCollection implements Comparable<PostingCollection>{
	long docFreq;
	Posting post;
	Iterator<Posting> iterator;
	
	public PostingCollection(long docFreq, Posting post, Iterator<Posting> iterator) {
		this.docFreq = docFreq;
		this.post = post;
		this.iterator = iterator;
	}
	
	public Posting getPost() {
		return this.post;
	}
	
	public long getDocFreq() {
		return this.docFreq;
	}
	
	public Posting nextPost() {
		if(this.iterator.hasNext())
			this.post = this.iterator.next();
		else
			this.post = null;
		
		return this.post;
	}

	@Override
	public int compareTo(PostingCollection o) {
		return this.post.compareTo(o.getPost());
	}
}
