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
import stoni_bean.Identidade;
import stoni_connect.ConnectionFactory;

/**
 *
 * @author SAMSUNG
 */
public class Identidade_Dao {
               Session se;
        Transaction tx;
    List<Identidade> lista = new ArrayList<Identidade>();

    public void Salvar(Identidade aa1) {
      
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

    public void Alterar(Identidade aa1) {
        se = ConnectionFactory.getSessionFactory();
        tx = se.beginTransaction();
        se.update(aa1);
        tx.commit();
     
            
        se.close();
        }

    public void Deletar(Identidade aa1) throws HibernateException{
            se = ConnectionFactory.getSessionFactory();
            tx = se.beginTransaction();
            se.delete(aa1);
            tx.commit();
    
            
            se.close();
    }

    public Identidade BuscarPorId(String aa1) {
        se = ConnectionFactory.getSessionFactory();
        Query qry = se.createSQLQuery("select * from Identidade where Ip_Imp = '" + aa1 + "'").addEntity(Identidade.class);
        lista = qry.list();
      
            
        return lista.get(0);
    }

    public Identidade BuscarPorNome(String aa1) {
        se = ConnectionFactory.getSessionFactory();
        Query qry = se.createSQLQuery("select * from Identidade where Nome_Imp = '" + aa1 + "'").addEntity(Identidade.class);
        lista = qry.list();
      
        return lista.get(0);
    }
    public void DeletaPorNome(String aa1) {
        se = ConnectionFactory.getSessionFactory();
        Query qry = se.createSQLQuery("delete from Identidade where Ip_Imp = '" + aa1 + "'").addEntity(Identidade.class);
        lista = qry.list();
  
    }

    public List<Identidade> Listar() {
        se = ConnectionFactory.getSessionFactory();
        lista = se.createCriteria(Identidade.class).list();
        
            return lista;
    }
         public int Soma() {
        se = ConnectionFactory.getSessionFactory();
        Query qry = se.createSQLQuery("select * from Identidade").addEntity(Identidade.class);
        lista = qry.list();
        qry = null;
       
            se.close();
        return lista.size();
    }
}
