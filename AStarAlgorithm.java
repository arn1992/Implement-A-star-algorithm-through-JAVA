/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

/**
 *
 * @author arn
 */
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.util.Scanner;
class AStarAlgorithm {

 static int node;
 static int edge;
 static String h[];
  static double g[];
  static double p[];
 
        public static void main(String[] args)throws FileNotFoundException{
           Scanner k=new Scanner(new File("i.txt"));
 node=k.nextInt();
 edge=k.nextInt();
 h=new String[node];
  g=new double[edge];
  p=new double[node];
  
  System.out.println("name of the node and their h value: ");
  //node name and h value
 for (int i=0;i<node;i++)
 {
 h[i]=k.next();
 System.out.print(h[i]+" ");
 p[i]=k.nextDouble();
 System.out.println(p[i]);
 System.out.println();
 }
                 Node n1=new Node(h[0],p[0]);
               
               Node n2=new Node(h[1],p[1]);
               Node n3=new Node(h[2],p[2]);
  Node n4=new Node(h[3],p[3]);
   Node n5=new Node(h[4],p[4]);
    Node n6=new Node(h[5],p[5]);
     Node n7=new Node(h[6],p[6]);
                //initialize the edges
 
                //node1
               
                System.out.println("name of the adjacent node and their adjacencies  value: ");
                for(int j=0;j<edge;j++){
                    String l=k.next();
                    String m=k.next();
                    
                    System.out.print(l+"  "+m+"--->");
                g[j]=k.nextDouble();
                System.out.print(g[j]);
                System.out.println();
                }
                n1.adjacencies = new Edge[]{
                    
                        new Edge(n2,g[0]),
                        new Edge(n5,g[1]),
                        
                         };
                 
                 //node2
                n2.adjacencies = new Edge[]{
                        new Edge(n3,g[2]),
                    //new Edge(n1,1.5),
                       
                };
                 
 
                 //node3
                n3.adjacencies = new Edge[]{
                        new Edge(n4,g[3]),
                       // new Edge(n2,2),
                        
                };
                 
                 //node4
                n4.adjacencies = new Edge[]{
                        new Edge(n7,g[4]),
                        //new Edge(n3,3),
                        
                };
                 
 
                 //node5
                n5.adjacencies = new Edge[]{
                        new Edge(n6,g[5]),
                    //new Edge(n1,2),
                       
                };
                 
                 //node6
                n6.adjacencies = new Edge[]{
                        new Edge(n7,g[6]),
                       // new Edge(n5,3),
                };
                 
                 //node7
                n7.adjacencies = new Edge[]{
                    // new Edge(n6,2),
                  //  new Edge(n4,4),
                };
                 
                               
                AstarSearch(n1,n7);
 
                List<Node> path = printPath(n7);
                System.out.println("--------------------------------------------------------");
                System.out.println("--------------------------------------------------------");
 System.out.println("So the Final Path Is: ");
                        System.out.println("Path"+" --> "+path);
 
 
        }
 
        public static List<Node> printPath(Node target){
                List<Node> path = new ArrayList<Node>();
        
        for(Node node = target; node!=null; node = node.parent){
            path.add(node);
        }
 
        Collections.reverse(path);
 
        return path;
        }
 
        public static void AstarSearch(Node source, Node goal){
 
                Set<Node> explored = new HashSet<Node>();
 
                PriorityQueue<Node> queue = new PriorityQueue<Node>( 
                        new Comparator<Node>(){
                                 //override compare method
                 public int compare(Node i, Node j){
                    if(i.f_scores > j.f_scores){
                        return 1;
                    }
 
                    else if (i.f_scores < j.f_scores){
                        return -1;
                    }
 
                    else{
                        return 0;
                    }
                 }
 
                        }
                        );
 
                //cost from start
                source.g_scores = 0;
 
                queue.add(source);
 
                boolean found = false;
 
                while((!queue.isEmpty())&&(!found)){
 
                        //the node in having the lowest f_score value
                        Node current = queue.poll();
 
                        explored.add(current);
 
                        //goal found
                        if(current.value.equals(goal.value)){
                                found = true;
                        }
 
                        //check every child of current node
                        for(Edge e : current.adjacencies){
                                Node child = e.target;
                                double cost = e.cost;
                                double temp_g_scores = current.g_scores + cost;
                                double temp_f_scores = temp_g_scores + child.h_scores;
 
 
                                /*if child node has been evaluated and 
                                the newer f_score is higher, skip*/
                                //don't need for this graph
                                if((explored.contains(child)) && 
                                        (temp_f_scores >= child.f_scores)){
                                        continue;
                                }
 
                                /*else if child node is not in queue or 
                                newer f_score is lower*/
                                
                                else if((!queue.contains(child)) || 
                                        (temp_f_scores < child.f_scores)){
 
                                        child.parent = current;
                                        child.g_scores = temp_g_scores;
                                        child.f_scores = temp_f_scores;
                                       //don't need for this graph
                                      /* if(queue.contains(child)){
                                                queue.remove(child);
                                        }*/
 
                                        queue.add(child);
 
                                }
 
                        }
 
                }
 
        }
        
}
 
class Node{
 
        public  String value;
        public double g_scores;
        public  double h_scores;
        public double f_scores = 0;
        public Edge[] adjacencies;
        public Node parent;
 
        public Node(String val, double hVal){
                value = val;
                h_scores = hVal;
        }
 
        @Override
        public String toString(){
                return value;
        }
 
}
 
class Edge{
        public  double cost;
        public  Node target;
 
        public Edge(Node targetNode, double costVal){
                target = targetNode;
                cost = costVal;
        }
}