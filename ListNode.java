package cracking_coding_interview;

public class ListNode {
	public ListNode next = null;
	public int val;
	
	public ListNode(int d){
		val = d;
	}
	
	public static void printList (ListNode n){
		ListNode walker = n;
		while ( walker != null){
			System.out.println(walker.val);
			walker = walker.next;
		}
	}
}
