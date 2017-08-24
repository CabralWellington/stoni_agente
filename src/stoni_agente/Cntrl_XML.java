/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stoni_agente;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlList;

/**
 *
 * @author SAMSUNG
 */
public class Cntrl_XML {

    static String ID = "ASAD-AS22";

    public static void main(String[] args) throws InterruptedException {
        List<Livro> lista = new ArrayList<Livro>();
        Livro livro = new Livro(2000, "Assim Falou Zaratustra", "123");
        Livro livro1 = new Livro(2000, "Assim Falou ", "445");
        Livro livro2 = new Livro(2000, "Assim Falou Zaratustra", "588");
        lista.add(livro);
        lista.add(livro1);
        lista.add(livro2);
        Cntrl_XML xml = new Cntrl_XML();
        xml.Salvar_XML(lista);
        
        }

    public void Salvar_XML(List<?> obj) {
        XStream xstream = new XStream();
        // Serializando objeto
        xstream.alias("livro", Object.class);
        String nome = "" + ID + "_$_" + System.currentTimeMillis() + ".xml";
        File arquivo = new File(nome);
        FileOutputStream gravar;
        try {
            gravar = new FileOutputStream(arquivo);
            gravar.write(xstream.toXML(obj).getBytes());
            gravar.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void lerXML_init() {
        try {
            lerArquivoDeURL("http://psvamazon.ddns.net/" + ID + ".xml", "temp");

            XStream xStream = new XStream(new Dom4JDriver());
            xStream.alias("TEMP", ArrayList.class);
            xStream.processAnnotations(Object.class);

            BufferedReader input = new BufferedReader(new FileReader("temp/"+ID+".xml"));
            ArrayList<Livro> enderecos = (ArrayList) xStream.fromXML(input);
            input.close();

            for (Livro livro : enderecos) {
                System.out.println(livro.toString());
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void lerXML(String path) {
        try {
            XStream xStream = new XStream(new Dom4JDriver());
            xStream.alias("TEMP", ArrayList.class);
            xStream.processAnnotations(Object.class);

            BufferedReader input = new BufferedReader(new FileReader(path));
            ArrayList<Livro> enderecos = (ArrayList) xStream.fromXML(input);
            input.close();

            for (Livro livro : enderecos) {
                System.out.println(livro.toString());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static File lerArquivoDeURL(String stringUrl, String pathLocal) {
        try {
            //Encapsula a URL num objeto java.net.URL
            URL url = new URL(stringUrl);
            //Queremos o arquivo local com o mesmo nome descrito na URL
            //Lembrando que o URL.getPath() ira retornar a estrutura 
            //completa de diretorios e voce deve tratar esta String
            //caso nao deseje preservar esta estrutura no seu disco local.
            String nomeArquivoLocal = url.getPath();
            //Cria streams de leitura (este metodo ja faz a conexao)...
            InputStream is = url.openStream();
            //... e de escrita.
            FileOutputStream fos = new FileOutputStream(pathLocal + nomeArquivoLocal);
            //Le e grava byte a byte. Voce pode (e deve) usar buffers para
            //melhor performance (BufferedReader).
            int umByte = 0;
            while ((umByte = is.read()) != -1) {
                fos.write(umByte);
            }
            //Nao se esqueca de sempre fechar as streams apos seu uso!
            is.close();
            fos.close();
            //apos criar o arquivo fisico, retorna referencia para o mesmo
            return new File(pathLocal + nomeArquivoLocal);
        } catch (Exception e) {
            //Lembre-se de tratar bem suas excecoes, ou elas tambem lhe tratarão mal!
            //Aqui so vamos mostrar o stack no stderr.
            System.err.println("Erro de conexao com o ser");
        }
        return null;
    }
}
