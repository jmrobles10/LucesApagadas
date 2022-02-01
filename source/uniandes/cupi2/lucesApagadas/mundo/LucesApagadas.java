package uniandes.cupi2.lucesApagadas.mundo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * Clase que representa el CupiCastillo.
 */
public class LucesApagadas
{
    
    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------
   
    /**
     * Constante que representa el alto del mundo.
     */
    public final static int ALTO =7 ;
    
    /**
     * Constante que representa el ancho del mundo.
     */
    public final static int ANCHO = 7;
    
    /**
     * Arreglo de ventanas del CupiCastillo en dos dimensiones.
     */
    private String venatana[][];
    
    // -------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------

    /**
     * Crea un nuevo juego lucesApagadas a partir de un archivo.<br>
     * <b>post</b> Se inicializ� el arreglo de ventanas a partir de un archivo. <br>
     * @param arch Se ingresa como parametro el archivo del que se desea cargar el juego.
     * @throws Exception Si ocurre un error al cargar el archivo con la informaci�n del juego.
     */
    public  LucesApagadas(File arch ) throws Exception
    {
        Properties pDatos = new Properties( );
        FileInputStream data = new FileInputStream( arch );
        try
        {
            pDatos.load( data );
            data.close( );
        }
        catch( Exception e )
        {
            throw new Exception( "Formato inv�lido" );
        }
        
        venatana = new String [ALTO][ANCHO];
       
        for(int i = 0 ; i <ALTO; i++ )
        {
            String luces =  pDatos.getProperty(  "cupiCastillo.fila" + (i+1)); 
            for(int  l= 0; l<ANCHO; l++)
            {
                venatana[i][l]= luces.charAt( l )+"";
            }
        }
    }


    // -------------------------------------------------------------
    // M�todos
    // -------------------------------------------------------------

    /**
     * M�todoq ue retorna el arreglo de ventanas.
     * @return estado El arreglo de las ventanas.
     */
    public String[][] darEstados()
    {
        return venatana;
    }
    
    // -----------------------------------------------------------------
    // M�todos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1.
     * @return respuesta1.
     */
    public String metodo1()
    {
        return "Opci�n 1";
    }
    
    /**
     * M�todo para la extensi�n 2.
     * @return respuesta2.
     */
    public String metodo2()
    {
        return "Opci�n 2";
    }
}
