package com.core.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.constant.CommonConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TSysmenu implements Serializable {
	//菜单序号
    private String menucode;
    
    //菜单名称
    private String menuname;
    
    //菜单描述
    private String menudesc;
    
    //菜单地址/事件
    private String url;
    
    //上级菜单序号
    private String upmenu;
    
    //菜单图标
    private String icon;
    
    //菜单排序
    private Short sort;
    
    //位置
    private String position;
    
    //级别
    private String menulevel;
    
    //是否有效
    private String isactive;
    
    private int menutype;
    
    //菜单类型
    private int menukind;

    private static final long serialVersionUID = 1L;
    
    private String menulevelDesc;
    
    private String menukindDesc;

    private List<TSysmenu> childMenuMap=new ArrayList<TSysmenu>();
    
	private Boolean checked;

	@SuppressWarnings("unused")
	private String state;
    

    public String getMenudesc() {
        return menudesc;
    }

    public void setMenudesc(String menudesc) {
        this.menudesc = menudesc;
    }

    public int getMenutype() {
		return menutype;
	}

	public void setMenutype(int menutype) {
		this.menutype = menutype;
	}

	public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUpmenu() {
        return upmenu;
    }

    public void setUpmenu(String upmenu) {
        this.upmenu = upmenu;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Short getSort() {
        return sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

	public String getMenulevel() {
		return menulevel;
	}

	public void setMenulevel(String menulevel) {
		this.menulevel = menulevel;
	}

    public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public List<TSysmenu> getChildMenuMap() {
		return childMenuMap;
	}

	public void setChildMenuMap(List<TSysmenu> childMenuMap) {
		this.childMenuMap = childMenuMap;
	}

	@JsonIgnore
    public List<TSysmenu> getChildMenu() {
		return childMenuMap;
	}

	public void setChildMenu(TSysmenu childMenu) {
		if (childMenu!=null)
			this.childMenuMap.add(childMenu);
	}
	
	public void setChildMenuAll(List<TSysmenu> childMenu){
		this.childMenuMap=childMenu;
	}
    
	@JsonProperty("id")
    public String getMenucode() {
        return menucode;
    }

    public void setMenucode(String menucode) {
        this.menucode = menucode;
    }

    @JsonProperty("text")
    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }


    
    public String getIconCls(){
    	if (this.childMenuMap!=null && this.childMenuMap.size()>0) return this.icon;
	    	
	    if (this.icon==null) return "icon-leaf";
	    else return this.icon;
    }
    
	public Map<String,Object> getAttributes(){
		Map<String,Object> attributes=new HashMap<String,Object>();
		attributes.put("url", this.url);
		return attributes;
	}
    
	public List<TSysmenu> getChildren() {
		return childMenuMap;
	}

	public void setState(String state) {
		this.state = "closed";
	}

	public String getState() {
		if(this.getMenulevel().equals(CommonConstant.MenuLevel.Level1.toString())){
			return "closed";
		}else {
			return null;
		}
	}

	public String getMenuPositionDesc() {
		return CommonConstant.MenuPosition.getDescByValue(position);
	}
	
	public void setMenulevelDesc(String menuLevelDesc) {
		this.menulevelDesc = menuLevelDesc;
	}

	public String getMenuLevelDesc() {
		
		if(menulevel.equals(CommonConstant.MenuLevel.Level1.toString())){
			this.setMenulevelDesc("一级菜单");
		} else if(menulevel.equals(CommonConstant.MenuLevel.Level2.toString())){
			this.setMenulevelDesc("二级菜单");
		} else if(menulevel.equals(CommonConstant.MenuLevel.Level3.toString())) {
			this.setMenulevelDesc("三级菜单");
		} else {
			this.setMenulevelDesc("四级菜单");
		}
		
		
		return menulevelDesc;
	}

	public int getMenukind() {
		return menukind;
	}

	public void setMenukind(int menukind) {
		this.menukind = menukind;
	}

	public String getMenukindDesc() {
		this.setMenukindDesc(CommonConstant.getDesc("BUSIKIND", Integer.toString(menukind)));
		return menukindDesc;
	}

	public void setMenukindDesc(String menukindDesc) {
		this.menukindDesc = menukindDesc;
	}
}