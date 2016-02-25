package com.disney.ad.adexchange.campaign.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.disney.ad.adexchange.campaign.domain.Video;
import com.disney.ad.adexchange.campaign.domain.VideoList;
import com.disney.ad.adexchange.campaign.domain.VideoSearchRequest;
import com.disney.ad.adexchange.campaign.repository.VideoRepository;
import com.disney.ad.adexchange.campaign.data.VideoSpecification;
import com.disney.ad.adexchange.exception.ApiException;
import com.disney.ad.adexchange.util.ServiceUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Validated
public class VideoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoService.class);
    
    @Autowired
    private final VideoRepository repository = null;

	
	@Autowired
	private ServiceUtil serviceUtil = null;	

    @Transactional
    public Video insert(@NotNull @Valid final Video video) {
        LOGGER.debug("Creating {}", video);
        Video existing = repository.findOne(video.getId());
        if (existing != null) {
            throw new ApiException(
                    String.format("Duplicate insert with id=%s", video.getId()));
        }
        return update(video);
    }
    
    @Transactional
    public Video update(@NotNull @Valid final Video video) {
        LOGGER.debug("Updating {}", video);
        Video updatedVideo =  repository.save(video);
        return updatedVideo;    	         
    }        

    @Transactional(readOnly = true)
    public VideoList getList(int pageNo, int pageSize) {
        return getList(pageNo, pageSize, null, null);
    }

    @Transactional(readOnly = true)
    public VideoList getList(int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Retrieving the list of all video");
        Page<Video> videoPage = repository.findAll(serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        VideoList videoList = convertPageToList(videoPage);
        return videoList;
    }

    @Transactional(readOnly = true)
    public VideoList search(VideoSearchRequest videoSearchRequest, int pageNo, int pageSize) {
        return search(videoSearchRequest, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public VideoList search(VideoSearchRequest videoSearchRequest, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search video");
        Page<Video> videoPage = repository.findAll(VideoSpecification.searchAnd(videoSearchRequest, true), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        VideoList videoList = convertPageToList(videoPage); 
        return videoList;
    }    

    @Transactional(readOnly = true)
    public VideoList searchByKeyword(String keyword, int pageNo, int pageSize) {
        return searchByKeyword(keyword, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public VideoList searchByKeyword(String keyword, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search video by keyword");
        Page<Video> videoPage = repository.findAll(VideoSpecification.searchByKeyword(keyword), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        VideoList videoList = convertPageToList(videoPage); 
        return videoList;
    }    
    @Transactional(readOnly = true)
    public Video getById(Integer id) {
    	Video existing = repository.findOne(id);
    	if (existing ==  null) {
    		throw new ApiException("Data does not exist");
    	}
        return existing;
    }
    
    @Transactional()
    public void deleteById(Integer id) {
    	getById(id); // to validate
        repository.delete(id);
    }        
 
 	private VideoList convertPageToList(Page<Video> videoPage) {
		long totalElements = videoPage.getTotalElements();
		List<Video> listOfVideo = videoPage.getContent();
		VideoList videoList = new VideoList(totalElements, listOfVideo);
		return videoList;
	}
 
	

}