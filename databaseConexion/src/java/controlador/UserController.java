/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.UsuarioDAO;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Usuario;

@Named(value = "UserController")
@SessionScoped

public class UserController implements Serializable {

    private Usuario usuario;
    private Usuario usuarioAutenticado = null;
    List<Usuario> listado;

    @EJB
    private UsuarioDAO ejbDao;

    public UserController() {
        usuario = new Usuario();

    }

    public void login() throws IOException {

        FacesContext ctx = FacesContext.getCurrentInstance();
        
         usuarioAutenticado = ejbDao.encontrarUsuarioPorLogin(usuario.getCorreo(), usuario.getClave());

        if (usuarioAutenticado != null) {
           
            ctx.getExternalContext().redirect("home.xhtml");
        } else {
            
            ctx.getExternalContext().redirect("index.xhtml");
        }

    }
    
    
    public List<Usuario> getListado(){
        listado = ejbDao.listar();
        return listado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
}
