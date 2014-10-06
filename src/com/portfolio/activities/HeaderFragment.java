package com.portfolio.activities;

import com.portfolio.model.interfaces.IPage;

import android.app.Fragment;

public abstract class HeaderFragment extends Fragment {
	
	protected IPage page;

	public void setPage(IPage page){
		this.page = page;
	}

}
