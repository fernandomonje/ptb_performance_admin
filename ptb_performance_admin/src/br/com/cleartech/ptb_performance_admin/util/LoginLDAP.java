package br.com.cleartech.ptb_performance_admin.util;

import java.util.*;
import javax.naming.*;
import javax.naming.directory.*;

public class LoginLDAP {
	private String LDAPUrl;
	private String principalName;
	private String domainName;
	private boolean userAuth;
	private final String securityGroup = "Cleartech";

	public LoginLDAP() {
		this.userAuth = false;
		this.LDAPUrl = "ldaps://saopaulo.ctech.local:3269";
		this.domainName = "ctech.local";

		if (this.domainName == null || "".equals(this.domainName)) {
			int delim = this.principalName.indexOf('@');
			this.domainName = this.principalName.substring(delim + 1);
		}
	}

	public boolean authUser(String username, String password) throws Exception {
		this.principalName = username + "@cleartech.com.br";
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		props.put(Context.PROVIDER_URL, this.LDAPUrl);
		props.put(Context.SECURITY_PRINCIPAL, this.principalName);
		props.put(Context.SECURITY_CREDENTIALS, password); // secretpwd
		if (this.LDAPUrl.toUpperCase().startsWith("LDAPS://")) {
			props.put(Context.SECURITY_PROTOCOL, "ssl");
			props.put(Context.SECURITY_AUTHENTICATION, "simple");
			props.put("java.naming.ldap.factory.socket",
					"br.com.cleartech.ptb_performance_admin.util.LDAPSSLHelperSocketFactory");
		}
		InitialDirContext context = new InitialDirContext(props);
		try {
			SearchControls ctrls = new SearchControls();
			ctrls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			NamingEnumeration<SearchResult> results = context.search(toDC(this.domainName),
					"(& (userPrincipalName=" + this.principalName + ")(objectClass=user))", ctrls);
			if (!results.hasMore())
				throw new AuthenticationException("Principal name not found");

			SearchResult result = results.next();

			Attribute memberOf = result.getAttributes().get("memberOf");

			if (memberOf != null) {
				for (int idx = 0; idx < memberOf.size(); idx++) {
					Attribute att = context.getAttributes(memberOf.get(idx).toString(), new String[] { "CN" })
							.get("CN");
					if (att.get().toString().equals(this.securityGroup)) {
						this.userAuth = true;
						return this.userAuth;
					}
				}
			}
		} finally {
			try {
				context.close();
			} catch (Exception ex) {
			}

		}
		return this.userAuth;
	}

	/**
	 * Create "DC=sub,DC=mydomain,DC=com" string
	 * 
	 * @param domainName sub.mydomain.com
	 * @return
	 */
	private static String toDC(String domainName) {
		StringBuilder buf = new StringBuilder();
		for (String token : domainName.split("\\.")) {
			if (token.length() == 0)
				continue;
			if (buf.length() > 0)
				buf.append(",");
			buf.append("DC=").append(token);
		}
		return buf.toString();
	}

}