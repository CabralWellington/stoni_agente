/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stoni_agente;

import java.util.ArrayList;
import java.util.List;
import org.snmp4j.PDU;
import stoni_bean.Impressao;
import stoni_bean.Tem;
import stoni_connect.Busca_Informacao;
import stoni_connect.PegaInformacao;
import stoni_connect.ConectaRede;
import stoni_controller.Filadeimpressao;
import stoni_dao.Impressao_Dao;
import stoni_dao.PDU_Dao;
import stoni_dao.Tem_Dao;

/**
 *
 * @author SAMSUNG
 */
public class app {
public static String ID_Systema = "ADSA_ASDA";
    

     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        Filadeimpressao fila = new Filadeimpressao();
        List<Impressao> listatemp;
        Tem_Dao temdao = new Tem_Dao();
        Tem tem = temdao.Listar().get(0);
        ConectaRede conectaRede = new ConectaRede();
        PDU pdu;
        PDU_Dao pdud;
        Impressao_Dao impredao = new Impressao_Dao();
        while(true){
        pdud = new PDU_Dao();
        pdu = pdud.conversor(tem);
        pdu = conectaRede.BuscarOid(pdu, tem.getImpressora().getIpImpressora());
        listatemp = impredao.pduToImpressao(pdu, tem);
        fila.insereFila(listatemp);
        listatemp.removeAll(listatemp);
        pdu.clear();
        Thread.sleep(25000);
       }
    }
}
