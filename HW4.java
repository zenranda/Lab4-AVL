import java.util.Scanner;

public class HW4 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String input;
        String task;
        int num;
        AVL<Integer> myAVL = new AVL<Integer>();

        while (sc.hasNextLine()) {
            input = sc.nextLine();
            String[] phrases = input.split(" ");
            task = phrases[0];
            
	        if (task.equals("insert")){
	        	num = Integer.parseInt(phrases[1]);
	        	myAVL.insert(num);
	        }
	        
	        if (task.equals("delete")){
	        	num = Integer.parseInt(phrases[1]);
	        	myAVL.delete(num);
	        }

	        if (task.equals("search")){
	        	num = Integer.parseInt(phrases[1]);
	        	Node<Integer> found = myAVL.search(num);
                if (found == null){
                    System.out.println("Not Found");
                } else {
                    System.out.println("Found");
                }
	        }
	        
	        if (task.equals("traverse")){
	        	myAVL.traverse("preorder", myAVL.getRoot());
	        }
	        
	        if (task.equals("exit")){
	        	System.out.println("Successful Exit.");
	        	break;
	        }

            }
        sc.close();

        }

}