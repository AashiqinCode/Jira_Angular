package com.bnp.activityMonitor.Common;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.ProjectRestClient;
import com.atlassian.jira.rest.client.api.UserRestClient;
import com.atlassian.jira.rest.client.api.domain.Project;
import com.atlassian.jira.rest.client.api.domain.User;
import com.atlassian.jira.rest.client.api.domain.Version;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

import io.atlassian.util.concurrent.Promise;

public class DataExtractor {
	
	

	public User getJiraDetails() throws URISyntaxException, InterruptedException, ExecutionException {
		UserRestClient client=getJRC().getUserClient();
		Promise<User> promuser=client.getUser("460020");
		User user=promuser.get();
		return user;
	}

	private JiraRestClient getJRC() throws URISyntaxException {

		AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
		URI baseURI=new URI("https://onlineprogrammer.atlassian.net");
		JiraRestClient jrc=factory.createWithBasicHttpAuthentication(baseURI, "online.programmer@gmail.com", "Secure@1");
		return jrc;
	}

	public List<Version> getJiraProjectDetails() throws URISyntaxException, InterruptedException, ExecutionException {
		ProjectRestClient pClient=getJRC().getProjectClient();
		Promise<Project> project=pClient.getProject("OMSA");
		Iterable<Version> versions=project.get().getVersions();
		Iterator<Version> itr=versions.iterator();
		List<Version> versionList=new ArrayList<>();
		while(itr.hasNext()) {
			versionList.add(itr.next());
		}
		return versionList;
	}
	
	
	
	

}
