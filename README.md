JIRA Plugin JaCoCO Example
==========================

This project demonstrate how to integrate JaCoCo code coverage with a sample JIRA plugin.
It covers unit and integration tests.
In this example each type of tests generates a different report.
The JIRA plugin code is based on the tutorial provided by Atlassian for JIRA Event Listener. You can check the original code on the links below.

## Links

- JIRA Plugin Guide - https://developer.atlassian.com/display/JIRADEV/JIRA+Plugin+Guide
- Atlassian SDK Working with Maven - https://developer.atlassian.com/display/DOCS/Working+with+Maven
- Event Listeners - https://developer.atlassian.com/display/JIRADEV/Writing+JIRA+Event+Listeners+with+the+atlassian-event+Library
- JIRA Event Listener Tutorial - https://bitbucket.org/atlassian_tutorial/jira-event-listener
- JaCoCo - http://www.eclemma.org/jacoco/

## How to build

Enter the following:

	$ git clone https://github.com/cristianonicolai/jira-jacoco-example.git
	$ cd jira-jacoco-example
	$ mvn clean install

## Sample report

![JaCoCo Report](/jacoco-report.png)

## Checking coverage level

When using JaCoCo you can also define a series of rules to difine quality criterias for your project.
When running it via Maven, you can make sure that no rules are violated.
In this project, I created a separated profile that runs all check. To run it, please execute:

	$ mvn clean install -P jacoco-check
