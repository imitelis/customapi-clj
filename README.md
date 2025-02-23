# customapi

Just a silly CustomAPI project making use of Clojure specific libraries to build a service layered backend that aims to be as similar as possible (both in architecture and experience) to FastAPI.

## Installation

  *  Check `java` is installed with `java -version` (default version: `openjdk version "21.0.5"`)
  *  Check `lein` is installed with `lein -version` (default version: `Leiningen 2.11.2 on Java 21.0.5 OpenJDK`)
  *  Run `lein deps` to install all required dependencies listed in `project.clj`
  *  Run `lein run` to start server at `localhost:3000` by default

## Usage

  *  Check `docker` is installed with `docker -version` (default version: `Docker version 27.5.1`)
  *  Run `lein uberjar` to compile project and get a `/target/uberjar/customapi-0.1.0-standalone.jar` file
  *  Build a docker image for running the `.jar` with `docker build -t customapi .`
  *  Run the docker container afer building its image with `docker run -p 3000:3000 customapi`

## Options

  *  `lein run`: Start the server on default settings
  *  `lein deps`: Check project dependencies and install them
  *  `lein clean`: Clean dependencies libraries and cache
  *  `lein lint`: Lint `clojure` code and fix linting issues
  *  `lein uberjar`: Compile project into a portable `.jar` file

## Features

  *  Logger: Making use of `slf4j` lib and just for chosen logs via `/resources/logback.xml`
  *  Migrations: Automatically set database tables and connection via `/src/customapi/config/database.clj` 
  *  Middlewares: Fully integrated customizable middlewares via `/src/customapi/config/middleware.clj`
  *  Modularity: Segregation of layers; customizable and sustainable folders organization
  *  OpenAPI: Self-documenting and interacteable API endpoints at `localhost:3000/index.html`

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
