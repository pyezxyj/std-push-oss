package com.xnjr.oss.home.req;

public class XN704301Req {

    // 内容类型(必填)
    private String type;

    // 内容标题（必填）
    private String title;

    // 图片1（选填）
    private String picture1;

    // 图片2（选填）
    private String picture2;

    // 详情描述（必填）
    private String description;

    // 备注（选填）
    private String remark;

    // 菜单编号（必填）
    private String menuCode;
    
    // 尾注（必填）
    private String endNote;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture1() {
        return picture1;
    }

    public void setPicture1(String picture1) {
        this.picture1 = picture1;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

	public String getEndNote() {
		return endNote;
	}

	public void setEndNote(String endNote) {
		this.endNote = endNote;
	}
}
