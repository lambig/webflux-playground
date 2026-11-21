FROM golang:1.27.4-bookworm@sha256:4fa7c968ba93f1635670522dbb01a2c78150443e1385746924566079f36570ea AS gosu-build
WORKDIR /src
RUN git init . \
    && git remote add origin https://github.com/tianon/gosu.git \
    && git fetch --depth=1 origin 29b939aa5bd4dc588af765b8248f6e39150d2565 \
    && git checkout --detach 29b939aa5bd4dc588af765b8248f6e39150d2565
ENV CGO_ENABLED=0 GOTOOLCHAIN=local
RUN go get golang.org/x/sys@v0.50.0 \
    && go mod download && go mod verify \
    && go build -trimpath -o /out/gosu .

FROM mysql:8.4@sha256:a81d043930e51745522bd5c430961cf8286f0a23f0a9a2a6e0108f7ea71b0902
# The database service uses mysqld and the mysql client, not the optional Python shell.
RUN microdnf remove -y mysql-shell \
    && microdnf upgrade -y \
    && microdnf clean all
COPY --from=gosu-build /out/gosu /usr/local/bin/gosu
RUN gosu nobody true && mysqld --version
