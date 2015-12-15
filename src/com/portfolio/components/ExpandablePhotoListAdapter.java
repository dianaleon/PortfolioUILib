package com.portfolio.components;

import java.util.HashMap;
import java.util.List;

import com.portfolio.R;
import com.portfolio.model.interfaces.component.IPageObject;
import com.portfolio.util.UIUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.provider.Contacts.Intents.UI;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class ExpandablePhotoListAdapter extends BaseExpandableListAdapter {
	
	private Typeface tf;
     
    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<Bitmap>> _listDataChild;
    private List<IPageObject> _objetosExt;
 
    public ExpandablePhotoListAdapter(Context context, List<String> listDataHeader,
            HashMap<String, List<Bitmap>> listChildData,List<IPageObject> objetos) {
    	 
    	tf = Typeface.createFromAsset(getAssets(),
                "fonts/Raleway-Regular.ttf");
    	 
    	this._context = context;
        this._listDataHeader = listDataHeader;
   
        this._listDataChild = listChildData;
        this._objetosExt = objetos;
        
       
    }
 
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }
 
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    @Override
    public View getChildView(int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
 
        final Bitmap childImage = (Bitmap) getChild(groupPosition, childPosition);
 
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }
 
        ImageView txtListChild = (ImageView) convertView
                .findViewById(R.id.lblListItem);
 
        txtListChild.setImageBitmap(childImage);
        return convertView;
    }
 
    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }
 
    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }
 
    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }
 
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }
 
       
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        
        lblListHeader.setTypeface(tf);
        String a = _objetosExt.get(groupPosition).getStartColorBackground();
        String b = _objetosExt.get(groupPosition).getEndColorBackground();
        UIUtils.setGradient(lblListHeader, _objetosExt.get(groupPosition).getStartColorBackground(),_objetosExt.get(groupPosition).getEndColorBackground(),String.valueOf(_objetosExt.get(groupPosition).getGradientOrientatio()));
        lblListHeader.setText(headerTitle);
   
        String aa =  _objetosExt.get(groupPosition).getTextColor();
        UIUtils.setTextColor(lblListHeader, _objetosExt.get(groupPosition).getTextColor());
   
       
       
        lblListHeader.setTypeface(tf);
        return convertView;
    }
 
    @Override
    public boolean hasStableIds() {
        return false;
    }
 
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}