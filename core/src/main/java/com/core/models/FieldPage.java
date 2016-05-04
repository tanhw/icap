package com.core.models;

import java.io.Serializable;
import java.util.List;

import com.constant.CommonConstant;

public class FieldPage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean text;
	
	private boolean time;
	
	
	private boolean combo;
	
	private String name;
	
	private String word;
	
	private List<FieldList> list;
	
	public FieldPage(String word, String name, String property) {
		this.word = word;
		this.name = name;
		
		if(property.equals(CommonConstant.Field.Text.toString())){
			this.setText(true);
		}
		if(property.equals(CommonConstant.Field.Time.toString())){
			this.setTime(true);
		}

	}
	
	public FieldPage(String word, String name, List<FieldList> list){
		this.word = word;
		this.name = name;
		this.setCombo(true);
	}


	public boolean isText() {
		return text;
	}


	public void setText(boolean text) {
		this.text = text;
	}


	public boolean isTime() {
		return time;
	}
	
	


	public String getWord() {
		return word;
	}


	public void setWord(String word) {
		this.word = word;
	}


	public void setTime(boolean time) {
		this.time = time;
	}


	public boolean isCombo() {
		return combo;
	}


	public void setCombo(boolean combo) {
		this.combo = combo;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<FieldList> getList() {
		return list;
	}


	public void setList(List<FieldList> list) {
		this.list = list;
	}

}
