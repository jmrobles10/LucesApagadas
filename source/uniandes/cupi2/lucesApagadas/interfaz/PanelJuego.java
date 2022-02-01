package uniandes.cupi2.lucesApagadas.interfaz;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel con el tablero de juego.
 */
public class PanelJuego extends JPanel implements ActionListener
{
 // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que representa el ancho del tablero.
     */
    public final static int ANCHO = 7;
    
    /**
     * Constante que representa el alto del tablero.
     */
    public final static int ALTO =7 ;
    
    /**
     * Constante que representa el comando para cambiar el estado de las ventanas.
     */
    public final static String JUGAR = "jugar";
    
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
     * Botón que representa cada botón del tablero.
     */
    private JButton btnVentanas[][];
    
    /**
     * Etiqueta para las filas del tablero.
     */
    private JLabel lblFilas;
    
    /**
     * Etiqueta para las columnas del tablero.
     */
    private JLabel lblColumnas;
    
    /**
     * Imagen que representa las ventanas encendidas.
     */
    private ImageIcon ventanaEncendida;
    
    /**
     * Imagen que representa las ventanas apagadas.
     */
    private ImageIcon ventanaApagada;
    
    /**
     * Imagen que representa las ventanas encendidas con fantasma.
     */
    private ImageIcon ventanaEncendidaFantasma;
    
    /**
     * Imagen que representa las ventanas apagadas con fantasma.
     */
    private ImageIcon ventanaApagadaFantasma;
    
    
    private int columna = 0;
    
    
    private int fila = 0;

    
 // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel con el tablero de juego.
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null.
     */
    public PanelJuego(InterfazLucesApagadas pPrincipal)
    {
       
        setLayout( new GridLayout( ALTO,ANCHO) );
       
        principal = pPrincipal;
        btnVentanas = new JButton[ALTO][ANCHO];
    
        ventanaEncendida = new ImageIcon("./data/imagenes/ventana_encendida.png");
        ventanaApagada = new ImageIcon("./data/imagenes/ventana_apagada.png");
        ventanaEncendidaFantasma = new ImageIcon("./data/imagenes/ventana_encendida_fantasma.png");
        ventanaApagadaFantasma = new ImageIcon("./data/imagenes/ventana_apagada_fantasma.png");
        
        for(int i=0; i<ALTO;++i)
        {
            for(int j=0; j<ANCHO;++j)
            {
                btnVentanas[i][j]= new JButton();
                btnVentanas[i][j].setSize( 90 , 60);
                btnVentanas [i] [j].setBackground(new Color(46,32,210));
                btnVentanas[i][j].setActionCommand(JUGAR );
                btnVentanas[i][j].addActionListener( this );
                add(btnVentanas[i][j]);
            }
        }
        setVisible(false);
    }

    /**
     * Carga el juego, lo muestra y asigana la correspondente imagen a cada casilla.
     * @throws Exception si no se ha seleccionado ningún archivo, se muestra un mensaje indicando que no hay juegos en curso.
     */
    public void cargarJuego() throws Exception
    {
        if(principal.configuracionJuego( )!= null)
        {
            for(int i=0; i<ALTO;++i)
            {
                for(int j=0; j<ANCHO;++j)
                {
                    if(principal.configuracionJuego( )[i][j].equalsIgnoreCase("0"))
                    {
                        btnVentanas[i][j].setIcon(ventanaApagada);
                    }
                    if(principal.configuracionJuego( )[i][j].equalsIgnoreCase("1"))
                    {
                        btnVentanas[i][j].setIcon(ventanaEncendida);
                    }
                }
            }
        }
        else
        {
            throw new Exception("No hay ningun juego en curso");
        }
        setVisible(true);
    }
      
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    
    /**
     * Método que retorna el númerod e ventanas encendidas en el juego.
     * @return Número de ventanas encendidas.
     */
    public int contadorVentanasEncendidasTotal()
    {
       int contador = 0;
       for(int i = 0; i < ALTO; ++i)
       {
           for(int j=0; j < ANCHO; ++j)
           { 
              if(btnVentanas[i][j].getIcon().equals( ventanaEncendida ))
              {
                  contador++;
              }
           }
       }
       return contador;
    }
    
    /**
     * Método que retorna la cantidad de luces encendidas que hay en cada fila
     * @param pFila Que indica el número de la fila que se está evaluando.
     * @return el número de luces encendidas por cada fila.
     */
    public int contadorVentanasEncendidasPorFila(int pFila)
    {
        int contador=0;
        for(int j = 0; j < ANCHO; ++j)
        {
            if(btnVentanas[pFila][j].getIcon().equals( ventanaEncendida )){
                contador++;
            }
        }
        return contador;
    }
    
    /**
     * Método que retorna la fila con mas luces encendidas.
     * @return la fila con mayor número de luces encendidas.
     */
    public int filaMasEncendida()
    {
        int mayor = 0;
        int fila = 0;
        for(int i=0; i < ALTO;++i)
        {
           if(contadorVentanasEncendidasPorFila( i ) > mayor)
           {
               mayor= contadorVentanasEncendidasPorFila( i );
               fila = i;
           }
        }
    return fila;
    } 

    
    /**
     * Método que cambia el estado de la ventana que se presiona e inmediatamente el estado de las ventanas que se encuentran arriba,
     * abajo, a la derecha y a la izquiera de esta.
     * Si la ventana está encendida su estado cambia a apagada.
     */
    public void cambiarEstado()
    {
        int i= fila;
        int j=columna;
       
        if(!(i-1 < 0))
        {
            if(btnVentanas[i-1][j].getIcon( ).equals(ventanaEncendida))    
            {
                btnVentanas[i-1][j].setIcon(ventanaApagada) ;  
            }
            else 
            {
                btnVentanas[i-1][j].setIcon(ventanaEncendida);
            }
        }
        if(!(j-1 < 0))
        {
            if(btnVentanas[i][j-1].getIcon( ).equals(ventanaEncendida))   
            {
                btnVentanas[i][j-1].setIcon(ventanaApagada) ;  
            }
            else 
            {
                btnVentanas[i][j-1].setIcon(ventanaEncendida);
            }
        }
        if(!(j+1 > ANCHO-1))
        {
            if(btnVentanas[i][j+1].getIcon( ).equals(ventanaEncendida))  
            {
                btnVentanas[i][j+1].setIcon(ventanaApagada) ;  
            }
            else 
            {
                btnVentanas[i][j+1].setIcon(ventanaEncendida);
            }
        }
        if(!(i+1 > ALTO-1))
        {
            if(btnVentanas[i+1][j].getIcon( ).equals(ventanaEncendida))   
            {
                btnVentanas[i+1][j].setIcon(ventanaApagada) ;  
            }
            else 
            {
                btnVentanas[i+1][j].setIcon(ventanaEncendida);
            }
        }
    }
    
    
   
    /**
     * Manejo de los eventos de los botones.
     * @param e Acción que generó el evento. pEvento != null.
     */
    public void actionPerformed( ActionEvent e )
    {
        for(int i=0; i < ALTO ; ++i)
        {
            for(int j=0; j < ANCHO; ++j)
            {
                if(e.getSource( ).equals(btnVentanas[i][j]))
                {
                    if(btnVentanas[i][j].getIcon().equals(ventanaApagadaFantasma))
                    {
                        btnVentanas[i][j].setIcon(ventanaEncendidaFantasma);
                    }
                    if(btnVentanas[i][j].getIcon().equals(ventanaEncendidaFantasma))
                    {
                        btnVentanas[i][j].setIcon(ventanaApagadaFantasma);
                    }
                    if(btnVentanas[i][j].getIcon().equals(ventanaEncendida))
                    {
                        btnVentanas[i][j].setIcon(ventanaApagadaFantasma);
                    }
                    if(btnVentanas[i][j].getIcon().equals(ventanaApagada))
                    {
                        btnVentanas[i][j].setIcon(ventanaEncendidaFantasma);
                    }
 
                    fila=i;
                    columna =j;
                    cambiarEstado( );
       
                    for(int f=0; f < ALTO ; ++f)
                    {
                        for(int c=0; c < ANCHO; ++c)
                        {
                            if(!btnVentanas[fila][columna].equals( btnVentanas [f][c]))
                            {
                                if(btnVentanas[f][c].getIcon().equals(ventanaApagadaFantasma))
                                {
                                    btnVentanas[f][c].setIcon(ventanaApagada);
                                }
                                if(btnVentanas[f][c].getIcon().equals(ventanaEncendidaFantasma))
                                {
                                    btnVentanas[f][c].setIcon(ventanaEncendida);
                                }
                            }
                        }
                    }
                    if(contadorVentanasEncendidasTotal( )==0)
                    {
                        JOptionPane.showMessageDialog( principal, "¡Felicitaciones!" +"\n"+ "Ganó el Juego.","Felicitaciones",JOptionPane.INFORMATION_MESSAGE );
                    }
                }
            }
        }
    }
}
