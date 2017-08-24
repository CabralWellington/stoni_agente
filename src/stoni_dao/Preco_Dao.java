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
import stoni_bean.Preco;
import stoni_connect.ConnectionFactory;

/**
 *
 * @author SAMSUNG
 */
public class Preco_Dao {
               Session se;
        Transaction tx;
    List<Preco> lista = new ArrayList<Preco>();

    public void Salvar(Preco aa1) {
      
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

    public void Alterar(Preco aa1) {
        se = ConnectionFactory.getSessionFactory();
        tx = se.beginTransaction();
        se.update(aa1);
        tx.commit();
     
            
        se.close();
        }

    public void Deletar(Preco aa1) throws HibernateException{
            se = ConnectionFactory.getSessionFactory();
            tx = se.beginTransaction();
            se.delete(aa1);
            tx.commit();
    
            
            se.close();
    }

    public Preco BuscarPorId(String aa1) {
        se = ConnectionFactory.getSessionFactory();
        Query qry = se.createSQLQuery("select * from Preco where Ip_Imp = '" + aa1 + "'").addEntity(Preco.class);
        lista = qry.list();
      
            
        return lista.get(0);
    }

    public Preco BuscarPorNome(String aa1) {
        se = ConnectionFactory.getSessionFactory();
        Query qry = se.createSQLQuery("select * from Preco where Nome_Imp = '" + aa1 + "'").addEntity(Preco.class);
        lista = qry.list();
      
        return lista.get(0);
    }
    public void DeletaPorNome(String aa1) {
        se = ConnectionFactory.getSessionFactory();
        Query qry = se.createSQLQuery("delete from Preco where Ip_Imp = '" + aa1 + "'").addEntity(Preco.class);
        lista = qry.list();
  
    }

    public List<Preco> Listar() {
        se = ConnectionFactory.getSessionFactory();
        lista = se.createCriteria(Preco.class).list();
        
            return lista;
    }
         public int Soma() {
        se = ConnectionFactory.getSessionFactory();
        Query qry = se.createSQLQuery("select * from Preco").addEntity(Preco.class);
        lista = qry.list();
        qry = null;
       
            se.close();
        return lista.size();
    }
}
