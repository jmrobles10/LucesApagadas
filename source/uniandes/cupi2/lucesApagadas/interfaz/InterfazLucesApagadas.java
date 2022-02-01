package uniandes.cupi2.lucesApagadas.interfaz;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.cupi2.lucesApagadas.mundo.LucesApagadas;

import java.awt.*;
import java.io.File;


/**
 * Ventana principal de la aplicación.
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
     * <b>post: </b> El objeto InterfazLucesApagadas está inicializado.
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
    // Métodos
    // -----------------------------------------------------------------

    
    /**
     * Método que retorna la información del juego.
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
    // Puntos de Extensión
    // -----------------------------------------------------------------
    
    
    
    /**
     * Carga la informción del juego y crea el mundo.
     * Si el archivo seleccionado no tiene formato Properties se muestra un mensaje de error.
     * Si no se selecciona ningú archivo, se muestra un mensaje de error.
     */
    public void cargarInformacion()
    {
        JFileChooser datos = new JFileChooser( "./data" );
        datos.setDialogTitle( "Abrir archivo de propiedades" );
        int resultado = datos.showOpenDialog( this );
        if( resultado != JFileChooser.APPROVE_OPTION )
        {
            JOptionPane.showMessageDialog( this, "Debe seleccionar un archivo de configuración para poder jugar." , "Luces Apagadas.", JOptionPane.WARNING_MESSAGE );
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
     * Si no existe un juego en curso se lanza una excepción.
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
     * Se muestran las estadísticas del juego.
     * Si no existe un juego en curso, lanza una excepción.
     */
    public void Estadisticas()
    {
        try
        {
            String mensaje="";
            mensaje += "Cantidad de ventanas encendidas: " + panelJuego.contadorVentanasEncendidasTotal( )+"."+"\n";
            mensaje += "La fila con más ventanas encendidas: " + panelJuego.filaMasEncendida( )+".";
            
            JOptionPane.showMessageDialog(this,mensaje,"Estadisticas" ,JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,"No hay ningún juego en curso.","Estadísticas" ,JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Método para la extensión 1.
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
     * Método para la extensión 2.
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
     * Este método ejecuta la aplicación, creando una nueva interfaz.
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
