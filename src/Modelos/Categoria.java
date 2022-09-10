package Modelos;

public class Categoria {
    
    public String Id_categoria;
    public String Categoria_principal;
    public String Sub_categoria;
    
    public Categoria(){
    }
    
    public Categoria(String id_categoria, String categoria_principal, String sub_categoria){
        
        super();
        Id_categoria=id_categoria;
        Categoria_principal=categoria_principal;
        Sub_categoria=sub_categoria;
        
    }
    
}
