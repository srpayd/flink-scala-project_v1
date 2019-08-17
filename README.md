# Apache Flink with Scala
 
Apache Flink is an open source stream processing framework with powerful stream- and batch-processing capabilities.

Prerequisites for building Flink:

- java 8
- scala (version 2.11.8)
- maven (version 3.2.5)

```
git clone https://github.com/srpayd/flink-scala-project_v1.git
```

# Developing Flink

I used IntelliJ IDEA to develop the case that involves Scala code. 

Minimal requirements for an IDE were:

- Support for Java and Scala (also mixed projects)
- Support for Maven with Java and Scala

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

Now the easy part is putting our application inside a docker container. Once we build a Dockerfile and stored it under the same path of fat jar **out\artifacts\flink_scala_project\Dockerfile**.
```

#node'un official image'ini kullandım. versiyon 8
FROM java:8

#uygulama 8080 portunu kullaniyor
EXPOSE 8080

# Install build dependencies and flink
ADD /C:/Users/srpayd/flink-scala-project/out/artifacts/flink_scala_project_jar/flink_scala_project.jar flink_scala_project.jar

ENTRYPOINT ["java","-jar","flink_scala_project.jar"]


```
The last step is building a Docker image and run it inside the container. Run the below codes in the terminal while we are in the location Dockerfile saved.
```

docker build -f Dockerfile -t dockerdemo .

```
```

docker run -d --name flink_container dockerdemo 

```
