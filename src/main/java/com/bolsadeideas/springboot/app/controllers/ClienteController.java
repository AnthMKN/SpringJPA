package com.bolsadeideas.springboot.app.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.dao.RolRepository;
import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.entity.Rol;
import com.bolsadeideas.springboot.app.models.service.IClienteService;
import com.bolsadeideas.springboot.app.models.service.IUploadFileService;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	@Autowired
	private RolRepository rolRepository;
	
	private final Logger log= LoggerFactory.getLogger(getClass());
	
	private final static String UPLOADS_FOLDER = "uploads";
	
	@GetMapping(value="/uploads/{fileName:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String fileName){
		
		Resource recurso = null;
		
		try {
			recurso = uploadFileService.load(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; fileName=\""+ recurso.getFilename()+"\"").body(recurso);
	}
	
	@GetMapping(value="cliente/ver/{id}")
	public String ver(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Cliente cliente = clienteService.findOne(id);
		if(cliente== null) {
			flash.addFlashAttribute("Error","Cliente no encontrado");
			return "redirect:/listar";
		}
		
		model.put("cliente", cliente);
		model.put("titulo", "Detalles del cliente" +" "+ cliente.getNombre() + " "+ cliente.getApellido());
		
		return "cliente/ver";
	}

	@RequestMapping(value ="cliente/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name="page",defaultValue="0") int page,Model model,
			Authentication authentication) {
		
		if(authentication != null) {
			log.info("Hola usuario autenticado, tu username es: " + authentication.getName());
		}
		
		if(hasRole("ROLE_ADMIN")) {
			log.info("Hola: " + authentication.getName() + " tienes acceso");
		}else {
			log.info("Hola: " + authentication.getName() + " no tienes acceso");
		}
		
		Pageable pageRequest= PageRequest.of(page,5);
		Page<Cliente> clientes= clienteService.findAll(pageRequest);
		
		PageRender<Cliente> pageRender= new PageRender<>("/cliente/listar",clientes);
		
		model.addAttribute("titulo", "Listado de clientes por página");
		model.addAttribute("clientes", clientes);
		model.addAttribute("page",pageRender);
		return "cliente/listar";
	}

	@RequestMapping(value = "cliente/form")
	public String crear(Map<String, Object> model) {

		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");
		return "cliente/form";
	}

	@RequestMapping(value = "cliente/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Cliente cliente = null;

		if (id > 0) {
			cliente = clienteService.findOne(id);
			if (cliente == null) {
				flash.addFlashAttribute("error", "El ID del cliente no existe en la BBDD!");
				return "redirect:/";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del cliente no puede ser cero!");
			return "redirect:/";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");
		return "cliente/form";
	}

	@RequestMapping(value = "cliente/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status, @RequestParam("file") MultipartFile foto) {
		
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "/form";
		}
		
		if(!foto.isEmpty()) {
			
			if(cliente.getId() != null 
					&& cliente.getId()> 0 
					&& cliente.getFoto() != null
					&& cliente.getFoto().length() > 0) {
				
				uploadFileService.delete(cliente.getFoto());
				
			}
			String uniqueFileName = null;
				
			try {
				uniqueFileName = uploadFileService.copy(foto);
			} catch (Exception e) {
				e.printStackTrace();
			}
				
			Path routePath = Paths.get(UPLOADS_FOLDER).resolve(uniqueFileName);
			Path routeAbsolutePath = routePath.toAbsolutePath();
			
			
			flash.addFlashAttribute("info","Has subido correctamente " + uniqueFileName);
			
			cliente.setFoto(uniqueFileName);
		}else {
			cliente.setFoto("");
		}

		String mensajeFlash = (cliente.getId() != null) ? "Cliente editado con éxito!" : "Cliente creado con éxito!";

		Rol rolUsuario = rolRepository.findByNombre("USER");
		cliente.setRol(rolUsuario);
		
		clienteService.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/";
	}

	@RequestMapping(value = "cliente/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			Cliente cliente = clienteService.findOne(id);
			
			clienteService.delete(id);
			flash.addFlashAttribute("success", "Cliente eliminado con éxito!");
			
			if(uploadFileService.delete(cliente.getFoto())) {
				flash.addFlashAttribute("info","Foto " + cliente.getFoto() + " eliminada con exito.");
			}
				
		}
		return "redirect:/cliente/listar";
	}
	
	private boolean hasRole(String role) {
		
		SecurityContext context = SecurityContextHolder.getContext();
		
		if(context == null) {
			return false;
		}

		Authentication auth = context.getAuthentication();

		if(auth ==null) {
			return false;
		}

		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		
		for(GrantedAuthority authority: authorities) {
			if(role.equals(authority.getAuthority())) {
				return true;
			}
		}
		return false;
	}
}