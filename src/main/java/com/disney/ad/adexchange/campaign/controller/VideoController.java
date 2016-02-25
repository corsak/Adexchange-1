package com.disney.ad.adexchange.campaign.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.disney.ad.adexchange.campaign.domain.Video;
import com.disney.ad.adexchange.campaign.domain.VideoList;
import com.disney.ad.adexchange.campaign.domain.VideoSearchRequest;
import com.disney.ad.adexchange.campaign.service.VideoService;
import com.disney.ad.adexchange.exception.ApiException;

import javax.validation.Valid;

@RestController
public class VideoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoController.class);
    
    private static final String DEFAULT_PAGE_SIZE = "10";
    
    @Autowired
    private final VideoService videoService = null;

    @RequestMapping(value = "/video", method = RequestMethod.POST)
    public Video createVideo(@RequestBody @Valid final Video video) {
        LOGGER.debug("API: Create {}", video);
        return videoService.insert(video);
    }
    
    @RequestMapping(value = "/video", method = RequestMethod.PUT)
    public Video updateVideo(@RequestBody @Valid final Video video) {
        LOGGER.debug("API: Update {}", video);
        return videoService.update(video);
    }        

    @RequestMapping(value = "/video", method = RequestMethod.GET)
    public VideoList listVideos(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: list all video");
        VideoList videoList = videoService.getList(pageNo, pageSize);
        return videoList;
    }
    
    @RequestMapping(value = "/video/search", method = RequestMethod.GET)
    public VideoList searchVideo(
			@RequestParam(required = false, value = "title") String title,
			@RequestParam(required = false, value = "videoUrl") String videoUrl,
			@RequestParam(required = false, value = "videoType") String videoType,
			@RequestParam(required = false, value = "delivery") String delivery,
			@RequestParam(required = false, value = "bitrate") String bitrate,
			@RequestParam(required = false, value = "width") Integer width,
			@RequestParam(required = false, value = "height") Integer height,
			@RequestParam(required = false, value = "duration") Float duration,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search video");
        VideoSearchRequest videoSearchRequest = composeVideoSearchRequest(
			title,
			videoUrl,
			videoType,
			delivery,
			bitrate,
			width,
			height,
			duration
        );
		        
        VideoList videoList = videoService.search(videoSearchRequest, pageNo, pageSize);
        return videoList;
    }
    
    @RequestMapping(value = "/video/search/keyword", method = RequestMethod.GET)
    public VideoList searchVideoByKeyword(
    		@RequestParam(required = true, value = "keyword") String keyword,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search video by keyword");
        VideoList videoList = videoService.searchByKeyword(keyword, pageNo, pageSize);
        return videoList;
    }

    @RequestMapping(value = "/video/{id}", method = RequestMethod.GET)
    public Video getVideo(@PathVariable("id") Integer id) {
        LOGGER.debug("API: list video by Id");
        return videoService.getById(id);
    }    

    @RequestMapping(value = "/video/{id}", method = RequestMethod.DELETE)
    public void deleteVideo(@PathVariable("id") Integer id) {
        LOGGER.debug("API: delete video by Id");
        videoService.deleteById(id);
    }       

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleException(ApiException e) {
        return e.getMessage();
    }

	public VideoSearchRequest composeVideoSearchRequest(
			String title,
			String videoUrl,
			String videoType,
			String delivery,
			String bitrate,
			Integer width,
			Integer height,
			Float duration
) {
		VideoSearchRequest videoSearchRequest = new VideoSearchRequest();
        videoSearchRequest.setTitle(title);
        videoSearchRequest.setVideoUrl(videoUrl);
        videoSearchRequest.setVideoType(videoType);
        videoSearchRequest.setDelivery(delivery);
        videoSearchRequest.setBitrate(bitrate);
        videoSearchRequest.setWidth(width);
        videoSearchRequest.setHeight(height);
        videoSearchRequest.setDuration(duration);
		return videoSearchRequest; 
	}    
}
