FROM openjdk:17-slim-bullseye as builder

RUN jlink --module-path /opt/java/openjdk/jmods --compress=2 \
    --add-modules java.base,java.scripting,java.logging,java.desktop,java.sql,java.xml,jdk.zipfs \
    --no-header-files --no-man-pages --output /opt/minimaljre

FROM debian:bullseye-slim

ARG VERSION="3.0.0-alpha-26"

LABEL org.opencontainers.image.authors="Haruaki Tamada <tamada@users.noreply.github.com>" \
      org.opencontainers.image.url="https://github.com/tamada/pochi3" \
      org.opencontainers.image.documentation="Detecting software theft, the birthmarking toolkit for the Java platform." \
      org.opencontainers.image.source="https://raw.githubusercontent.com/tamada/pochi3/blob/main/dockers/minimalgrl/Dockerfile" \
      org.opencontainers.image.version="${VERSION}"

RUN adduser --disabled-login --disabled-password --no-create-home nonroot \
    && mkdir -p /opt/pochi3/libs
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
