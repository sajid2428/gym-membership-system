import java.util.Scanner;
import java.util.ArrayList;

class GymMember {

    private String name;
    private String password;
    private int age;
    private String gender;
    private double weight;
    private double height;
    private double activityLevel;
    private String bodyMode;

    private double benchPR;
    private double squatPR;
    private double deadliftPR;

    GymMember(String name, String password, int age, String gender,
              double weight, double height,
              double activityLevel, String bodyMode,
              double benchPR, double squatPR, double deadliftPR) {

        this.name = name;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.activityLevel = activityLevel;
        this.bodyMode = bodyMode;
        this.benchPR = benchPR;
        this.squatPR = squatPR;
        this.deadliftPR = deadliftPR;
    }

    String getName() {
        return name;
    }

    boolean checkPassword(String input) {
        return password.equals(input);
    }

    double calculateBMR() {
        if (gender.equalsIgnoreCase("male"))
            return (10 * weight) + (6.25 * height) - (5 * age) + 5;
        else
            return (10 * weight) + (6.25 * height) - (5 * age) - 161;
    }

    double calculateMaintenance() {
        return calculateBMR() * activityLevel;
    }

    double calculateCalories() {
        double maintenance = calculateMaintenance();

        if (bodyMode.equalsIgnoreCase("bulk"))
            return maintenance * 1.20;
        else if (bodyMode.equalsIgnoreCase("leanbulk"))
            return maintenance * 1.10;
        else
            return maintenance * 0.80;
    }

    double calculateProtein() {
        if (bodyMode.equalsIgnoreCase("cut"))
            return weight * 2.4;
        else
            return weight * 2.2;
    }

    double calculateBMI() {
        double h = height / 100;
        return weight / (h * h);
    }

    double calculateOneRepMax(double liftedWeight, int reps) {
        return liftedWeight * (1 + (reps / 30.0));
    }

    void updateWeight(double w) { weight = w; }
    void updateActivity(double a) { activityLevel = a; }
    void updateBenchPR(double pr) { benchPR = pr; }
    void updateSquatPR(double pr) { squatPR = pr; }
    void updateDeadliftPR(double pr) { deadliftPR = pr; }

    void showDetails() {

        System.out.println("\n----- PROFILE -----");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Weight: " + weight + " kg");
        System.out.println("Height: " + height + " cm");

        System.out.println("BMI: " + String.format("%.3f", calculateBMI()));
        System.out.println("Maintenance Calories: " + String.format("%.0f", calculateMaintenance()));
        System.out.println("Recommended Calories: " + String.format("%.0f", calculateCalories()));
        System.out.println("Protein: " + String.format("%.0f", calculateProtein()) + " g");

        System.out.println("\nPR Records:");
        System.out.println("Bench Press: " + benchPR + " kg");
        System.out.println("Squat: " + squatPR + " kg");
        System.out.println("Deadlift: " + deadliftPR + " kg");
        System.out.println("--------------------\n");
    }
}

public class GymSystem {

    static ArrayList<GymMember> members = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Default Member (You)
        members.add(new GymMember(
                "Sajid",
                "9031",
                21,
                "male",
                65,
                165,
                1.55,
                "leanbulk",
                80,
                120,
                150
        ));

        while (true) {

            System.out.println("===== GYM SYSTEM =====");
            System.out.println("1. Add Member");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Select option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1)
                addMember();
            else if (choice == 2)
                login();
            else
                return;
        }
    }

    static void activityGuide() {
        System.out.println("\nActivity Level Guide:");
        System.out.println("1.2   - Sedentary");
        System.out.println("1.375 - Light Exercise");
        System.out.println("1.55  - Moderate Exercise");
        System.out.println("1.725 - Heavy Exercise");
        System.out.println("1.9   - Athlete\n");
    }

    static void addMember() {

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        System.out.print("Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Gender: ");
        String gender = sc.nextLine();

        System.out.print("Weight: ");
        double weight = sc.nextDouble();

        System.out.print("Height: ");
        double height = sc.nextDouble();

        activityGuide();
        System.out.print("Enter Activity Level: ");
        double activity = sc.nextDouble();
        sc.nextLine();

        System.out.print("Body Mode (bulk/leanbulk/cut): ");
        String mode = sc.nextLine();

        System.out.print("Bench PR: ");
        double bench = sc.nextDouble();

        System.out.print("Squat PR: ");
        double squat = sc.nextDouble();

        System.out.print("Deadlift PR: ");
        double deadlift = sc.nextDouble();
        sc.nextLine();

        members.add(new GymMember(name, password, age, gender,
                weight, height, activity, mode,
                bench, squat, deadlift));

        System.out.println("Member added successfully.\n");
    }

    static void login() {

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        for (GymMember m : members) {

            if (m.getName().equals(name) && m.checkPassword(pass)) {

                System.out.println("Login Successful!\n");

                while (true) {

                    System.out.println("1. Show Profile");
                    System.out.println("2. Update Weight");
                    System.out.println("3. Update Activity Level");
                    System.out.println("4. Update PR");
                    System.out.println("5. 1RM Calculator");
                    System.out.println("6. Logout");
                    System.out.print("Choose: ");

                    int c = sc.nextInt();
                    sc.nextLine();

                    if (c == 1)
                        m.showDetails();

                    else if (c == 2) {
                        System.out.print("New Weight: ");
                        m.updateWeight(sc.nextDouble());
                        sc.nextLine();
                    }

                    else if (c == 3) {
                        activityGuide();
                        System.out.print("New Activity Level: ");
                        m.updateActivity(sc.nextDouble());
                        sc.nextLine();
                    }

                    else if (c == 4)
                        updatePR(m);

                    else if (c == 5)
                        oneRepMaxCalculator(m);

                    else
                        return;
                }
            }
        }

        System.out.println("Invalid Credentials!\n");
    }

    static void updatePR(GymMember m) {

        System.out.println("1. Bench");
        System.out.println("2. Squat");
        System.out.println("3. Deadlift");
        System.out.print("Choose: ");

        int ch = sc.nextInt();

        System.out.print("New PR: ");
        double pr = sc.nextDouble();
        sc.nextLine();

        if (ch == 1) m.updateBenchPR(pr);
        else if (ch == 2) m.updateSquatPR(pr);
        else if (ch == 3) m.updateDeadliftPR(pr);

        System.out.println("PR Updated.\n");
    }

    static void oneRepMaxCalculator(GymMember m) {

        System.out.print("Weight lifted: ");
        double weight = sc.nextDouble();

        System.out.print("Reps: ");
        int reps = sc.nextInt();
        sc.nextLine();

        double oneRM = m.calculateOneRepMax(weight, reps);

        System.out.println("Estimated 1RM: "
                + String.format("%.1f", oneRM) + " kg\n");
    }
}
