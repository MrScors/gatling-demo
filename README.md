gatling-maven-demo
=========================

Simple showcase of a maven project using the gatling-maven-plugin.

To test it out, simply start SpringBoot application and execute the following command:

    $mvn gatling:test@health-check gatling:test@health-check-report
    $mvn gatling:test@notes gatling:test@notes-report

or simply:

    $mvn gatling:test
