package org.dante.springboot.vo;

import java.io.Serializable;

public class MsgVO implements Serializable {

	private static final long serialVersionUID = 1L;

	public MsgVO() {
	}

	private String msgId;
	private String info;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}


}
