/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stoni_connect;

/**
 *
 * @author SAMSUNG
 */
public class Busca_Informacao extends Thread {

    private String nome_thread;
    private boolean status;
    private boolean status_force;
    public Busca_Informacao(String nome_Thread) {
        this.nome_thread = nome_Thread;
        this.status = true;
        this.status_force = true;
    
    }

    public void run() {
        while(status_force){
        if (status){
            while (status) {
                System.out.println("Aqui = " + nome_thread);
                try {
                    currentThread().setName(nome_thread);
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
            }
        }else{
            try {
                System.out.println("Thread Parada");    
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }
        System.out.println(
        Thread.currentThread().getName());
        Thread.interrupted();
    }

    public void stop_Thread() {
        this.status = false;
    }

    public void start_Thread() {
        this.status = true;
    }
    public void force_interrupt(){
        this.status_force = false;
        this.status = false;
    }
}
