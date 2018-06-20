call mvn clean compile
call mvn package
call cd target
call java -jar stackDocsJava-0.0.1-SNAPSHOT-jar-with-dependencies.jar