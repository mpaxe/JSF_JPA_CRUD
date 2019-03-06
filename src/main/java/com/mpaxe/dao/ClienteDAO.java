package com.mpaxe.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.mpaxe.model.Cliente;
import com.mpaxe.model.JPAUtil;

public class ClienteDAO {
	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	
	// Guardar cliente
	public void guardar(Cliente cliente) {
		entity.getTransaction().begin();
		entity.persist(cliente);
		entity.getTransaction().commit();
		//JPAUtil.shutdown();
	}
	
	// Editar cliente
	public void editar(Cliente cliente) {
		entity.getTransaction().begin();
		entity.merge(cliente);
		entity.getTransaction().commit();
		//JPAUtil.shutdown();
	}
	
	// Buscar cliente 
	public Cliente buscar(Long id) {
		Cliente c = new Cliente();
		c = entity.find(Cliente.class, id);
		//JPAUtil.shutdown();
		return c;
	}
	
	// Eliminar cliente
	public void eliminar(Long id) {
		Cliente cliente = entity.find(Cliente.class, id);
		entity.getTransaction().begin();
		entity.remove(cliente);
		entity.getTransaction().commit();
	}
	
	// Obtener todos los clientes
	public List<Cliente> obtenerClientes(){
		List<Cliente> listaCliente = new ArrayList<Cliente>();
		Query q = entity.createQuery("SELECT c FROM Cliente c");
		listaCliente = q.getResultList();
		return listaCliente;
	}
}
