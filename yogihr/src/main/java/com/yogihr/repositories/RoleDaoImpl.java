package com.yogihr.repositories;

import com.yogihr.models.Role;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao{

    private EntityManager entityManager;

    @Autowired
    public RoleDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Role findRoleByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery("Select From Role where role = :roleName", Role.class);

        query.setParameter("roleName", name);

        Role theRole = null;
		
		try {
			theRole = query.getSingleResult();
		} catch (Exception e) {
			theRole = null;
		}
		
		return theRole;
    }
    
}
