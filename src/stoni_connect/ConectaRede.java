/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stoni_connect;

import java.util.ArrayList;
import java.util.List;
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

import stoni_bean.Impressao;
import stoni_bean.Impressora;
import stoni_bean.Tem;

/**
 *
 * @author SAMSUNG
 */
public class ConectaRede {

    private static String port = "161";
    private static int snmpVersion = SnmpConstants.version1;
    private static String community = "public";

    public PDU BuscarOid(PDU pdu, String IP) {
        try {
            // Criando a classe de transporte de dados
            TransportMapping transporte = new DefaultUdpTransportMapping();
            transporte.listen();
            // Create Target Address object
            CommunityTarget comtarget = new CommunityTarget();
            comtarget.setCommunity(new OctetString(community));
            comtarget.setVersion(snmpVersion);
            comtarget.setAddress(new UdpAddress(IP + "/" + port));
            comtarget.setRetries(2);
            comtarget.setTimeout(10000);

            // Create the PDU object
            //PDU pdu = new PDU();
            //pdu.setType(PDU.GET);
            //pdu.setRequestID(new Integer32(1));

            // Create Snmp object for sending data to Agent
            Snmp snmp = new Snmp(transporte);

            //System.out.println("Sending Request to Agent...");
            ResponseEvent response = snmp.get(pdu, comtarget);

            // Process Agent Response
            if (response != null) {
               // System.out.println("Got Response from Agent");
                PDU responsePDU = response.getResponse();
                if (responsePDU != null) {
                    int errorStatus = responsePDU.getErrorStatus();
                    int errorIndex = responsePDU.getErrorIndex();
                    String errorStatusText = responsePDU.getErrorStatusText();

                    if (errorStatus == PDU.noError) {
                        return responsePDU;
                    } else {
                        System.out.println("Error: Request Failed");
                        System.out.println("Error Status = " + errorStatus);
                        System.out.println("Error Index = " + errorIndex);
                        System.out.println("Error Status Text = " + errorStatusText);
                    }
                } else {
                    System.out.println("Error: Response PDU is null");
                    snmp.close();
                }
            } else {
                System.out.println("Error: Agent Timeout... ");
            }
            snmp.close();

            //   return aux.substring(aux.indexOf("=")+1,(aux.lastIndexOf("]")));
        } catch (Exception e) {
        }
        return null;
    }

    public String MudaHexa(String aa1) {
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
}
