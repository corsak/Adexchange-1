package com.disney.ad.adexchange.common.domain;


import java.util.List;

public class IndustryCategoryList {

    private long count;

    private List<IndustryCategory> IndustryCategorys;

	public IndustryCategoryList(long count, List<IndustryCategory> IndustryCategorys) {
		this.count = count;
		this.IndustryCategorys = IndustryCategorys;
	}

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<IndustryCategory> getIndustryCategorys() {
        return IndustryCategorys;
    }

    public void setIndustryCategorys(List<IndustryCategory> IndustryCategorys) {
        this.IndustryCategorys = IndustryCategorys;
    }
}

