package culturemedia.controllers;

import java.util.*;

import culturemedia.exception.VideoNotFoundExeption;
import culturemedia.model.Video;

import culturemedia.service.CultureMediaService;

public class CultureMediaController
 {

	private final CultureMediaService cultureMediaService;


	public CultureMediaController(CultureMediaService cultureMediaService) {
		this.cultureMediaService = cultureMediaService;
	}


	public List<Video> ListAllVideos() throws VideoNotFoundExeption {
		List<Video> videos = cultureMediaService.ListAllVideos();
		return videos;
	}

}