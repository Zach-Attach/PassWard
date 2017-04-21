package brittanyPackage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MatchingFloatValues {
    private static final String FLOATING_POINT_REGEX = "[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)";
    private static final float MAX_FLOAT_VALUE = Float.MAX_VALUE;
    private static final float MIN_FLOAT_VALUE = Float.MIN_VALUE;

    private ArrayList<String> values;
    private String filename;

    public MatchingFloatValues(String filename){
        values = new ArrayList<>();
        this.filename = filename;

        readFromFile();
    }

    public MatchingFloatValues(){

    }

    private void readFromFile(){
        try (Scanner reader = new Scanner(new File(this.filename))){

            while(reader.hasNext()){
                String next = reader.next();
                values.add(next);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getFloatingPointValues(){
        String values = "";

        for (String str :
                this.values) {
            if (str.matches(FLOATING_POINT_REGEX)){
                float f = Float.parseFloat(str);
                if (f >= MIN_FLOAT_VALUE && f <= MAX_FLOAT_VALUE)
                    values = values.concat(str + "\n");
            }
        }
        return values;
    }


    public boolean isFloatingPointValue(String input){
        if (!input.matches(FLOATING_POINT_REGEX))
            return false;

        if (Float.parseFloat(input) > Float.MAX_VALUE || Float.parseFloat(input) < Float.MIN_VALUE)
            return false;

        return true;
    }

    public static void main(String[] args) {
        File file = new File("numbers.txt");
        String filename = "";
        try {
            filename = file.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MatchingFloatValues mfv = new MatchingFloatValues(filename);
        String values = mfv.getFloatingPointValues();
        System.out.println(values);
    }

}
