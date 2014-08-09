package com.sltunion.cloudy.persistent.model;

/**
 * @author sundial
 * @date 2014-05-15 21:56:59
 */
public class TCostlog {
	private Long id;// id

	private Long userid;// userid

	private Long channelid;// channelid

	private Integer installnum;// installnum

	private Float costprice;// costprice

	private Float totalcost;// totalcost

	private Float inputprice;// inputprice

	private Float totalinput;// totalinput

	private Byte status;// status

	private String createdate;// createdate

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getChannelid() {
		return this.channelid;
	}

	public void setChannelid(Long channelid) {
		this.channelid = channelid;
	}

	public Integer getInstallnum() {
		return this.installnum;
	}

	public void setInstallnum(Integer installnum) {
		this.installnum = installnum;
	}

	public Float getCostprice() {
		return this.costprice;
	}

	public void setCostprice(Float costprice) {
		this.costprice = costprice;
	}

	public Float getTotalcost() {
		return this.totalcost;
	}

	public void setTotalcost(Float totalcost) {
		this.totalcost = totalcost;
	}

	public Float getInputprice() {
		return this.inputprice;
	}

	public void setInputprice(Float inputprice) {
		this.inputprice = inputprice;
	}

	public Float getTotalinput() {
		return this.totalinput;
	}

	public void setTotalinput(Float totalinput) {
		this.totalinput = totalinput;
	}

	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		toString.append("TCostlog").append("{");
		toString.append("id").append(" = ").append(id).append(",");
		toString.append("userid").append(" = ").append(userid).append(",");
		toString.append("channelid").append(" = ").append(channelid).append(",");
		toString.append("installnum").append(" = ").append(installnum).append(",");
		toString.append("costprice").append(" = ").append(costprice).append(",");
		toString.append("totalcost").append(" = ").append(totalcost).append(",");
		toString.append("inputprice").append(" = ").append(inputprice).append(",");
		toString.append("totalinput").append(" = ").append(totalinput).append(",");
		toString.append("status").append(" = ").append(status).append(",");
		toString.append("createdate").append(" = ").append(createdate).append(",");
		toString.setLength(toString.length() - 1);
		toString.append("}");
		return toString.toString();
	}
}