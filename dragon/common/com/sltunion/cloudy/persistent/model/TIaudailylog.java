package com.sltunion.cloudy.persistent.model;
/**
 * @author sundial
 * @date 2014-02-08 10:56:22
 */
public class TIaudailylog { 
	private Long id;//id 
	
	private Long userid;//userid 
	
	private Long channelid;//channelid 
	
	private Integer installnum;//installnum 
	
	private Integer activenum;//activenum 
	
	private Integer uninstallnum;//uninstallnum 
	
	private Integer vmnum;//vmnum 
	
	private Integer xpnum;//xpnum 
	
	private Integer win732num;//win732num 
	
	private Integer win764num;//win764num 
	
	private Integer win832num;//win832num 
	
	private Integer win864num;//win864num 
	
	private Integer othernum;//othernum 
	
	private Integer driverdown;//driverdown 
	
	private Integer hour;//hour
	
	private String createdate;//createdate 
	
	public Long getId(){
		return this.id;
	}
	public void  setId(Long id){
		this.id=id;
	}

	public Long getUserid(){
		return this.userid;
	}
	public void  setUserid(Long userid){
		this.userid=userid;
	}

	public Long getChannelid(){
		return this.channelid;
	}
	public void  setChannelid(Long channelid){
		this.channelid=channelid;
	}

	public Integer getInstallnum(){
		return this.installnum;
	}
	public void  setInstallnum(Integer installnum){
		this.installnum=installnum;
	}

	public Integer getActivenum(){
		return this.activenum;
	}
	public void  setActivenum(Integer activenum){
		this.activenum=activenum;
	}

	public Integer getUninstallnum(){
		return this.uninstallnum;
	}
	public void  setUninstallnum(Integer uninstallnum){
		this.uninstallnum=uninstallnum;
	}

	public Integer getVmnum(){
		return this.vmnum;
	}
	public void  setVmnum(Integer vmnum){
		this.vmnum=vmnum;
	}

	public Integer getXpnum(){
		return this.xpnum;
	}
	public void  setXpnum(Integer xpnum){
		this.xpnum=xpnum;
	}

	public Integer getWin732num(){
		return this.win732num;
	}
	public void  setWin732num(Integer win732num){
		this.win732num=win732num;
	}

	public Integer getWin764num(){
		return this.win764num;
	}
	public void  setWin764num(Integer win764num){
		this.win764num=win764num;
	}

	public Integer getWin832num(){
		return this.win832num;
	}
	public void  setWin832num(Integer win832num){
		this.win832num=win832num;
	}

	public Integer getWin864num(){
		return this.win864num;
	}
	public void  setWin864num(Integer win864num){
		this.win864num=win864num;
	}

	public Integer getOthernum(){
		return this.othernum;
	}
	public void  setOthernum(Integer othernum){
		this.othernum=othernum;
	}

	public Integer getDriverdown(){
		return this.driverdown;
	}
	public void  setDriverdown(Integer driverdown){
		this.driverdown=driverdown;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}
	public Integer getHour() {
		return hour;
	}
	public String getCreatedate(){
		return this.createdate;
	}
	public void  setCreatedate(String createdate){
		this.createdate=createdate;
	}

	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		toString.append("TIaudailylog").append("{");
		toString.append("id").append(" = ").append(id).append(",");
		toString.append("userid").append(" = ").append(userid).append(",");
		toString.append("channelid").append(" = ").append(channelid).append(",");
		toString.append("installnum").append(" = ").append(installnum).append(",");
		toString.append("activenum").append(" = ").append(activenum).append(",");
		toString.append("uninstallnum").append(" = ").append(uninstallnum).append(",");
		toString.append("vmnum").append(" = ").append(vmnum).append(",");
		toString.append("xpnum").append(" = ").append(xpnum).append(",");
		toString.append("win732num").append(" = ").append(win732num).append(",");
		toString.append("win764num").append(" = ").append(win764num).append(",");
		toString.append("win832num").append(" = ").append(win832num).append(",");
		toString.append("win864num").append(" = ").append(win864num).append(",");
		toString.append("othernum").append(" = ").append(othernum).append(",");
		toString.append("driverdown").append(" = ").append(driverdown).append(",");
		toString.append("hour").append(" = ").append(hour).append(",");
		toString.append("createdate").append(" = ").append(createdate).append(",");
		toString.setLength(toString.length() - 1);
		toString.append("}");
		return toString.toString();
	}
}