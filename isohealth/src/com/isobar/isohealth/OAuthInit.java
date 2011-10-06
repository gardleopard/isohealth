package com.isobar.isohealth;

import java.util.Scanner;

import net.smartam.leeloo.client.OAuthClient;
import net.smartam.leeloo.client.URLConnectionClient;
import net.smartam.leeloo.client.request.OAuthClientRequest;
import net.smartam.leeloo.client.response.OAuthJSONAccessTokenResponse;
import net.smartam.leeloo.common.message.types.GrantType;

/**
 * Class to initiate OAuth authorization.
 * Visit main method for the usage
 * @author dfernandes
 *
 */
public class OAuthInit {
	private OAuthClientRequest request;
	private String clientId;
	private String clientSecretId;
	private String redirectUri;
	
	public OAuthInit(String clientId, String clientSecretId, String redirectUri) {
		try {
			this.clientId = clientId;
			this.clientSecretId = clientSecretId;
			this.redirectUri = redirectUri;
			
			request = OAuthClientRequest
				.authorizationLocation(GraphConstants.AUTH_URL)
				.setClientId(clientId)
				.setRedirectURI(GraphConstants.REDIRECT_URI)
				.buildQueryMessage();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String authorizationUrl() {
		String url = "";
		try {
			url = request.getLocationUri() + "&response_type=code";
		}catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
	
	public String getAccessToken(String code) {
		String accessToken = "";
		try {
		request = OAuthClientRequest
				.tokenLocation(
						GraphConstants.ACCESS_URL)
				.setGrantType(GrantType.AUTHORIZATION_CODE)
				.setClientId(this.clientId)
				.setClientSecret(this.clientSecretId)
				.setRedirectURI(this.redirectUri)
				.setCode(code).buildBodyMessage();

		// create OAuth client that uses custom http client under the hood
		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
		
		OAuthJSONAccessTokenResponse response = oAuthClient.accessToken(request);
		
        accessToken = response.getAccessToken();
        String expiresIn = response.getExpiresIn();
        
        System.out.println("Access Token: " + accessToken + " will expire in " + expiresIn);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}
	
	public static void main(String[] args) {
		OAuthInit oauthInit = new OAuthInit(GraphConstants.CLIENT_ID, GraphConstants.CLIENT_SECRET, GraphConstants.REDIRECT_URI);
		
		System.out.println(oauthInit.authorizationUrl());
		System.out.println("\n\nCopy url in browser, Allow the access to account and enter the received access token here:");
		Scanner read = new Scanner(System.in);
		String code = read.next();
		oauthInit.getAccessToken(code);
	}
}
