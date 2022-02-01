package uniandes.cupi2.lucesApagadas.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel de manejo de acciones.
 */
public class PanelOpciones extends JPanel implements ActionListener
{
    
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    
    /**
     * Constante que representa el comando cargar juego.
     */
    private static final String CARGAR = "Cargar";   
    
    /**
     * Constante que representa el comando reiniciar el juego.
     */
    private static final String REINICIAR= "Reiniciar"; 
    
    /**
     * Constante que representa el comando estadisticas del juego.
     */
    private static final String ESTADISTICAS= "Estadísticas";   
    
    /**
     * Constante que representa el comando para la opción 1.
     */
    private static final String OPCION1 = "Opción 1";
    
    /**
     * Constante que representa el comando para la opción 2.
     */
    private static final String OPCION2 = "Opción 2";
    
 // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación.
     */
    private InterfazLucesApagadas principal;
    
 // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------
    
    /**
     * Botón para cargar el juego.
     */
    private JButton btnCargar;
    
    /**
     * Botón para reiniciar el juego.
     */
    private JButton btnReiniciar;
    
    /**
     * Botón para mostrar las estadisticas del juego.
     */
    private JButton btnEstadisticas;
    
    /**
     * Botón para la opción 1.
     */
    private JButton btnOpcion1;
    
    /**
     * Botón para la opción 2.
     */
    private JButton btnOpcion2;
    
   
 // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    
    /**
     * Constructor del panel con las acciones del programa.
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null.
     */
    public PanelOpciones(InterfazLucesApagadas pPrincipal)
    {
        principal = pPrincipal;
        setBorder( new TitledBorder( "Opciones" ) );
        setLayout( new GridLayout(1,5) );
        btnCargar = new JButton( "Cargar");
        btnCargar.setActionCommand( CARGAR );
        btnCargar.addActionListener( this );
        add(btnCargar);
        
        btnReiniciar = new JButton( "Reiniciar");
        btnReiniciar.setActionCommand(REINICIAR );
        btnReiniciar.addActionListener( this );
        add(btnReiniciar);
        
        btnEstadisticas =new JButton( "Estadísticas");
        btnEstadisticas.setActionCommand(ESTADISTICAS );
        btnEstadisticas.addActionListener( this );
        add(btnEstadisticas);
        
        btnOpcion1= new JButton("Opción 1" );
        btnOpcion1.setActionCommand( OPCION1 );
        btnOpcion1.addActionListener( this );
        add(btnOpcion1);
        
        btnOpcion2= new JButton("Opción 2" );
        btnOpcion2.setActionCommand( OPCION2 );
        btnOpcion2.addActionListener( this );
        
        add(btnOpcion2);
        
    }
   
   
    
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones.
     * @param arg0 Acción que generó el evento. pEvento != null.
     */
    public void actionPerformed( ActionEvent arg0 )
    {
        String evento = arg0.getActionCommand( );
        if(evento.equals(CARGAR))
        {
            principal.cargarInformacion( );
        }
        if(evento.equals(REINICIAR))
        {
            principal.cargarJuego( );  
        }
        if(evento.equals(ESTADISTICAS))
        {
            principal.Estadisticas( ); 
        }
        if(evento.equals(OPCION1))
        {
            principal.reqFuncion1( );
        }
        if(evento.equals(OPCION2))
        {
            principal.reqFuncion2( );
        }
    }

}