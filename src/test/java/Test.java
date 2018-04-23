
import co.edu.ufps.sitioadministrable.model.DAO.conexion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eliza
 */
public class Test {
    public static void main(String[] args) {
        try{conexion.generarConexion();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
