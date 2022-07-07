FROM openjdk:17-slim-bullseye as builder

RUN ["jlink", "--compress=2", \
     "--module-path", "/opt/java/openjdk/jmods", \
     "--no-header-files", "--add-modules", \
     "java.base,java.scripting,java.logging,java.desktop,java.sql,java.xml,jdk.zipfs", \
     "--no-man-pages", "--output", "/opt/minimaljre"]

FROM debian:bullseye-slim

RUN adduser --no-create-home --disabled-password --disabled-login nonroot

COPY --from=builder /opt/minimaljre /opt/minijre
ADD build/libs /opt/pochi3/libs

ENV POCHI3_HOME=/opt/pochi3
ENV JAVA_HOME=/opt/minijre
ENV PATH=$JAVA_HOME/bin:$PATH
ENV HOME=/app
ENV POCHI3_VERSION=${VERSION}

WORKDIR /app
USER nonroot

ENTRYPOINT [ "java", "-p", "/opt/pochi3/libs", "-m", "jp.cafebabe.pochi.cli/jp.cafebabe.pochi.cli.Main" ]
