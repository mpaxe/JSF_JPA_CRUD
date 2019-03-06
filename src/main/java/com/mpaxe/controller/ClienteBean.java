package com.mpaxe.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.mpaxe.dao.ClienteDAO;
import com.mpaxe.model.Cliente;


@ManagedBean (name="clienteBean")
@RequestScoped
public class ClienteBean {
	
	public List<Cliente> obtenerClientes() {
		return new ClienteDAO().obtenerClientes();
	}
	
	public String editar(Long id) {
		Cliente cliente = new ClienteDAO().buscar(id);
		
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("cliente", cliente);
		return "/faces/editar.xhtml";
	}
	
	public String actualizar(Cliente cliente) {
		ClienteDAO clienteDAO = new ClienteDAO();
		cliente.setFecActualizar(new Date());
		clienteDAO.editar(cliente);
		return "/faces/index.xhtml";
	}
	
	public String eliminar(Long id) {
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.eliminar(id);
		return "/faces/index.xhtml";
	}
	
	public String nuevo() {
		Cliente cliente = new Cliente();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("cliente", cliente);
		return "/faces/nuevo.xhtml";
	}
	
	public String guardar(Cliente cliente) {
		ClienteDAO clienteDAO = new ClienteDAO();
		cliente.setFecRegistro(new Date());
		clienteDAO.guardar(cliente);
		return "/faces/index.xhtml";
	}
}