package com.example.openhtmltopdf;

import java.net.URI;
import java.net.URISyntaxException;

import com.openhtmltopdf.extend.FSUriResolver;
import com.openhtmltopdf.swing.NaiveUserAgent;

public class MyResolver implements FSUriResolver {
	final NaiveUserAgent.DefaultUriResolver defaultUriResolver = new NaiveUserAgent.DefaultUriResolver();
//	  @Override
	  public String resolveURI(String baseUri, String uri) {
	    System.out.println("URI--- " + uri);
	    String supResolved = defaultUriResolver.resolveURI(baseUri, uri);
	    if (supResolved == null || supResolved.isEmpty())
	      return null;
	    
	    try {
	      URI uriObj = new URI(supResolved);
	      //System.out.println("resolveURI..." + uriObj.toString());
	      return uriObj.toString();
	    }catch (URISyntaxException e) {
	      e.printStackTrace();
	    }
	    return null;
	  }
}
