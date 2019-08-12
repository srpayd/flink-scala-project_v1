# Apache Flink with Scala
 
Apache Flink is an open source stream processing framework with powerful stream- and batch-processing capabilities.

Prerequisites for building Flink:

- java 8
- scala (version 2.11.8)
- maven (version 3.2.5)

```
git clone https://github.com/apache/flink.git
```

# Developing Flink

I used IntelliJ IDEA to develop the case that involves Scala code. 

Minimal requirements for an IDE were:

Support for Java and Scala (also mixed projects)
Support for Maven with Java and Scala

The regarding codes are located at the below paths.


Source | Location
------ | --------
data | data\case.csv
code | src\main\scala\package1\trendyol_case.scala
output | outputs\result.txt


# Dockerizing the Scala project

Steps for dockerize the project as listed below:
- Produce the fat jar file
- Define a Dockerfile
- Build a Docker image

Installed the most recent stable version of docker https://docs.docker.com/installation/

The first concern is creating a fat jar from the Scala project. We will end up with a fat jar located at the **out\artifacts\flink_scala_project\flink_scala_project.jar** path.

Now the easy part is putting our application inside docker, thus a Dockerfile is needed. *(Dockerfile is needed to store in where we saved the fat jar file.)*
```

#node'un official image'ini kullandım. versiyon 8
FROM java:8

#uygulama 8080 portunu kullaniyor
EXPOSE 8080

# Install build dependencies and flink
ADD /C:/Users/srpayd/flink-scala-project/out/artifacts/flink_scala_project_jar/flink_scala_project.jar flink_scala_project.jar

ENTRYPOINT ["java","-jar","flink_scala_project.jar"]


```
The last step is building a Docker image. Run the below code on cmd while I am in the path saved the Dockerfile. 
```

docker build -f Dockerfile -t dockerdemo .

```

# Details

