# customapi

Just a silly CustomAPI project making use of Clojure specific libraries to build a service layered backend that aims to be as similar as possible (both in architecture and experience) to FastAPI.

## Installation

  *  Check `java` is installed with `java -version` (`openjdk version "21.0.5"`)
  *  Check `lein` is installed with `lein -version` (`Leiningen 2.11.2 on Java 21.0.5 OpenJDK`)
  *  Run `lein deps` to install all required dependencies listed in `project.clj`
  *  Run `lein run` to start server at `localhost:3000` by default

## Usage

  *  Check `docker` is installed with `docker -version` (`Docker version 27.5.1`)
  *  Run `lein uberjar` to compile project and get a `/target/uberjar/customapi-0.1.0-standalone.jar` file
  *  Build a docker image for running the `.jar` with `docker build -t customapi .`
  *  Run the docker container afer building its image with `docker run -p 3000:3000 customapi`

## Environments
  *  Set the testing environment using `export ENV=test` via terminal
  *  After running all tests, use `unset ENV` to point the `config.edn` database

## Options

  *  `lein run`: Start the server on default settings
  *  `lein deps`: Check project dependencies and install them
  *  `lein clean`: Clean dependencies libraries and cache
  *  `lein lint`: Spot `clojure` code from linting issues
  *  `lein lint-fix`: Fix `clojure` code from linting issues
  *  `lein test`: Run whole project unit and integration tests
  *  `lein test :ns`: Run an specific test passing its namespace
  *  `lein uberjar`: Compile project into a portable `.jar` file

## Features

  *  Logger: Making use of `slf4j` logs and just for specified ones via `/resources/logback.xml`
  *  Migrations: Automatically set database tables and connection via `/src/customapi/db/core.clj` 
  *  Middlewares: Fully integrated pickeable middlewares via `/src/customapi/config/middlewares.clj`
  *  Modularity: Segregation of layers; customizable and sustainable folders organization
  *  Secrets: Dynamically pass secrets and config as an EDN file at `/config.edn`
  *  JWT: Encrypt JSONs in tokens and then decrypt them for authentication via `/src/customapi/config/jwt.clj`
  *  OAuth: Middleware for secured API endpoints with interactive `auth-header` via `/src/customapi/config/middlewares.clj`
  *  OpenAPI: Self-documenting and interacteable API endpoints at `localhost:3000/index.html`
  *  RestAPI: Resftul API endpoints fulfilling CRUD operations for a `/src/customapi/schemas/clothes.clj` schema
  *  Tests: Validate `schemas` with unit tests and `routes` with integration tests via `/test/customapi` respectively

## License

Copyright © 2025 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
