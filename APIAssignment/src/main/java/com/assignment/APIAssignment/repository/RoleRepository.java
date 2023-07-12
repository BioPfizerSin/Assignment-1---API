package com.assignment.APIAssignment.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assignment.APIAssignment.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
