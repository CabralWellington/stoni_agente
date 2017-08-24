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
import stoni_bean.Tem;
import stoni_connect.ConnectionFactory;

/**
 *
 * @author SAMSUNG
 */
public class Tem_Dao {
               Session se;
        Transaction tx;
    List<Tem> lista = new ArrayList<Tem>();

    public void Salvar(Tem aa1) {
      
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

    public void Alterar(Tem aa1) {
        se = ConnectionFactory.getSessionFactory();
        tx = se.beginTransaction();
        se.update(aa1);
        tx.commit();
     
            
        se.close();
        }

    public void Deletar(Tem aa1) throws HibernateException{
            se = ConnectionFactory.getSessionFactory();
            tx = se.beginTransaction();
            se.delete(aa1);
            tx.commit();
    
            
            se.close();
    }

    public Tem BuscarImpressoraPorId(Integer aa1) {
        se = ConnectionFactory.getSessionFactory();
        Query qry = se.createSQLQuery("select * from Tem where ID_Impressora = '" + aa1 + "'").addEntity(Tem.class);
        lista = qry.list();
      
            
        return lista.get(0);
    }

    public Tem BuscarPorNome(String aa1) {
        se = ConnectionFactory.getSessionFactory();
        Query qry = se.createSQLQuery("select * from Tem where Nome_Imp = '" + aa1 + "'").addEntity(Tem.class);
        lista = qry.list();
      
        return lista.get(0);
    }
    public void DeletaPorNome(String aa1) {
        se = ConnectionFactory.getSessionFactory();
        Query qry = se.createSQLQuery("delete from Tem where Ip_Imp = '" + aa1 + "'").addEntity(Tem.class);
        lista = qry.list();
  
    }

    public List<Tem> Listar() {
        se = ConnectionFactory.getSessionFactory();
        lista = se.createCriteria(Tem.class).list();
        
            return lista;
    }
         public int Soma() {
        se = ConnectionFactory.getSessionFactory();
        Query qry = se.createSQLQuery("select * from Tem").addEntity(Tem.class);
        lista = qry.list();
        qry = null;
       
            se.close();
        return lista.size();
    }
}
