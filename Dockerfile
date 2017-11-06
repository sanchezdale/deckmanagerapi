FROM openjdk:8
ADD /build/libs/deckmanagerserver-0.0.1-SNAPSHOT.jar app.jar
ADD /src/main/resources/indexes indexes
ENV AWS_CLIENT="AKIAI3F6TDXBFEHCUVYA"
ENV AWS_SECRET="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"
ENV SLACK_CLIENT="256053771665.256571390690"
ENV SLACK_SECRET="8353d9b2f45c43480b862b2ea291b1e7"
ENV SLACK_VERIFICATION="XDeLuOXCiIIskPX7nZno1CNB"
EXPOSE 8080
RUN bash -c 'touch /app.jar'
CMD ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
#ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]