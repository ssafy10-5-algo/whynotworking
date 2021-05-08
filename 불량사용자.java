import java.util.HashSet;

public class 불량사용자 {
	class Solution{
	    private int[] bansame;
		private HashSet<Integer> set;
		private int u;
		private HashSet<Integer> setan;

		public int solution(String[] user_id, String[] banned_id) {
			u=user_id.length;
	    	bansame = new int[banned_id.length];
	        for (int i=0 ; i<banned_id.length ;i++){
	            for (int j=0 ; j<user_id.length; j++){
	                if(isSame(user_id[j],banned_id[i])){
	                	bansame[i] +=(1<<j); // 유저id가 8개가 최대라 비트연산해도 괜찮음!
	                }
	            }
	        }
//	        for (int i = 0; i < banned_id.length; i++) { // 확인용
//				System.out.println(bansame[i]);
//			}
	        set = new HashSet<>(); // 카운팅중 지금까지 차단된 유저를 넣어줄 set
	        setan = new HashSet<>(); // 차단된 유저 목록을 넣어줄 set
	        make(0,0);
	        return setan.size();
	    }
	    
		/**dfs함수 i번째 차단된 아이디를 확인해야 하며, 지금까지 차단된 유저의 비트식표현은 s*/
	    private void make(int i, int s) {
	    	if (i==bansame.length) {
	    		setan.add(s);
                return;
			}
			for (int j = 0; j < u; j++) {
				if ((bansame[i] & (1<<j)) != 0) {
					if (set.add(j)) { // 사실 여기를 set으로 하지 않고 s에 대한 연산으로 해도 되는데.... 이미 구현한 부분 고치기가 너무 귀찮았다.
						make(i+1, s+(1<<j));
						set.remove(j);
					}
				}
			}
		}
	    
	    /**유저아이디a가 차단된아이디b에 해당되는지 여부 확인*/
		public boolean isSame(String a, String b){
	        //a가 유저 b가 밴드
	        if (a.length() != b.length()) {
	            return false;
	        }
	        for ( int i=0 ; i<a.length(); i++){
	            if(b.charAt(i)!='*' && a.charAt(i)!=b.charAt(i)){
	                return false;
	            }
	        }
	        return true;
	    }
	}
}
