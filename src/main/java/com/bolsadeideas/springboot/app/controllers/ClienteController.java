package com.bolsadeideas.springboot.app.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.service.IClienteService;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	
	private final Logger log= LoggerFactory.getLogger(getClass());
	
	private final static String UPLOADS_FOLDER = "uploads";
	
	@GetMapping(value="/uploads/{fileName:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String fileName){
		
		Path pathFoto = Paths.get(UPLOADS_FOLDER).resolve(fileName).toAbsolutePath();
		log.info("pathFoto:" + pathFoto);
		
		Resource recurso = null;
		
		try {
			
			recurso = new UrlResource(pathFoto.toUri());
			
			if(!recurso.exists() || !recurso.isReadable()) {
				throw new RuntimeException("Error, no se puede cargar la imagen: " + pathFoto.toString());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; fileName=\""+ recurso.getFilename()+"\"").body(recurso);
	}
	
	@GetMapping(value="/ver/{id}")
	public String ver(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Cliente cliente = clienteService.findOne(id);
		if(cliente== null) {
			flash.addFlashAttribute("Error","Cliente no encontrado");
			return "redirect:/listar";
		}
		
		model.put("cliente", cliente);
		model.put("titulo", "Detalles del cliente" +" "+ cliente.getNombre() + " "+ cliente.getApellido());
		
		return "ver";
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name="page",defaultValue="0") int page,Model model) {
		Pageable pageRequest= PageRequest.of(page,5);
		Page<Cliente> clientes= clienteService.findAll(pageRequest);
		
		PageRender<Cliente> pageRender= new PageRender<>("/listar",clientes);
		
		model.addAttribute("titulo", "Listado de clientes por página");
		model.addAttribute("clientes", clientes);
		model.addAttribute("page",pageRender);
		return "listar";
	}

	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {

		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");
		return "form";
	}

	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Cliente cliente = null;

		if (id > 0) {
			cliente = clienteService.findOne(id);
			if (cliente == null) {
				flash.addFlashAttribute("error", "El ID del cliente no existe en la BBDD!");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del cliente no puede ser cero!");
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");
		return "form";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status, @RequestParam("file") MultipartFile foto) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "form";
		}
		
		if(!foto.isEmpty()) {
//			Path directorioRerurso = Paths.get("src//main//resources//static//uploads");
//			String routePath = directorioRerurso.toFile().getAbsolutePath();
			
			//String routePath = "C://Temp//uploads";
			
			if(cliente.getId()!=null 
					&& cliente.getId()>0 
					&& cliente.getFoto().length()>0) {
				Path routePath = Paths.get(UPLOADS_FOLDER).resolve(cliente.getFoto()).toAbsolutePath();
				File archivo = routePath.toFile();
				if(archivo.exists() && archivo.canRead()) {
					archivo.delete();
				}
			}
			
			String uniqueFileName = UUID.randomUUID().toString() + "_" + foto.getOriginalFilename();
			
			Path routePath = Paths.get(UPLOADS_FOLDER).resolve(uniqueFileName);
			Path routeAbsolutePath = routePath.toAbsolutePath();
			
			log.info("routePath:" + routePath);
			log.info("routeAbsolutePath:" + routeAbsolutePath);
			
			try {
//				byte[] bytes = foto.getBytes();
//				Path rutaCompleta = Paths.get(routePath + "//" + cliente.getId()+cliente.getNombre()+".jpg");
//				Files.write(rutaCompleta, bytes);
				
				Files.copy(foto.getInputStream(), routeAbsolutePath);
				
				flash.addFlashAttribute("info", "Foto subida correctamente: " + uniqueFileName);
				
				cliente.setFoto(uniqueFileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		String mensajeFlash = (cliente.getId() != null) ? "Cliente editado con éxito!" : "Cliente creado con éxito!";

		clienteService.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			
			Cliente cliente = clienteService.findOne(id);
			
			clienteService.delete(id);
			
			flash.addFlashAttribute("success", "Cliente eliminado con éxito!");
			
			Path routePath = Paths.get(UPLOADS_FOLDER).resolve(cliente.getFoto()).toAbsolutePath();
			File archivo = routePath.toFile();
			if(archivo.exists() && archivo.canRead()) {
				if(archivo.delete()) {
					flash.addFlashAttribute("info","La foto: " + cliente.getFoto() + " ha sido eliminada con éxito.");
				}
			}
			
			
			
		}
		return "redirect:/listar";
	}
}