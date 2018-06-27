package vn.lgsp.fw.cmon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@Component
@Data
@ToString
public class SAML2Properties {

	@Value("${conf.saml2.keystorePath}")
	private String keystorePath;
	
	@Value("${conf.saml2.keystorePassword}")
	private String keystorePassword;
	
	@Value("${conf.saml2.keystoreAlias}")
	private String keystoreAlias;
	
	@Value("${conf.saml2.privateKeyPassword}")
	private String privateKeyPassword;
	
	@Value("${conf.saml2.serviceProviderEntityId}")
	private String serviceProviderEntityId;
	
	@Value("${conf.saml2.identityProviderMetadataPath}")
	private String identityProviderMetadataPath;
	
}
