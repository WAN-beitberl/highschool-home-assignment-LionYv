import java.util.Scanner;

public class Report {
    static Scanner reader = new Scanner(System.in);
    public static void start()
    {
        System.out.println("greetings sima!");
        double[] percentages = new double[3];
        int input = 0, id =0;
        while (input != 8)
        {
            displayMessage();
            input = reader.nextInt();
            switch(input)
            {
                case (1):
                    System.out.println("average school grade: " +  jdbc.gradeAvgOf("school"));
                    break;
                case (2):
                    System.out.println("average male grade: " + jdbc.gradeAvgOf("Male"));
                    break;
                case (3):
                    System.out.println("average female grade: " + jdbc.gradeAvgOf("Female"));
                    break;
                case (4):
                    System.out.println("average height(meters): " + jdbc.avgHeight()/100);
                    break;
                case (5):
                    System.out.println("input a student id: ");
                    id = reader.nextInt();
                    System.out.println("friends of student " + id + ": "  + jdbc.getAllFriends(id));
                    break;
                case (6):
                    percentages = jdbc.calculatePercentages();
                    System.out.println("lonely kids percentage: " + percentages[0] + "%");
                    System.out.println("regular kids percentage: " + percentages[1] + "%");
                    System.out.println("popular kids percentage: " + percentages[2] + "%");
                    break;
                case (7):
                    System.out.println("input identification of a kid: ");
                    id = reader.nextInt();
                    System.out.println("avg grade: " + jdbc.getViewGrade(id));
                    break;
                case (8):
                    break;
                default:
                    System.out.println("Invalid option, try again.");
                    break;
            }
        }
    }



    public static void displayMessage()
    {
        System.out.println("\nchoose your preferred action: ");
        System.out.println("1 - school grades average");
        System.out.println("2 - male grades average");
        System.out.println("3 - female grades average");
        System.out.println("4 - average height of students that are over 2m and have a purple car");
        System.out.println("5 - show friends of a specific student");
        System.out.println("6 - percentages of: popular, regular and lonely students");
        System.out.println("7 - grade average of a specific student");
        System.out.println("8 - exit the program");
        System.out.println("-----------");
    }
}
