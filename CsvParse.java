import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class CsvParse {
    static String MAIN_CSV_PATH = "C:\\Users\\User\\Desktop\\highschool_sql_assignment\\highschool_sql_assignment\\highschool.csv";
    static String FRIENDSHIP_CSV_PATH = "C:\\Users\\User\\Desktop\\highschool_sql_assignment\\highschool_sql_assignment\\highschool_friendships.csv";
    CSVReader reader;
    public void parse()
        {

            try {
                reader = new CSVReader(new FileReader(MAIN_CSV_PATH));
                List<String[]> r = null;
                try {
                    r = reader.readAll();
                    for (int i = 1; i < r.size(); i++) {
                        //System.out.println(Arrays.toString(r.get(i)));
                        String[] student = r.get(i);
                        if (fixData(student)) {
                            jdbc.insertStudentInfo(student);

                        } else {
                            System.out.println("invalid student at: " + i);
                            break;
                        }

                    }
                    //r.forEach(x -> System.out.println(Arrays.toString(x)));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
            catch (FileNotFoundException e)
            {
                throw new RuntimeException(e);
            }
            //parseFriends();
        }


    public boolean fixData(String [] student)
    {
        System.out.println("checking : " + student[8] + " " + student[9]);
        if (student[8].equals("true") && student[9].isEmpty()){
            student[9] = "didnt pick";
        }
        if (student[8].equals("false") && !student[9].isEmpty()){student[9] = "";}
        return true;
    }
    public void parseFriends()
    {
        try {
            reader = new CSVReader(new FileReader(FRIENDSHIP_CSV_PATH));
            List<String[]> r = null;
            try {
                r = reader.readAll();
                for (int i = 1; i <= 1000; i++) {
                        String[] studentFriends = r.get(i);
                        studentFriends = fixFriendData(studentFriends);
                        System.out.println(Arrays.toString(studentFriends));
                        if (!(studentFriends[1] == null || studentFriends[2] == null))
                        jdbc.insertFriends(studentFriends);

                }
                //r.forEach(x -> System.out.println(Arrays.toString(x)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }
    public  String[] fixFriendData(String[] studentFriends)
    {
        studentFriends[1] = studentFriends[1].isEmpty() ? null: studentFriends[1];
        studentFriends[2] = studentFriends[2].isEmpty() ? null : studentFriends[2];
        return studentFriends;
    }



}

