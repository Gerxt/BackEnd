package culturemedia.controllers;

import java.util.*;

import culturemedia.exception.VideoNotFoundExeption;
import culturemedia.model.Video;

import culturemedia.service.CultureMediaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import culturemedia.repository.impl.VideoRepositoryImpl;
import culturemedia.repository.impl.ViewsRepositoryImpl;
import culturemedia.service.impl.CultureMediaServiceImpl;


@RestController
public class CultureMediaController 
{

	private final CultureMediaService cultureMediaService;


	public CultureMediaController() 
	{
		this.cultureMediaService = new CultureMediaServiceImpl(new VideoRepositoryImpl(), new ViewsRepositoryImpl());
	}

	@GetMapping("/videos")
	public ResponseEntity<List<Video>> ListAllVideos() throws VideoNotFoundExeption 
	{
		try 
		{
			return ResponseEntity.status( HttpStatus.OK ).body( cultureMediaService.ListAllVideos() );
		} 

		catch (VideoNotFoundExeption e) 
		{
			return ResponseEntity.status ( HttpStatus.BAD_REQUEST)
			.header( "Error_name", e.getMessage() )
			.body(Collections.emptyList());
		}	
	}

	@PostMapping("/videos")
	public ResponseEntity<Video> add(@RequestBody Video video) 
	{
		return ResponseEntity.status( HttpStatus.OK ).body(cultureMediaService.add(video));
	}

	
}
