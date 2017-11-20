package TP2;

import java.util.ArrayList;
import java.util.Collections;

public class BTree {
	
	// Identifier les feuilles
	//SORT lors de la construction
	//Chaque noeud doit avoir des fils 
	//Définit quand est-ce qu'une tree est pleine
	
	private ArrayList<Node> tree;
	private int height;
	protected static int m=4;
	
	public BTree() {
		tree = new ArrayList<>();
		height =2;
		
	}
	
	public void build(Node n){
		this.tree.add(n);
	}
			
	public void showTree(){
		for(int i=0;i<tree.size();i++){
			System.out.println(tree.get(i));
		}
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public ArrayList<Node> getTree() {
		return tree;
	}

	public void setTree(ArrayList<Node> tree) {
		this.tree = tree;
	}
	
	
	
	public Node search(int key){
		int cptParcours = 0;
		for(Integer root:tree.get(0).getKeys()){ // récupérer la liste des clés du ROOT
			if(root==key)
				return tree.get(0);
			
			else if(this.getHeight()>1){ // veut dire qu'il a des children
				for(int i=1;i<this.getTree().size();i++){
						if(key>tree.get(i).getId().getX() && key<tree.get(i).getId().getY() ){ // On récupére l'intervalle du premier child
							cptParcours ++; 
							for(Integer ilist: tree.get(i).getKeys()){
								// On parcours les clés du noeud 
								
								if(ilist==key){
									System.out.println("trouvé");
									return tree.get(i);	
								}
								else {
									System.out.println("non trouvé doit etre" + tree.get(i).toString());
									return tree.get(i); //quand il le trouve pas et qu'il doit etre dans cet intervalle
								
								}
							} 		   
								
						}	
				  }
							 
				
			}
		}
		System.out.println("non trouvé");
		return null;
	     
	}
	
	
	
	public void insert(int key){
		Node noeud = search(key);
		if(noeud.getKeys().contains(key)==true){
			System.out.println("la clé est deja presente");
		}
		else{ //recherche infructueuse
			if(noeud.getKeys().size()<4){ // le cas où il ya assez de place dans le noeud
				noeud.addKey(key);
				Collections.sort(noeud.getKeys());
				
			}else{
				//la valeur mediane 
				noeud.split(this,key);
				
			}
			
			
		}
	}
	

}
