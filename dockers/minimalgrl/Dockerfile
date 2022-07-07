FROM ghcr.io/graalvm/jdk:ol8-java17-22.1.0 as builder

RUN jlink --module-path $JAVA_HOME/jmods --compress=2 \
    --add-modules java.base,java.scripting,java.logging,java.desktop,java.sql,java.xml,jdk.zipfs,org.graalvm.js.scriptengine,org.graalvm.sdk,org.graalvm.truffle \
    --no-header-files --no-man-pages --output /opt/minigrl

FROM ubuntu:cosmic

ARG VERSION="3.0.0-alpha-23"

LABEL org.opencontainers.image.authors="Haruaki Tamada <tamada@users.noreply.github.com>" \
      org.opencontainers.image.url="https://github.com/tamada/pochi3" \
      org.opencontainers.image.documentation="Detecting software theft, the birthmarking toolkit for the Java platform." \
      org.opencontainers.image.source="https://raw.githubusercontent.com/tamada/pochi3/blob/main/dockers/minimalgrl/Dockerfile" \
      org.opencontainers.image.version="${VERSION}"

RUN  echo '/bin/false' > /etc/shells \
  && adduser --shell /bin/false --disabled-login --disabled-password --no-create-home appuser

COPY --from=builder /opt/minigrl /opt/minigrl
ADD build/libs /opt/pochi3/libs

ENV POCHI3_HOME=/opt/pochi3
ENV JAVA_HOME=/opt/minigrl
ENV PATH=$JAVA_HOME/bin:$PATH
ENV HOME=/app
ENV POCHI3_VERSION=${VERSION}

WORKDIR /app
USER appuser

ENTRYPOINT [ "java", "-p", "/opt/pochi3/libs", "-m", "jp.cafebabe.pochi.cli/jp.cafebabe.pochi.cli.Main" ]


