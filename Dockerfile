ARG OPENJDK_TAG=8u292
ARG SBT_VERSION=1.6.0

FROM mozilla/sbt

COPY . ./src
WORKDIR ./src
RUN sbt test