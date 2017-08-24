
import static java.lang.String.format;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author SAMSUNG
 */
public class teste {

    public static void main(String[] args) throws InterruptedException {
        long seconds = 1503614036;
        long millis = seconds * 1000;
        Date date = new Date(millis);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE,MMMM d,yyyy h:mm,a", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String formattedDate = sdf.format(date);
        System.out.println(formattedDate);
    }
}
