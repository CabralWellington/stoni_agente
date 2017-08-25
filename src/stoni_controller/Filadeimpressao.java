/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stoni_controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import stoni_bean.Impressao;

/**
 *
 * @author SAMSUNG
 */
public class Filadeimpressao {
    private  List<Impressao> filadeimpressao = new ArrayList<Impressao>();
    
    
    
    public void insereFila(List<Impressao> insere){
       for (int i = (insere.size()-1); i > 0 ; i--){
           if (!this.filadeimpressao.contains(insere.get(i))) {
                   this.filadeimpressao.add(insere.get(i));
                   System.out.println(insere.get(i));    
           }
       }
        }
    /*    if (this.fila.size()>(insere.size()+1)){
            System.out.println("removido da fila = " + this.fila.get(0));
            this.fila.remove(0);
        }*/
    //}

}
