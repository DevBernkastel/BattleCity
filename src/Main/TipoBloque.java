 /*************************************************************
 * ITM - Departamente de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 *
 * Nombre: Oscar Hernan Chan Tun
 * Fecha: 25 de Octubre del 2020
 * Hora: ##:## AM
 *
 * Projecto: BattleCity
 * Paquete: Main
 * Archivo: TipoBloque
 * Descripción: Clase enum para los bloques de todo el mapa
 *
 *************************************************************/

package Main;

public enum TipoBloque 
{
    VACIO(0), LADRILLO(1), METAL(2), BASE(3), AGUA(4), ARBUSTO(5), BORDE(6), TANQUE(7),
    ESTRELLA(8), BOMBA(9), RELOJ(10), PALA(11), ESCUDO(12);
    
    private final int valor;
    
    private TipoBloque(int valor)
    {
        this.valor = valor;
    }
    
    public int getValue() 
    {
        return valor;
    }
    
    public static TipoBloque getTypeFromInt(int valor) 
    {
        return TipoBloque.values()[valor];
    }
}