/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller.facade;

import java.util.ArrayList;

/**
 *
 * @author Nikolas
 */
public class ReservaGrupo {
    private ArrayList<Reserva> componentes;

    public ReservaGrupo() {
        this.componentes = new ArrayList<>(); ;
    }
    
    public void AñadirGrupito(Reserva r){
        this.componentes.add(r);
    }
    
    public void EliminarAlGrupito(Reserva r){
    this.componentes.remove(r);
    }
    
    public void ModificarLugar(String id, String Nuevo_Lugar){
        for (int i = 0; i < this.componentes.size(); i++) {
            if(id.compareTo(this.componentes.get(i).getId())==0 ){
                this.componentes.get(i).setLugar(Nuevo_Lugar);
        }
            
        }
    }
public int CantidadReservas(){
return this.componentes.size();

}
    
   public String LeerReserva(String id){
         String nombre = "";
        for (int i = 0; i < this.componentes.size(); i++) {
            if(id.compareTo(this.componentes.get(i).getId())==0 ){
             nombre = this.componentes.get(i).getNombre();
             nombre = nombre +  this.componentes.get(i).getId();
             System.out.print(nombre);
           
        }
            
        }
        return nombre;
}
   public Reserva ObtenerReserva(String id){
         Reserva p = null;
         for (int i = 0; i < this.componentes.size(); i++) {
            if(id.compareTo(this.componentes.get(i).getId())==0 ){
             p= this.componentes.get(i);
        }
         }
         return p;
     }
}
