SupportSim
==========

This project has now split into two different branches.

The master branch is still SupportSim, a Java app that simulates a helpdesk environemnt (Ticket generation, assignment, and completion etc).

It uses:
Hibernate ORM for persistence.
Spring for scheduling/polling mechanisms.
JSP (scriplets currently, but due to be re-worked) for a frontend.
Boostrap for the webpage layout/styling.

The ZD branch is quite different, leveraging the existing framework of SupportSim it is intended to become a functional frontend that provides 'at a glance' information for Zendesk via the RESTful API.

This is made possible by the Zendesk Java Client written by stephenc/cloudbees

https://github.com/cloudbees/zendesk-java-client


I'll be aming to write a few entries regarding this on tombeadman.com
