package com.example.tutorial.plugins;

import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.event.issue.IssueEvent;
import com.atlassian.jira.event.type.EventType;
import com.atlassian.jira.issue.Issue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Simple JIRA listener using the atlassian-event library and demonstrating
 * plugin lifecycle integration.
 */
public class IssueCreatedResolvedListener implements InitializingBean, DisposableBean {

    private static final Logger log = LoggerFactory.getLogger(IssueCreatedResolvedListener.class);

    private final EventPublisher eventPublisher;

    /**
     * Constructor.
     * 
     * @param eventPublisher
     *            injected {@code EventPublisher} implementation.
     */
    public IssueCreatedResolvedListener(EventPublisher eventPublisher) {
	this.eventPublisher = eventPublisher;
    }

    /**
     * Called when the plugin has been enabled.
     * 
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
	// register ourselves with the EventPublisher
	eventPublisher.register(this);
    }

    /**
     * Called when the plugin is being disabled or removed.
     * 
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
	// unregister ourselves with the EventPublisher
	eventPublisher.unregister(this);
    }

    /**
     * Receives any {@code IssueEvent}s sent by JIRA.
     * 
     * @param issueEvent
     *            the IssueEvent passed to us
     */
    @EventListener
    public void onIssueEvent(IssueEvent issueEvent) {
	Long eventTypeId = issueEvent.getEventTypeId();
	Issue issue = issueEvent.getIssue();
	// if it's an event we're interested in, log it
	if (eventTypeId.equals(EventType.ISSUE_CREATED_ID)) {
	    log.info("Issue {} has been created at {}.", issue.getKey(),
		    issue.getCreated());
	} else if (eventTypeId.equals(EventType.ISSUE_RESOLVED_ID)) {
	    log.info("Issue {} has been resolved at {}.", issue.getKey(),
		    issue.getResolutionDate());
	} else if (eventTypeId.equals(EventType.ISSUE_CLOSED_ID)) {
	    log.info("Issue {} has been closed at {}.", issue.getKey(),
		    issue.getUpdated());
	}
    }
}