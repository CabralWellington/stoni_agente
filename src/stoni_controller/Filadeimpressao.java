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
import stoni_dao.Impressao_Dao;

/**
 *
 * @author SAMSUNG
 */
public class Filadeimpressao {

    private List<Impressao> filadeimpressao;
    // private  List<Impressao> filaparasalvar;

    public void trataFila(List<Impressao> insere) {
        for (int i = (insere.size() - 1); i > 0; i--) {
            if (naoContemNaFila(insere.get(i))) {
                System.out.println("Nao contem");
                insere(insere.get(i));
            }
            

        }
        //if certo para contagem   
        // if (this.filadeimpressao.size()>(insere.size()+1)){
        
        //if para testeS
        if (this.filadeimpressao.size()>(insere.size()-2)){
            salvaPrimeiro();
            removeFila();
        }
    }

    public Filadeimpressao() {
        this.filadeimpressao = new ArrayList<Impressao>();
        //this.filaparasalvar = new ArrayList<Impressao>();
    }

    private void insere(Impressao insert) {
        this.filadeimpressao.add(insert);
    }

    private void removeFila() {
        this.filadeimpressao.remove(0);
    }

    private boolean naoContemNaFila(Impressao contem) {
        return !this.filadeimpressao.contains(contem);
    }

    private void salvaPrimeiro() {
        Impressao_Dao salvaImpressao = new Impressao_Dao();
        Impressao impressao = new Impressao();
        impressao = this.filadeimpressao.get(retornaSize());
        
        System.out.println(impressao.toString());
        //salvaImpressao.Salvar(this.filadeimpressao.get(retornaSize()));
        //System.out.println(this.filadeimpressao.get(retornaSize()));
        
    }

    private int retornaSize() {
        return (this.filadeimpressao.size() - 1);
    }

}
