/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stoni_controller;

import java.util.List;
import stoni_bean.Impressao;

/**
 *
 * @author SAMSUNG
 */
public class Filadeimpressao {
    private List<Impressao> fila;
    
    
    public void insereFila(List<Impressao> insere){
        for (int i = insere.size(); i > 0; i--) {
            if(!this.fila.contains(insere.get(i))){
                this.fila.add(insere.get(i));
            }
        }
        
    }

}
