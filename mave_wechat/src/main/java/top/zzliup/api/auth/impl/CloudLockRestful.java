package top.zzliup.api.auth.impl;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import top.zzliup.api.auth.inter.CloudLockImpl;


@Path("vertify")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class CloudLockRestful {
	private CloudLockImpl impl;
	@GET
	@Path("vertifyUser")
	public String vertifyUser(@QueryParam("signature")String signature,@QueryParam("timestamp")String timestamp,
			@QueryParam("nonce")String nonce,@QueryParam("echostr")String echostr) {
		boolean flag = impl.checkWx(signature, timestamp, nonce);
		if(flag) {
			return echostr;
		}else {
			return "error";
		}
	}
	public CloudLockImpl getImpl() {
		return impl;
	}
	public void setImpl(CloudLockImpl impl) {
		this.impl = impl;
	}
	
}
