/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stoni_controller;

import java.util.ArrayList;
import java.util.List;
import stoni_bean.Impressao;

/**
 *
 * @author SAMSUNG
 */
public class Filadeimpressao {
    private ArrayList<Impressao> fila;
    
    
    public void insereFila(List<Impressao> insere){
        System.out.println((insere.size()-1));
        
        for (Impressao impressao : insere) {
            System.out.println(insere.toString());
        }
        for (int i = (insere.size()-1); i == 0; i--){
            if(!this.fila.contains(insere.get(i))){
                System.out.println("Inserido na fila = " + insere.get(i).toString());
                this.fila.add(insere.get(i));
            }
        }
        if (this.fila.size()>(insere.size()+1)){
            System.out.println("removido da fila = " + this.fila.get(0));
            this.fila.remove(0);
        }
    }

}
