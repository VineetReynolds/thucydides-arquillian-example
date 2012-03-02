# What is this ?

An example demonstrating the use of Thucydides and Arquillian.
The tests are run using the Thucydides testrunner, while Arquillian performs the deployment of archives defined in the tests.

# How do I run it ?

## Setup JBoss AS 7.1

Download and extract the JBoss AS 7.1 distribution from [jboss.org](http://download.jboss.org/jbossas/7.1/jboss-as-7.1.0.Final/jboss-as-7.1.0.Final.zip).

Create or replace the value of the environment variable `JBOSS_HOME` to the location of the JBoss installation. This is used by Arquillian to locate the JBoss installation that it would manage.

## Run the tests

Run the test in the project using: `mvn verify`.
The Thucydides reports would be generated in the `target/site` sub-directory.