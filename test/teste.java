
import static java.lang.String.format;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import stoni_bean.Impressao;
import stoni_dao.Impressao_Dao;

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
            long seconds = 1503568337+14400;
            long millis = seconds * 1000;
            Date date = new Date(millis);
            Impressao impre = new Impressao();
            Impressao_Dao impredao = new Impressao_Dao();
            
           impre.setDataImprime(date);
           impredao.Salvar(impre);
            
            
            
           /* SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT-00:00"));
            String formattedDate = sdf.format(date);
            SimpleDateFormat sdf2 = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
            System.out.println(date);
    */}
    
}
