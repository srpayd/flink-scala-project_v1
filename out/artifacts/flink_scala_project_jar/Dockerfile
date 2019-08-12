#node'un official image'ini kullandım. versiyon 8
FROM java:8

#uygulama 8080 portunu kullaniyor
EXPOSE 8080

# Install build dependencies and flink
ADD /C:/Users/srpayd/flink-scala-project/out/artifacts/flink_scala_project_jar/flink_scala_project.jar flink_scala_project.jar

ENTRYPOINT ["java","-jar","flink_scala_project.jar"]
