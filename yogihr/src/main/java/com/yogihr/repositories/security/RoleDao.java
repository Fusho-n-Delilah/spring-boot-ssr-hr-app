package com.yogihr.repositories.security;

import com.yogihr.models.security.Role;



public interface RoleDao{

    Role findRoleByName(String string);

}
