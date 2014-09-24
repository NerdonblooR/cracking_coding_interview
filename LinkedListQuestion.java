package cracking_coding_interview;

public class LinkedListQuestion {

	public static ListNode partionList(ListNode head, int x){
		ListNode curHead = head;
		if (head == null){
			return null;
		}
		ListNode prev = head;
		ListNode walker = head.next;
		while (walker != null){
			if (walker.val <= x){
				prev.next = walker.next;
				walker.next = curHead;
				curHead = walker;
				walker = prev.next;
			}else{
				prev = walker;
				walker = walker.next;
			}
			
		}
		return curHead;
	}


    public static ListNode detectCycle(ListNode head) {
        if (head == null){
            return null;
        }
        if (head.next == null){
            return null;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;

        while(slow != null && fast != null){
            if (slow == fast){
                slow = head;
                while(slow != fast){
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
            slow = slow.next;
            if (fast.next != null){
                fast = fast.next.next;
            }else{
                return null;
            }
        }
        return null;
    }

    public static int[] mergeTwoSortedArray(int[] a, int[] b){

        int i = 0; int j = 0; int k = 0;

        int[] ret = new int[a.length + b.length];

        //no string consumes
        while (i < a.length && j < b.length){

            if (a[i] > b[j]){
                ret[k] = a[i];
                i++;

            }else{
                ret[k] = b[j];
                j++;
            }
            k++;
        }

        return ret;


    }

    public static ListNode mergeTwoSortedLinkedList(ListNode l1, ListNode l2){
        //use two walker to keep track of the head of un-merged part
        ListNode head = l1.val > l2.val ? l2 : l1;
        ListNode pivot = l1.val > l2.val ? l1 : l2;
        ListNode walker = head;

        if (l1 == null)return l2;
        if (l2 == null)return l1;

        while (walker.next != null){

            if (pivot == null){
                return head;
            }

            if (walker.next.val < pivot.val){
                //advance the walker
                walker = walker.next;
            }else{
                ListNode temp = walker.next;
                walker.next = pivot;
                pivot = temp;
                walker = walker.next;
            }

        }


        walker.next = pivot;


        return head;

    }



    public static ListNode mergeList(ListNode l1, ListNode l2){

        ListNode head = l1;
        ListNode curPos = head;
        ListNode temp = curPos;
        ListNode walker = l2;

        if (l1.val > l2.val){
            head = l2;
            curPos = head;
            temp = curPos;
            walker = l1;
        }

        if (l1 == null){
            return l2;
        }

        if (l2 == null){
            return l1;
        }

        while (walker != null){
            while(curPos.val <= walker.val){
                temp = curPos;
                curPos = curPos.next;
                if (curPos == null){
                    break;
                }
            }
            temp.next = walker;
            walker = curPos;
            curPos = temp.next;

        }

        return head;

    }

}
