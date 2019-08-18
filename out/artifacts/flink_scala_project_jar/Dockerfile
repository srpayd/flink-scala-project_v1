#node'un official image'ini kullandım. versiyon 8
FROM java:8

#uygulama 8080 portunu kullaniyor
EXPOSE 8080

# Install build dependencies and flink
ADD flink-scala-project.jar flink-scala-project.jar

ENTRYPOINT ["java","-jar","flink-scala-project.jar"]
