package ci.jsi.importExport.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {

	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	private final Path p1 = Paths.get(File.pathSeparator+"/tmp/foo"); 
	//private final Path rootLocaltion = Paths.get("upload-dir");
	private final Path rootLocaltion2 = Paths.get(File.separator+"DREAMS"+File.separator+"upload-dir");
	
	
	public void store(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), this.rootLocaltion2.resolve(file.getOriginalFilename()));
		} catch (IOException e) {
			throw new RuntimeException("FAIL!");
		}
		
	}
	
	public Resource loadFile(String filename) {
		
		try {
			Path file = rootLocaltion2.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if(resource.exists() || resource.isReadable()) {
				return resource;
			}else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
	}
	
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocaltion2.toFile());
	}
	
	public void init() {
		try {
			Files.createDirectories(rootLocaltion2);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage!");
		}
	}
}
























