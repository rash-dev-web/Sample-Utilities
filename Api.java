package com.otherutil;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.xml.sax.SAXException;

public class Api {
	
	public static void restURI(String urit)
	{
		
		HttpUriRequest request = new HttpGet(urit);
		try
		{
			HttpResponse res= HttpClientBuilder.create().build().execute(request);
			Assert.assertEquals(res.getStatusLine().getStatusCode(),HttpStatus.SC_OK);
		}
		catch (ClientProtocolException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			
	}
	
	public static void testMIMEType(String uri,String expectedmimetype) throws ClientProtocolException, IOException
	{
		HttpUriRequest request = new HttpGet(uri);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		Assert.assertEquals(expectedmimetype,ContentType.getOrDefault(response.getEntity()).getMimeType());
	}
	
	public static void testContent(String uri,String element,String expectedvalue) throws SAXException, IOException, ParserConfigurationException
	{
			org.w3c.dom.Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(uri);
			org.w3c.dom.NodeList nl = doc.getElementsByTagName(element);
			
		Assert.assertEquals(expectedvalue,nl.item(0).getTextContent());
	}
}
