package com.disney.ad.adexchange.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

@Component
public class ServiceUtil {
	
	public Pageable composePageable(int pageNo, int pageSize, String sortField, String sortBy) {
		Sort sort = null;
		if (sortField != null) {
			Direction direction = Direction.ASC;
			if (sortBy != null && sortBy.equalsIgnoreCase("DESC")) {
				direction = Direction.DESC;
			}
			sort = new Sort(direction, sortField);
		}
		// Page no is zero indexed. hence reducing by 1.
		Pageable pageable = null;
		if (sort == null) {
			pageable = new PageRequest(pageNo - 1, pageSize);
		} else {
			pageable = new PageRequest(pageNo - 1, pageSize, sort);
		}

		return pageable;
	}

}
