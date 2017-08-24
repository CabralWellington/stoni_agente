/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stoni_dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.snmp4j.PDU;
import stoni_bean.Impressao;
import stoni_bean.Tem;
import stoni_connect.ConnectionFactory;

/**
 *
 * @author SAMSUNG
 */
public class Impressao_Dao {

    Session se;
    Transaction tx;
    private static List<Impressao> lista = new ArrayList<Impressao>();
    private static List<Impressao> lista2 = new LinkedList<Impressao>();

    public void Salvar(Impressao aa1) {

        try {
            se = ConnectionFactory.getSessionFactory();
            tx = se.beginTransaction();
            se.save(aa1);
            tx.commit();

            se.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void Alterar(Impressao aa1) {
        se = ConnectionFactory.getSessionFactory();
        tx = se.beginTransaction();
        se.update(aa1);
        tx.commit();

        se.close();
    }

    public void Deletar(Impressao aa1) throws HibernateException {
        se = ConnectionFactory.getSessionFactory();
        tx = se.beginTransaction();
        se.delete(aa1);
        tx.commit();

        se.close();
    }

    public Impressao BuscarPorId(String aa1) {
        se = ConnectionFactory.getSessionFactory();
        Query qry = se.createSQLQuery("select * from Impressao where Ip_Imp = '" + aa1 + "'").addEntity(Impressao.class);
        lista = qry.list();

        return lista.get(0);
    }

    public Impressao BuscarPorNome(String aa1) {
        se = ConnectionFactory.getSessionFactory();
        Query qry = se.createSQLQuery("select * from Impressao where Nome_Imp = '" + aa1 + "'").addEntity(Impressao.class);
        lista = qry.list();

        return lista.get(0);
    }

    public void DeletaPorNome(String aa1) {
        se = ConnectionFactory.getSessionFactory();
        Query qry = se.createSQLQuery("delete from Impressao where Ip_Imp = '" + aa1 + "'").addEntity(Impressao.class);
        lista = qry.list();

    }

    public List<Impressao> Listar() {
        se = ConnectionFactory.getSessionFactory();
        lista = se.createCriteria(Impressao.class).list();

        return lista;
    }

    public int Soma() {
        se = ConnectionFactory.getSessionFactory();
        Query qry = se.createSQLQuery("select * from Impressao").addEntity(Impressao.class);
        lista = qry.list();
        qry = null;
        se.close();
        return lista.size();
    }

    public List<Impressao> pduToImpressao(PDU pdu, Tem tem) {
        Impressao impre;
        int j = -1;
        try {
            lista.removeAll(lista);
        } catch (Exception e) {
        }
        for (int i = 0; i < tem.getEquipamento().getJobListCountEquipamento(); i++) {
            try {
                impre = new Impressao();
                j++;
                impre.setNomeDoArquivoImprime(MudaHexa(pdu.get(j).toValueString()));
                j++;
                impre.setQtdPaginasImprime(Integer.parseInt(pdu.get(j).toValueString()));
                j++;
                if (pdu.get(j).toValueString().equals("1")) {
                    impre.setTipoImprime("Impressao");
                } else if (pdu.get(j).toValueString().equals("2")) {
                    impre.setTipoImprime("Copia");
                } else {
                    impre.setTipoImprime("deu erro");
                }
                j++;
                if (!pdu.get(j).toValueString().equals("")) {
                    impre.setNomeIdentidade(pdu.get(j).toValueString());
                } else {
                    impre.setNomeIdentidade("Outros");
                }
                j++;
                
                mudaData(pdu.get(j).toValueString());
                if (impre.getQtdPaginasImprime() != 0) {
                    //System.out.println(impre.toString());
                impre.setValorImprime(impre.getQtdPaginasImprime()*tem.getPreco().getA4PbPerfil());
                lista.add(impre);
                }
            } catch (Exception e) {
            }
        }
        return lista;
    }

    private String MudaHexa(String aa1) {
        StringBuilder saida = new StringBuilder();
        aa1 = aa1.replaceAll(":", "");
        try {
            for (int i = 0; i < aa1.length(); i += 2) {
                String str = aa1.substring(i, i + 2);
                saida.append((char) Integer.parseInt(str, 16));
                str = null;
            }
            return saida.toString();
        } catch (Exception e) {
        }
        return aa1;
    }
    
    private void mudaData(String data){
        try {
        Date date = new Date(data);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(format.format(date));
        
        } catch (Exception e) {
        e.printStackTrace();
        }
        }
}
