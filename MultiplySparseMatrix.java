import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class MultiplySpareMatrix{
    public static class Node{
        int location;
        int value;
        Node next;

        Node(int d1, int d2){
            location = d1;
            value = d2;
        }
    }

    public static List<Node> multi(List<Node> m1, List<Node> m2){
        List<Node> M1 = m1; 
        List<Node> M2 = m2;
        List<Node> result = new ArrayList<Node>();
        int row = M1.size(); 
        int col = M2.size();  
        int c1 = 0;
        int c2 = 0;
        int e1 = 0;
        int e2 = 0;
        for (int i = 0; i < row; i++){
            Node r1 = M1.get(i); 
            Node node = new Node(i+1,0); 
            Node tail = node;
            Node head1 = r1;
            
            if (r1.next == null){
                result.add(node);
            }
           
            else{
                for (int j = 0; j < col; j++){
                    Node r2 = M2.get(j); 
                    Node head2 = r2;
                    int e = 0;
                    
                    if (head2.next == null){
                        continue;
                    }
                    
                    else{
                        r1 = head1.next;
                        r2 = head2.next;
                        while (r1 != null | r2 != null){
                            
                            if (r1 != null){
                                if (r2 != null){
                                    c1 = r1.location;
                                    c2 = r2.location;
                                    
                                    if(c1 < c2){
                                        r1 = r1.next;
                                    }

                                    else if(c1 > c2){
                                        r2 = r2.next;
                                    }

                                    else{
                                        e1 = r1.value;
                                        e2 = r2.value;
                                        e += e1 * e2;
                                        r1 = r1.next;
                                        r2 = r2.next;
                                    }
                                }
                                else{
                                    break;
                                }
                            }
                            else{
                                break;
                            }
                        }
                        if (e != 0){
                            Node newNode = new Node(head2.location, e);
                            tail.next = newNode;
                            tail = newNode;
                        }
                    }
                }
            result.add(node);
            }
        }
        return result;
    }

    public static void main(String args[]) throws Exception{
        Scanner input = new Scanner(System.in);
        List<Node> M1 = new ArrayList<Node>();
        List<Node> M2 = new ArrayList<Node>();
        String str = input.nextLine();

        String[] arr = str.split(", ");
        Integer row1 = Integer.parseInt(arr[0]);
        str = arr[1];
        Integer col1 = Integer.parseInt(str.split(" ")[0]);
        for (int i = 1; i <= row1; i++){
            Node node = new Node(i,0);
            Node tail = node;
            str = input.nextLine();
            if (str == null) break;
            arr = str.split(" ");
            for (int j = 1; j < arr.length; j++){
                String[] s = arr[j].split(":");
                if(s.length != 0){
                    int n1 = Integer.parseInt(s[0]);
                    int n2 = Integer.parseInt(s[1]);
                    Node nodeNext = new Node(n1, n2);
                    tail.next = nodeNext;
                    tail = nodeNext;
                }
            }
            M1.add(node);
        }

        str = input.nextLine();
        arr = str.split(", ");
        str = arr[1];
        Integer row2 = Integer.parseInt(str.split(" ")[0]); 
        Integer col2 = Integer.parseInt(arr[0]); 
        List<Node> tails = new ArrayList<Node>(); 
        //initialization
        for (int j = 1; j <= col2; j++){
            Node head = new Node(j,0);
            tails.add(head);
            M2.add(head);
        }   
        
        for(int i = 1; i <= col2; i++){
            str = input.nextLine();
            if (str == null) break;
            arr = str.split(" ");
            for(int j = 1; j < arr.length; j++){
                String[] s = arr[j].split(":");
                if (s.length != 0){
                    int n1 = Integer.parseInt(s[0]);
                    int n2 = Integer.parseInt(s[1]);
                    Node nodeNext = new Node(i, n2);
                    Node tail = tails.get(n1-1);
                    tail.next = nodeNext;
                    tails.set(n1-1, nodeNext);
                }
            }
        }
        
        List<Node> result = multi(M1, M2);
        System.out.print(String.valueOf(row1)+", "+String.valueOf(row2)+" ");
        for (int i = 0; i < result.size(); i++){
            System.out.print("\n");
            System.out.print(String.valueOf(i+1)+" ");
            Node printNode = result.get(i);
            if (printNode.next == null){
                System.out.print(":");
            }
            else{
                while(printNode.next != null){
                    printNode = printNode.next;
                    System.out.print(String.valueOf(printNode.location)+":"+String.valueOf(printNode.value)+" ");
                }
            }
        }
    }
}