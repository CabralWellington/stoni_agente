/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stoni_connect;

import java.math.BigInteger;
import java.util.Vector;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

/**
 *
 * @author Wellington
 */
public class PegaInformacao extends Thread{
    //Informacoes da conexao
    
    private static String  oidValue  = ".1.3.6.1.4.1.1347.47.6.1.1.4.2";

    private static String  oidValue2  = ".1.3.6.1.4.1.1347.47.6.1.1.8.1";
    private static String  oidValue3  = ".1.3.6.1.4.1.1347.43.10.1.1.12.1.1";
    private static String  oidValue4  = ".1.3.6.1.4.1.1347.47.6.1.1.4.1";
        private static String port = "161";

    private static int snmpVersion = SnmpConstants.version1;

    private static String community = "public";

    
    private static int contador ;
    private String ipAddress = "192.168.100.1";
    

    public void run(){
        //variaveis
        int i = 0;
        String aux = null;
        String aux2 = null;
        contador = 0;
       
        //----------------
        //Conexao
        try {
              do{
                
            
            // Criando a classe de transporte de dados
            TransportMapping transporte = new DefaultUdpTransportMapping();
            transporte.listen();
             // Create Target Address object
                CommunityTarget comtarget = new CommunityTarget();
                comtarget.setCommunity(new OctetString(community));
                comtarget.setVersion(snmpVersion);
                comtarget.setAddress(new UdpAddress(ipAddress + "/" + port));
                comtarget.setRetries(2);
                comtarget.setTimeout(1000);
                // Create the PDU object
                PDU pdu = new PDU();
                pdu.add(new VariableBinding(new OID(oidValue)));
                pdu.setType(PDU.GET);
                pdu.setRequestID(new Integer32(1));

                // Create Snmp object for sending data to Agent
                Snmp snmp = new Snmp(transporte);
                
                  
            
            // System.out.println("Sending Request to Agent...");
                ResponseEvent response = snmp.get(pdu, comtarget);

    // Process Agent Response
    if (response != null)
    {
     // System.out.println("Got Response from Agent");
                PDU responsePDU = response.getResponse();

      if (responsePDU != null)
      {
        int errorStatus = responsePDU.getErrorStatus();
        int errorIndex = responsePDU.getErrorIndex();
        String errorStatusText = responsePDU.getErrorStatusText();

        if (errorStatus == PDU.noError)
            
        {
             aux = responsePDU.getVariableBindings().toString();
             if (aux.equals(aux2)) {
                  
            }else{
                        
                        
                     
                        aux2 = aux;
                        System.out.println(
                         "Status da Impressora - " +
                         aux2.substring(aux.indexOf("=")+1,(aux.lastIndexOf("]"))).trim()
                         +"\nUsuario - " + pegaresto(oidValue2)+
                                 "\nContador-"+ pegaresto(oidValue3)
                                 
                         +"\nNome Documento" + pegaresto(oidValue4)
                         +"\n--------------------------------------------"
                                 );
            
                 
             }
            
        }
        else
        {
          System.out.println("Error: Request Failed");
          System.out.println("Error Status = " + errorStatus);
          System.out.println("Error Index = " + errorIndex);
          System.out.println("Error Status Text = " + errorStatusText);
        }
      }
      else
      {
        System.out.println("Error: Response PDU is null");
      }
    }
    else
    {
      System.out.println("Error: Agent Timeout... ");
    }
   snmp.close();
   
       // System.out.println(Thread.currentThread());
        Thread.sleep(1000);
                }while(i!=100);
                    

                   } catch (Exception e) {
        }
        
    }
    public String pegaresto (String OID){
        try {
            
        String aux = null;
        String aux2 = null;
       
                
            
            // Criando a classe de transporte de dados
            TransportMapping transporte = new DefaultUdpTransportMapping();
            transporte.listen();
             // Create Target Address object
                CommunityTarget comtarget = new CommunityTarget();
                comtarget.setCommunity(new OctetString(community));
                comtarget.setVersion(snmpVersion);
                comtarget.setAddress(new UdpAddress(ipAddress + "/" + port));
                comtarget.setRetries(2);
                comtarget.setTimeout(1000);
                
                // Create the PDU object
                PDU pdu = new PDU();
                pdu.add(new VariableBinding(new OID(OID)));
                pdu.setType(PDU.GET);
                pdu.setRequestID(new Integer32(1));
                
                // Create Snmp object for sending data to Agent
                Snmp snmp = new Snmp(transporte);
               
            // System.out.println("Sending Request to Agent...");
                ResponseEvent response = snmp.get(pdu, comtarget);
                
    // Process Agent Response
    if (response != null)
    {
     // System.out.println("Got Response from Agent");
                PDU responsePDU = response.getResponse();
      if (responsePDU != null)
      {
        int errorStatus = responsePDU.getErrorStatus();
        int errorIndex = responsePDU.getErrorIndex();
        String errorStatusText = responsePDU.getErrorStatusText();

        if (errorStatus == PDU.noError)
            
        {
             aux = responsePDU.getVariableBindings().toString();
            System.out.println(aux); 
        }
        else
        {
          System.out.println("Error: Request Failed");
          System.out.println("Error Status = " + errorStatus);
          System.out.println("Error Index = " + errorIndex);
          System.out.println("Error Status Text = " + errorStatusText);
        }
      }
      else
      {
        System.out.println("Error: Response PDU is null");
      }
    }
    else
    {
      System.out.println("Error: Agent Timeout... ");
    }
   snmp.close();
   
        return aux.substring(aux.indexOf("=")+1,(aux.lastIndexOf("]")));
}catch(Exception e){}
    return null;
    }


 
    
}
    
