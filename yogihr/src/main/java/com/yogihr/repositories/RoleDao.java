package com.yogihr.repositories;

import org.springframework.stereotype.Repository;

import com.yogihr.models.Role;



public interface RoleDao{

    Role findRoleByName(String string);

}
