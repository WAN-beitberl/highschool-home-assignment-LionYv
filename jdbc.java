import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class jdbc {
    static String MAIN_TABLE_NAME = "highschool";
    static String FRIENDS_TABLE_NAME = "highschool_friendships";
    static String url = "jdbc:mysql://localhost:3306/sima";
    static String name = "root";
    static String password = "bestSima";

    public static void insertStudentInfo(String[] student) {

        for (int i = 1; i <= 5; i++) {
            student[i] = "\"" + student[i] + "\"";

        }
        student[9] = "'" + student[9] + "'";
        String value = Arrays.toString(student).replace('[', '(');
        value = value.replace(']', ')');


        String query = "INSERT INTO " + MAIN_TABLE_NAME + " VALUES" + value + ";";
        executeVoidStatement(query);
    }

    public static void executeVoidStatement(String query)
    {
        PreparedStatement statement = null;
        Connection con = null;
        System.out.println(query);
        try {
            con = DriverManager.getConnection(url, name, password);
            statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.execute();


        } catch (SQLException e) {
            System.out.println("exception : " + e.getMessage() + " error code: " + e.getErrorCode());

        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void insertFriends(String[] friendRow) throws SQLException {
        String value = Arrays.toString(friendRow).replace('[', '(').replace(']', ')');
        String query = "INSERT INTO " + FRIENDS_TABLE_NAME + " VALUES" + value + ";";
        PreparedStatement statement = null;
        Connection con = null;
        System.out.println("query: " + query);
        executeVoidStatement(query);
    }

    public static float gradeAvgOf(String param) // 1-3
    {
        if (param.equals("school")) {
            return executeFloatStatement("select AVG(grade_avg) from " + MAIN_TABLE_NAME + ";");
        } else {
            return executeFloatStatement("select AVG(grade_avg) FROM " + MAIN_TABLE_NAME + " WHERE gender = " + "'" + param + "';");
        }

    }

    public static float avgHeight() {
        float res;
        String query = "select AVG(cm_height) FROM " + MAIN_TABLE_NAME + " WHERE cm_height > 200 AND car_color = 'purple';";
        return executeFloatStatement(query);
    }


    public static float executeFloatStatement(String query) {
        String resString;
        PreparedStatement statement = null;
        Connection con = null;
        System.out.println("query: " + query);
        try {
            con = DriverManager.getConnection(url, name, password);
            statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            rs.next();

            resString = query.contains("grades") ? rs.getString(2) : rs.getString(1);

            if (resString != null) {
                return Float.parseFloat(resString);
            }

        } catch (SQLException e) {
            System.out.println("exception : " + e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return 0;
    }

    public static double[] calculatePercentages() {
        double[] res = new double[3];
        double sumPop = 0, sumReg = 0, sumL = 0;
        int friends = 0;
        for (int i = 1; i < 1000; i++) {
            friends = countFriends(i);
            if (friends >= 2) {
                sumPop++;
            } else if (friends == 1) {
                sumReg++;
            } else {
                sumL++;
            }
        }
        res[0] = sumL / 10.0;
        res[1] = sumReg / 10.0;
        res[2] = sumPop / 10.0;
        // sum / 1000 * 100 = sum/10 = %
        return res;
    }

    public static float getViewGrade(int identification) {
        String query = "select * from grades where identification = " + identification + ";";
        float res = executeFloatStatement(query);
        return res;
    }


    public static int countFriends(int id)
    {
        return getAllFriends(id).size();
    }
    public static ArrayList<Integer> getAllFriends(int id) {
        ArrayList<Integer> allFriends = new ArrayList<>();
        Set<Integer> allF = new HashSet<>();
        String query = "WITH friends AS ( select friend_id from highschool_friendships where friend_id = " + id + " or other_friend_id = " + id +  " union select other_friend_id from highschool_friendships where friend_id = " + id +  " or other_friend_id = " + id +  " ), second_degree_friends AS ( SELECT other_friend_id FROM highschool_friendships WHERE friend_id IN (SELECT * FROM friends) UNION SELECT friend_id FROM highschool_friendships WHERE other_friend_id IN (SELECT * FROM friends)) SELECT * FROM second_degree_friends where other_friend_id is not null union select * from friends WHERE friend_id is not null;";
        PreparedStatement statement = null;
        Connection con = null;
        String resString;
        try {
            con = DriverManager.getConnection(url, name, password);
            statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next()) { // iterate over result set
                    resString = rs.getString(1);
                    if (Integer.parseInt(resString) != id)
                    allF.add(Integer.parseInt(resString));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        }
        allFriends.addAll(allF); // copy set(no duplicates) to arraylist
        return allFriends;
    }

}
