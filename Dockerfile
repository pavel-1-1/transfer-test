FROM ubuntu:latest
LABEL authors="servak"

ENTRYPOINT ["top", "-b"]