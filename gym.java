import java.util.Scanner;
class GymMember{
	String Name;
	double Weight;
	
	void setdata(String a,double b){
		Name = a;
		Weight = b;
	}
	void UpdateWeight(double w){
		Weight += w;
	}
	void showdetails(){
		System.out.println("Name : " + Name + "      Weight : " + Weight + "kg");
	}
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		GymMember p1 = new GymMember();
		GymMember p2 = new GymMember();
		
		p1.setdata("Sajid Bashir", 51);
		p2.setdata("Rajveer Singh", 47);
		
		while (true){
			System.out.println("Enter Your Choice");
			System.out.println("[1] Sajid Bashir         [2] Rajveer Singh     [0] Exit ");
			int p = sc.nextInt();
			
			if (p == 0)
				break;
			GymMember selected;
			if (p == 1)
				selected = p1;
			else if (p == 2)
				selected = p2;
			else {
				System.out.println("Invalid choice");
				continue;
			}
				
				System.out.println("Enter Your Choice");
				System.out.println("[1] Update detail      [2] Show detail");
				int c = sc.nextInt();
				switch (c){
					case 1:
					System.out.println("Enter gain your gain: ");
					double g = sc.nextDouble();
					selected.UpdateWeight(g);
					selected.showdetails();
					break;
					case 2:
					selected.showdetails();
					break;
					default :
					System.out.println("Invalid option");
					
				}
		}
		sc.close();
	}
}
