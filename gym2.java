import java.util.Scanner;
import java.util.ArrayList;

class GymMember {
    String name;
    double weight;

    GymMember(String a, double b) {
        name = a;
        weight = b;
    }

    void updateWeight(double w) {
        weight += w;
    }

    void showDetails() {
        System.out.println("Name : " + name + "   Weight : " + weight + " kg");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<GymMember> members = new ArrayList<>();

        // default members
        members.add(new GymMember("Sajid Bashir", 51));
        members.add(new GymMember("Rajveer Singh", 47));

        while (true) {
            System.out.println("\nEnter Your Choice");
            System.out.println("[1] Show details   [2] Add Member   [3] Update Weight   [0] Exit");

            int p = sc.nextInt();

            if (p == 0)
                break;

            switch (p) {

                case 1:
                    if (members.size() == 0) {
                        System.out.println("Data not found");
                    } else {
                        for (int i = 0; i < members.size(); i++) {
                            System.out.print((i + 1) + ". ");
                            members.get(i).showDetails();
                        }
                    }
                    break;

                case 2:
                    sc.nextLine(); // clear buffer
                    System.out.print("Enter new member's name : ");
                    String n = sc.nextLine();

                    System.out.print("Enter new member's weight : ");
                    double w = sc.nextDouble();

                    members.add(new GymMember(n, w));
                    System.out.println("Member added successfully");
                    break;

                case 3:
                    if (members.size() == 0) {
                        System.out.println("Data not found");
                    } else {
                        for (int i = 0; i < members.size(); i++) {
                            System.out.println((i + 1) + ". " + members.get(i).name);
                        }

                        System.out.print("Select Member : ");
                        int d = sc.nextInt();

                        System.out.print("Gained weight : ");
                        double dw = sc.nextDouble();

                        members.get(d - 1).updateWeight(dw);
                        members.get(d - 1).showDetails();
                    }
                    break;

                default:
                    System.out.println("Invalid Input");
            }
        }

        sc.close();
    }
}
