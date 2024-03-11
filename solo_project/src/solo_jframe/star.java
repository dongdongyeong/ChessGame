package solo_jframe;

public class star {

	public static void main(String[] args) {
		int sum = 1;
		int trim = 5;
			for(int z = 0; z < 5;z++) {
				for(int w = 1; w < trim; w++) {
					System.out.print(" ");
				}
				for(int i = 1; i <= sum; i++) {
					System.out.print("*");
				}
					System.out.println();
				trim--;
				sum += 2;
			}
	}

}
