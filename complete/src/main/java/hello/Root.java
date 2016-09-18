package hello;

import java.util.List;

public class Root {
	private String comment;
	private List<Double> roots;
	
	public Root(String comment, List<Double> roots){
		this.comment = comment;
		this.roots = roots;
	}
	
	public Root combine(Root root){
		if(root.getRoots()!=null){
			if(this.roots == null){
				this.roots = root.getRoots();
			}else{
				this.roots.addAll(root.getRoots());
			}
		}
		return this;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Double> getRoots() {
		return roots;
	}

	public void setRoots(List<Double> roots) {
		this.roots = roots;
	}

}
