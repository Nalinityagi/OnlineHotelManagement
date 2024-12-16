package com.hotelbooking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotelbooking.entity.Role;
import com.hotelbooking.entity.User;
import com.hotelbooking.exception.RoleAlreadyExistException;
import com.hotelbooking.service.IRoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
	private final IRoleService roleService = null;

	@GetMapping("/all-roles")
	public ResponseEntity<List<Role>> getAllRoles() {
		return new ResponseEntity<>(roleService.getRoles(), HttpStatus.FOUND);
	}

	@PostMapping("/create-new-role")
	public ResponseEntity<String> createRole(@RequestBody Role theRole) {
		try {
			roleService.createRole(theRole);
			return ResponseEntity.ok("New role created successfully!");
		} catch (RoleAlreadyExistException re) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(re.getMessage());
		}
	}

	@DeleteMapping("/delete/{roleId}")
	public void deleteRole(@PathVariable Long roleId) {
		roleService.deleteRole(roleId);
	}

	@PostMapping("/remove-all-users-from-role/{roleId}")
	public Role removeAllUsersFromRole(@PathVariable Long roleId) {
		return roleService.removeAllUserFromRole(roleId);
	}

	@PostMapping("/remove-user-from-role")
	public User removeUserFromRole(
			@RequestParam Long userId,
			@RequestParam Long roleId) {
		return roleService.removeUserFromRole(userId, roleId);
	}

	@PostMapping("/assign-user-to-role")
	public User assignUserToRole(
			@RequestParam Long userId,
			@RequestParam Long roleId) {
		return roleService.assignRoleToUser(userId, roleId);
	}
}