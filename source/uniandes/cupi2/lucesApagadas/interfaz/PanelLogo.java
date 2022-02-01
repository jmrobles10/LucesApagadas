package uniandes.cupi2.lucesApagadas.interfaz;

import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Clase donde se coloca la imagen encabezado.
 */
public class PanelLogo extends JPanel
{
    /**
     * Etiqueta con la imagen del encabezado.
     */
    private JLabel lblImagen;
    
    /**
     * Método constructor por defecto. Coloca la imagen del encabezado de la aplicación.
     */
    public PanelLogo()
    {
        setLayout( new GridLayout( 1, 1 ) );
        setSize( 630, 180 );
        lblImagen = new JLabel();
        lblImagen.setIcon( new ImageIcon( "./data/imagenes/titulo.jpg") );
        add(lblImagen);
    }
    
}
 