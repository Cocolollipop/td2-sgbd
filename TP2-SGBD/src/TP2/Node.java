package TP2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node {

	private List<Integer> keys;
	private List<Node> children;
	private Pair id; //les pères
	//private final int MAX = 4; 

	public Node(List<Integer> noeud, Pair id) {
		this.keys= new ArrayList<>();
		this.keys=noeud;
		this.id = id;
		this.children=new ArrayList<>();

	}
	public String toString(){
		return ("entre"+ id.getX() + "et" + id.getY() + keys.toString());
	}

	public Pair getId() {
		return id;
	}
	public void setId(Pair id) {
		this.id = id;
	}
	public void setKeys(List<Integer> keys) {
		this.keys = keys;
	}
	public List<Integer> getKeys() {
		return keys;
	}


	public List<Node> getChildren() {
		return children;
	}
	public void setChildren(List<Node> children) {
		this.children = children;
	}

	public boolean equals(Node n1){
		return (this.getId() == n1.getId() && this.getKeys()==n1.getKeys() && this.getChildren()==n1.getChildren());

	}

	public Node getPere(BTree tree){
		for(Node pere:tree.getTree()){
			for(Node child: pere.getChildren()){
				if(child.equals(this))
					return pere;
			}
		}
		return null;

	}

	/**addKey ajoute une clé dans un noeud
	 * 
	 * @param key
	 */
	public void addKey(int key){
		this.keys.add(key);
	}

	/**
	 * addChild attribue des enfants a un noeud 
	 * @param n
	 */
	public void addChild(Node n){
		this.children.add(n);
	}


	/**isLeaf determine si un noeud est une feuille ou pas
	 * si sa liste de children est vide
	 * @return true
	 */
	public boolean isLeaf(){
		if(this.children.size()==0)
			return true;
		else
			return false;
	}

	public int getMediane(){
		return this.getKeys().get((BTree.m/2)-1);
	}

	/** Split sépare un noeud en 2 noeuds
	 * 
	 */
	public void split(BTree tree, int key){
		//trouver la valeur mediane
		int mediane = this.getKeys().get((BTree.m/2)-1);
		if(this.getPere(tree).getKeys().size()<4) {
			this.getPere(tree).addKey(mediane);
			Collections.sort(this.getPere(tree).getKeys());
		}
		else System.out.println("oups il faut resplit");


		//creation du 1er noeud
		ArrayList<Integer> list1 = new ArrayList<>();
		//liste du 1er noeud
		for(int i=0; i< this.getKeys().indexOf(this.getMediane());i++){
			list1.add(this.getKeys().get(i));
		}

		if(key<this.getMediane()) {
			list1.add(key);
			Collections.sort(list1);
		}
		int rangNoeudAvantMediane = this.getPere(tree).getKeys().indexOf(mediane) -1;
		Node noeud1 = new Node(list1, new Pair(this.getPere(tree).getKeys().get(rangNoeudAvantMediane),this.getMediane()));
		System.out.println("liste1" + noeud1.toString());

		//creation du 2e noeud
		ArrayList<Integer> list2 = new ArrayList<>();
		//liste du 1er noeud
		for(int i=this.getKeys().indexOf(this.getMediane())+1; i<this.getKeys().size() ;i++){
			list2.add(this.getKeys().get(i));
		}
		if(key>this.getMediane()) {
			list2.add(key);
			Collections.sort(list2);
		}
		Node noeud2;
		if(this.getPere(tree).getKeys().indexOf(mediane) +1 == 4) 
		{ noeud2 = new Node(list2, new Pair(this.getMediane(),1000)); 
		//cas particulier...

		}


		else{
			//caas normal
			int rangNoeudApresMediane = this.getPere(tree).getKeys().indexOf(mediane) +1;
			noeud2 = new Node(list2, new Pair(this.getMediane(),this.getPere(tree).getKeys().get(rangNoeudApresMediane))); 
			System.out.println("list2" + noeud2.toString());
		}
		tree.getTree().add(tree.getTree().indexOf(this), noeud1);
		tree.getTree().add(tree.getTree().indexOf(this)+1, noeud2);
		tree.getTree().remove(this);
		//on modifie les pairs
		//tree.getTree().indexOf(this)-1

		//Il faut trouver le pere et rajouter la mediane


	}






}