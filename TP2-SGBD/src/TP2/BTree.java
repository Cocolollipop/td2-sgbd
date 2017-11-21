package TP2;

import java.util.LinkedList;
import java.util.Collections;
import java.util.LinkedList;

public class BTree {

	// Identifier les feuilles
	//SORT lors de la construction
	//Chaque noeud doit avoir des fils 
	//Définit quand est-ce qu'une tree est pleine

	private LinkedList<Node> tree;
	private int height;
	protected static int m=4;
	int MIN;
	int MAX = 1000;

	public BTree() {
		tree = new LinkedList<>();
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

	public LinkedList<Node> getTree() {
		return tree;
	}

	public void setTree(LinkedList<Node> tree) {
		this.tree = tree;
	}



	public Node search(int key){
		int cptParcours = 0;
		for(Integer root:tree.get(0).getKeys()){ // récupérer la liste des clés du ROOT
			if(root==key)
				return tree.get(0);

			else if(this.getHeight()>1){ // veut dire qu'il a des children
				for(int i=1;i<this.getTree().size();i++){
					if(key>=tree.get(i).getId().getX() && key<=tree.get(i).getId().getY() ){ // On récupére l'intervalle du premier child
						 
						for(Integer ilist: tree.get(i).getKeys()){
							// On parcours les clés du noeud 

							if(ilist.equals(key)){
								System.out.println("trouvé");
								return tree.get(i);	
							}
							//System.out.println("non trouvé doit etre" + tree.get(i).toString());
							return tree.get(i); //quand il le trouve pas et qu'il doit etre dans cet intervalle
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
			if(tree.get(0).getKeys().size()==4){
				//on doit split la racine
				System.out.println("on split la racine");
				int mediane = tree.get(0).getKeys().get((BTree.m/2)-1);
				LinkedList<Integer> list1 = new LinkedList<>();
				//liste du 1er noeud
				for(int i=0; i< tree.get(0).getKeys().indexOf(mediane);i++){
					list1.add(tree.get(0).getKeys().get(i));
				}

				if(key<mediane) {
					//list1.add(key);
					Collections.sort(list1);
				}
				Node noeud1 = new Node(list1, new Pair(MIN,mediane));
				System.out.println("list1" + noeud1.toString());

				//creation du 2e noeud
				LinkedList<Integer> list2 = new LinkedList<>();
				//liste du 1er noeud
				for(int i=tree.get(0).getKeys().indexOf(mediane)+1; i<tree.get(0).getKeys().size() ;i++){
					list2.add(tree.get(0).getKeys().get(i));
				}
				if(key>mediane) {
					//list2.add(key);
					Collections.sort(list2);
				}
			
				Node noeud2 = new Node(list2, new Pair(mediane,list2.get(list2.size()-1))); 
				System.out.println("list2" + noeud2.toString());

				
				tree.remove(tree.get(0));
				System.out.println("on ajoute le noeud1");tree.addFirst(noeud2);
				System.out.println("on ajoute le noeud2" );tree.addFirst(noeud1);
				LinkedList<Integer> newroot = new LinkedList<>();
				newroot.add(mediane);
				tree.addFirst(new Node(newroot,new Pair(MIN, MAX)));
				
				
			}

		}
		if(noeud.getKeys().size()<4){ // le cas où il ya assez de place dans le noeud
			noeud.addKey(key);
			Collections.sort(noeud.getKeys());

		}else{
			//la valeur mediane 
			noeud.split(this,key);

		}

	}



}