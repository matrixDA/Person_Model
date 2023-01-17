import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {
    public static void main(String[] args)
    {
        ArrayList<String> personCSVData = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        String ID = "";
        String firstName = "";
        String lastName = "";
        String title = "";
        String CSVPersonRecord = "";
        int YOB = 0;

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\PersonTestData.txt");

        boolean done = false;
        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter the ID as 6 digits");
            firstName = SafeInput.getNonZeroLenString(in, "Enter the First Name");
            lastName = SafeInput.getNonZeroLenString(in, "Enter the Last Name");
            title = SafeInput.getNonZeroLenString(in, "Enter the Last Title");
            YOB = SafeInput.getRangedInt(in,"Enter the year of birth as 4 digits", 1000, 9999);

            CSVPersonRecord = ID + ", " + firstName + ", " + lastName + ", " + title + ", " + YOB;
            personCSVData.add(CSVPersonRecord);

            done = SafeInput.getYNConfirm(in, "Are you done entering Person data?");


        } while (!done);

        for (String p:personCSVData) {
            System.out.println(p);
        }
        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for(String rec : personCSVData)
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
