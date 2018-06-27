package vn.lgsp.fw.cmon.web.security;

import org.opensaml.saml.saml2.core.LogoutResponse;
import org.opensaml.saml.saml2.core.Response;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.credentials.extractor.CredentialsExtractor;
import org.pac4j.core.exception.CredentialsException;
import org.pac4j.core.exception.HttpAction;
import org.pac4j.core.util.CommonHelper;
import org.pac4j.saml.client.SAML2Client;
import org.pac4j.saml.client.SAML2ClientConfiguration;
import org.pac4j.saml.context.SAML2MessageContext;
import org.pac4j.saml.credentials.SAML2Credentials;

public class SAML2CredentialsExtractor implements CredentialsExtractor<SAML2Credentials> {

	private SAML2ClientConfiguration configuration;
	private SAML2Client client;

	public SAML2CredentialsExtractor(SAML2ClientConfiguration configuration, SAML2Client client) {
		CommonHelper.assertNotNull("configuration", configuration);
		CommonHelper.assertNotNull("client", client);
		this.configuration = configuration;
		this.client = client;
	}

	@Override
	public SAML2Credentials extract(WebContext context) throws HttpAction, CredentialsException {
		final SAML2MessageContext samlContext = this.client.getContextProvider().buildContext(context);
		System.out.println("samlContext.getMessage():"+samlContext.getMessage());
		if (samlContext.getMessage() instanceof Response) {
			final SAML2Credentials credentials = (SAML2Credentials) this.client.getProfileHandler().receive(samlContext);
			credentials.setClientName(client.getName());
			return credentials;
		} else if (samlContext.getMessage() instanceof LogoutResponse) {
			return null;
		}
		return null;
	}

}
