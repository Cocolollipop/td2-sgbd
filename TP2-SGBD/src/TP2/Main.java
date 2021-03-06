package TP2;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
	
		
		ArrayList<Integer> root=new ArrayList<>();
		root.add(66);
		root.add(79);
		
		ArrayList<Integer> child1=new ArrayList<>();
		child1.add(50);
		child1.add(55);
		child1.add(60);
		child1.add(65);
		
		ArrayList<Integer> child2=new ArrayList<>();
		child2.add(68);
		child2.add(71);
		child2.add(74);
		child2.add(78);
		
		ArrayList<Integer> child3=new ArrayList<>();
		child3.add(81);
		child3.add(85);
		child3.add(90);
		child3.add(95);
		
		Node node1 = new Node(root,new Pair(0,1000) );
		Node node2 = new Node(child1,new Pair(0,66));
		Node node3 = new Node(child2, new Pair(66,79));
		Node node4 = new Node(child3,new Pair(79,1000));
		
		/////////////////////////////////
	
		BTree tree = new BTree();
		
		tree.build(node1);
		tree.build(node2);
		tree.build(node3);
		tree.build(node4);
		
		node1.addChild(node2);
		node1.addChild(node3);
		node1.addChild(node4);
		
		System.out.println("------------------On insere 70------------------");
		tree.insert(70);
		System.out.println("------------On a l'arbre suivant pour 70------------");
		tree.showTree();
	
		
		System.out.println("------------------On insere 80------------------");
		tree.insert(80);
		System.out.println("------------On a l'arbre suivant pour 80------------");
		tree.showTree();
		tree.search(80);
		
		System.out.println("------------------On insere 96------------------");
		tree.insert(96);
		System.out.println("------------On a l'arbre suivant pour 96------------");
		tree.showTree();
		tree.search(96);
		
		System.out.println("------------------On insere 84------------------");
		tree.insert(84);
		System.out.println("------------On a l'arbre suivant pour 84------------");
		tree.showTree();
		tree.search(84);
		
		System.out.println("--------------ON INSERE 87------------------");
		tree.insert(87);
		System.out.println("------------On a l'arbre suivant pour 87------------");
		tree.showTree();
		tree.search(87);
		
		System.out.println("--------------ON INSERE 72------------------");
		tree.insert(72);
		System.out.println("------------On a l'arbre suivant pour 72------------");
		tree.showTree();
		tree.search(72);
		System.out.println("test"); tree.search(70);
		
		System.out.println("--------------ON INSERE 120------------------");
		tree.insert(120);
		System.out.println("------------On a l'arbre suivant pour 120------------");
		tree.showTree();
		
		
		
		//System.out.println("On insere 91");
		//tree.insert(91);
		
		
		

	}

}