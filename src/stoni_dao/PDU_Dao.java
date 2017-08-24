/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stoni_dao;

import org.snmp4j.PDU;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;
import stoni_bean.Tem;

/**
 *
 * @author SAMSUNG
 */
public class PDU_Dao {
    public PDU conversor(Tem tem){
        try {
        PDU pdu = new PDU();
            for (int i = 1; i < 17; i++) {
            pdu.add(new VariableBinding(new OID((tem.getEquipamento().getJobNameEquipamento()+ (String.valueOf(i))))));
            pdu.add(new VariableBinding(new OID((tem.getEquipamento().getJobPagecountEquipamento()+(String.valueOf(i))))));
            pdu.add(new VariableBinding(new OID((tem.getEquipamento().getJobTypeEquipamento()+(String.valueOf(i))))));
            pdu.add(new VariableBinding(new OID((tem.getEquipamento().getJobUsernameEquipmaneto()+(String.valueOf(i))))));
            pdu.add(new VariableBinding(new OID((tem.getEquipamento().getJobDateEquipamento()+(String.valueOf(i))))));
            }
        pdu.setType(PDU.GET);
        pdu.setRequestID(new Integer32(1));
        return pdu;    
        } catch (Exception e) {
            System.out.println("erro no conversor");
            return null;
        }
        
    }
}
