package com.disney.ad.adexchange.common.domain;


import java.util.List;

public class AdTemplatesList {

    private long count;

    private List<AdTemplates> AdTemplatess;

	public AdTemplatesList(long count, List<AdTemplates> AdTemplatess) {
		this.count = count;
		this.AdTemplatess = AdTemplatess;
	}

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<AdTemplates> getAdTemplatess() {
        return AdTemplatess;
    }

    public void setAdTemplatess(List<AdTemplates> AdTemplatess) {
        this.AdTemplatess = AdTemplatess;
    }
}

