package uniandes.cupi2.lucesApagadas.interfaz;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.cupi2.lucesApagadas.mundo.LucesApagadas;

import java.awt.*;
import java.io.File;


/**
 * Ventana principal de la aplicaci�n.
 */
public class InterfazLucesApagadas extends JFrame
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    
    /**
     * Clase principal del mundo.
     */
    private LucesApagadas lucesApagadas;
    
    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con la imagen del encabezado.
     */
    private PanelLogo panelLogo;
    
    /**
     * Panel con las extensiones.
     */
    private PanelOpciones panelOpciones;
    
    /**
     * Panel con las ventanas del juego.
     */
    private PanelJuego panelJuego;
    
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva interfaz. <br>
     * <b>post: </b> El objeto InterfazLucesApagadas est� inicializado.
     */
    public InterfazLucesApagadas()
    {
        setTitle( "Luces Apagadas" );
        setSize(805,630);
        this.setLocationRelativeTo( null );
        setResizable(false);
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLayout( new BorderLayout( )) ;
        
        panelLogo= new PanelLogo( );
        add(panelLogo, BorderLayout.NORTH);
        
        panelOpciones= new PanelOpciones( this );
        add(panelOpciones, BorderLayout.SOUTH);
        
        panelJuego = new PanelJuego( this );
        add(panelJuego, BorderLayout.CENTER);
    }
    
   
    
    
    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    
    /**
     * M�todo que retorna la informaci�n del juego.
     * @return La matriz del juego.
     * @throws Exception Si no se ha inicializado el juego. 
     */
    public String[][] configuracionJuego()throws Exception
    {
        if( lucesApagadas.darEstados( ) == null)
        {
            throw new Exception("No hay juego");
        }
        else
        {
            return lucesApagadas.darEstados( );
        }
    }
    
    
   
    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------
    
    
    
    /**
     * Carga la informci�n del juego y crea el mundo.
     * Si el archivo seleccionado no tiene formato Properties se muestra un mensaje de error.
     * Si no se selecciona ning� archivo, se muestra un mensaje de error.
     */
    public void cargarInformacion()
    {
        JFileChooser datos = new JFileChooser( "./data" );
        datos.setDialogTitle( "Abrir archivo de propiedades" );
        int resultado = datos.showOpenDialog( this );
        if( resultado != JFileChooser.APPROVE_OPTION )
        {
            JOptionPane.showMessageDialog( this, "Debe seleccionar un archivo de configuraci�n para poder jugar." , "Luces Apagadas.", JOptionPane.WARNING_MESSAGE );
        }
        else
        {
            File archivoluces = datos.getSelectedFile( );
            try
            {
                lucesApagadas = new LucesApagadas( archivoluces );
            }
            catch( Exception e )
            {
                lucesApagadas = null;
                JOptionPane.showMessageDialog( this, "El archivo no tiene el formato esperado." , "Error", JOptionPane.ERROR_MESSAGE );
                e.printStackTrace( );
            }
        }
        cargarJuego();
    }
    
    /**
     * Se reinicia el juego.
     * Si no existe un juego en curso se lanza una excepci�n.
     */
    public void cargarJuego(){
        try
        {
            panelJuego.cargarJuego( );
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,"No hay ningun juego en curso.","Reiniciar Juego",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Se muestran las estad�sticas del juego.
     * Si no existe un juego en curso, lanza una excepci�n.
     */
    public void Estadisticas()
    {
        try
        {
            String mensaje="";
            mensaje += "Cantidad de ventanas encendidas: " + panelJuego.contadorVentanasEncendidasTotal( )+"."+"\n";
            mensaje += "La fila con m�s ventanas encendidas: " + panelJuego.filaMasEncendida( )+".";
            
            JOptionPane.showMessageDialog(this,mensaje,"Estadisticas" ,JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,"No hay ning�n juego en curso.","Estad�sticas" ,JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * M�todo para la extensi�n 1.
     */
    public void reqFuncion1()
    {
        try
        {
            JOptionPane.showMessageDialog( this,lucesApagadas.metodo1( ),"Respuesta." ,JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,"Se necesita inicializar el mundo","Error" ,JOptionPane.ERROR_MESSAGE);  
        }
    }
    
    /**
     * M�todo para la extensi�n 2.
     */
    public void reqFuncion2()
    {
        try
        {
            JOptionPane.showMessageDialog( this,lucesApagadas.metodo2( ) ,"Respuesta." ,JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,"Se necesita inicializar el mundo","Error" ,JOptionPane.ERROR_MESSAGE);  
        }
    }
   
    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz.
     * @param args Argumentos del programa. args != null.
     */
    
    public static void main( String[] args )
    {
        try
        {
            InterfazLucesApagadas interfaz = new InterfazLucesApagadas( );
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
       
    }

}
