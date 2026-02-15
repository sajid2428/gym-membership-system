import java.util.*;

class GymMember {

    private String name;
    private String password;
    private int age;
    private String gender;
    private double weight;
    private double height;
    private double activityLevel;
    private String bodyMode;

    GymMember(String name, String password, int age, String gender,
              double weight, double height,
              double activityLevel, String bodyMode) {

        this.name = name;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.activityLevel = activityLevel;
        this.bodyMode = bodyMode;
    }

    String getName() {
        return name;
    }

    boolean checkPassword(String input) {
        return password.equals(input);
    }

    void updateWeight(double w) {
        weight = w;
    }

    void updateHeight(double h) {
        height = h;
    }

    void updateActivityLevel(double a) {
        activityLevel = a;
    }

    void updateBodyMode(String mode) {
        bodyMode = mode;
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

    void showProfile() {

        System.out.println("\n------ PROFILE ------");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Weight: " + weight + " kg");
        System.out.println("Height: " + height + " cm");
        System.out.println("Body Mode: " + bodyMode);

        double maintenance = calculateMaintenance();
        long roundedMaintenance = Math.round(maintenance);

        double minCalories = 0;
        double maxCalories = 0;
        double minProtein = 0;
        double maxProtein = 0;

        if (bodyMode.equalsIgnoreCase("bulk")) {
            minCalories = maintenance * 1.15;
            maxCalories = maintenance * 1.25;
            minProtein = weight * 1.8;
            maxProtein = weight * 2.2;
        }
        else if (bodyMode.equalsIgnoreCase("leanbulk")) {
            minCalories = maintenance * 1.05;
            maxCalories = maintenance * 1.15;
            minProtein = weight * 2.0;
            maxProtein = weight * 2.4;
        }
        else {
            minCalories = maintenance * 0.75;
            maxCalories = maintenance * 0.85;
            minProtein = weight
