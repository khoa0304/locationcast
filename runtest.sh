#!/bin/sh
mvn test

# Convert .xml reports into .html report, but without the CSS or images
mvn surefire-report:report-only

# Put the CSS and images where they need to be without the rest of the
# time-consuming stuff
mvn site -DgenerateReports=false


firefox ~/git/locationcast/target/site/surefire-report.html &
