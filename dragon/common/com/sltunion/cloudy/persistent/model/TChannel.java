package com.sltunion.cloudy.persistent.model;
/**
 * @author sundial
 * @date 2014-02-07 15:30:54
 */
public class TChannel { 
	private Long id;//id 
	
	private String name;//name 
	
	private String username;//username 
	
	private Long userid;//userid 
	
	private Float deduct;//deduct 
	
	private Integer startnum;//startnum 
	
	private Integer status;//status 
	
	private String remark;//remark 
	
	private String createtime;//createtime
	
	private Float price;
	
	public Long getId(){
		return this.id;
	}
	public void  setId(Long id){
		this.id=id;
	}

	public String getName(){
		return this.name;
	}
	public void  setName(String name){
		this.name=name;
	}

	public String getUsername(){
		return this.username;
	}
	public void  setUsername(String username){
		this.username=username;
	}

	public Long getUserid(){
		return this.userid;
	}
	public void  setUserid(Long userid){
		this.userid=userid;
	}

	public Float getDeduct(){
		return this.deduct;
	}
	public void  setDeduct(Float deduct){
		this.deduct=deduct;
	}

	public Integer getStartnum(){
		return this.startnum;
	}
	public void  setStartnum(Integer startnum){
		this.startnum=startnum;
	}

	public Integer getStatus(){
		return this.status;
	}
	public void  setStatus(Integer status){
		this.status=status;
	}

	public String getRemark(){
		return this.remark;
	}
	public void  setRemark(String remark){
		this.remark=remark;
	}

	public String getCreatetime(){
		return this.createtime;
	}
	public void  setCreatetime(String createtime){
		this.createtime=createtime;
	}

	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		toString.append("TChannel").append("{");
		toString.append("id").append(" = ").append(id).append(",");
		toString.append("name").append(" = ").append(name).append(",");
		toString.append("username").append(" = ").append(username).append(",");
		toString.append("userid").append(" = ").append(userid).append(",");
		toString.append("deduct").append(" = ").append(deduct).append(",");
		toString.append("startnum").append(" = ").append(startnum).append(",");
		toString.append("status").append(" = ").append(status).append(",");
		toString.append("remark").append(" = ").append(remark).append(",");
		toString.append("createtime").append(" = ").append(createtime).append(",");
		toString.append("price").append(" = ").append(price).append(",");
		toString.setLength(toString.length() - 1);
		toString.append("}");
		return toString.toString();
	}
}