package com.bolsadeideas.springboot.app.models.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

public class UploadFileServiceImp implements IUploadFileService {
	
	private final Logger log= LoggerFactory.getLogger(getClass());
	private final static String UPLOADS_FOLDER = "uploads";

	@Override
	public Resource load(String fileName) throws MalformedURLException {

		Path pathFoto = getPath(fileName);
		log.info("pathFoto:" + pathFoto);
		
		Resource recurso = null;
		
		recurso = new UrlResource(pathFoto.toUri());
		
		if(!recurso.exists() || !recurso.isReadable()) {
			throw new RuntimeException("Error, no se puede cargar la imagen: " + pathFoto.toString());
		}
			
		return recurso;
	}

	@Override
	public String copy(MultipartFile file) throws IOException{
		
		String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		Path routePath = Paths.get(uniqueFileName);	
		log.info("routePath:" + routePath);

		
	
//			byte[] bytes = foto.getBytes();
//			Path rutaCompleta = Paths.get(routePath + "//" + cliente.getId()+cliente.getNombre()+".jpg");
//			Files.write(rutaCompleta, bytes);
		
		Files.copy(file.getInputStream(), routePath);
		
		return uniqueFileName;
	}

	@Override
	public boolean delete(String filename) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Path getPath(String fileName) {
		return Paths.get(UPLOADS_FOLDER).resolve(fileName).toAbsolutePath();
	}

}
