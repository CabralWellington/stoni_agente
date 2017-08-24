/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stoni_dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import stoni_bean.Impressora;
import stoni_connect.ConnectionFactory;

/**
 *
 * @author SAMSUNG
 */
public class Impressora_Dao {
       Session se;
        Transaction tx;
    List<Impressora> lista = new ArrayList<Impressora>();

    public void Salvar(Impressora aa1) {
      
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

    public void Alterar(Impressora aa1) {
        se = ConnectionFactory.getSessionFactory();
        tx = se.beginTransaction();
        se.update(aa1);
        tx.commit();
     
            
        se.close();
        }

    public void Deletar(Impressora aa1) throws HibernateException{
            se = ConnectionFactory.getSessionFactory();
            tx = se.beginTransaction();
            se.delete(aa1);
            tx.commit();
    
            
            se.close();
    }

    public Impressora BuscarPorId(String aa1) {
        se = ConnectionFactory.getSessionFactory();
        Query qry = se.createSQLQuery("select * from Impressora where Ip_Imp = '" + aa1 + "'").addEntity(Impressora.class);
        lista = qry.list();
      
            
        return lista.get(0);
    }

    public Impressora BuscarPorNome(String aa1) {
        se = ConnectionFactory.getSessionFactory();
        Query qry = se.createSQLQuery("select * from Impressora where Nome_Imp = '" + aa1 + "'").addEntity(Impressora.class);
        lista = qry.list();
      
        return lista.get(0);
    }
    public void DeletaPorNome(String aa1) {
        se = ConnectionFactory.getSessionFactory();
        Query qry = se.createSQLQuery("delete from Impressora where Ip_Imp = '" + aa1 + "'").addEntity(Impressora.class);
        lista = qry.list();
  
    }

    public List<Impressora> Listar() {
        se = ConnectionFactory.getSessionFactory();
        lista = se.createCriteria(Impressora.class).list();
        
            return lista;
    }
         public int Soma() {
        se = ConnectionFactory.getSessionFactory();
        Query qry = se.createSQLQuery("select * from Impressora").addEntity(Impressora.class);
        lista = qry.list();
        qry = null;
       
            se.close();
        return lista.size();
    }
}
