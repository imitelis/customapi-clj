(ns customapi.config.middleware
  (:require
   [muuntaja.core :as muuntaja-core]
   [reitit.coercion.spec :as coercion-spec]
   [reitit.dev.pretty :as pretty]
   [reitit.ring.coercion :as coercion]
   [reitit.ring.middleware.exception :as exception]
   [reitit.ring.middleware.multipart :as multipart]
   [reitit.ring.middleware.muuntaja :as muuntaja]
   [reitit.ring.middleware.parameters :as parameters]
   [reitit.swagger :as swagger]
   [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
   [ring.logger :as logger]))

(def set-middleware
  [;; swagger feature
   swagger/swagger-feature
   ;; query-params & form-params
   parameters/parameters-middleware
   ;; content-negotiation
   muuntaja/format-negotiate-middleware
   ;; encoding response body
   muuntaja/format-response-middleware
   ;; exception handling
   exception/exception-middleware
   ;; decoding request body
   muuntaja/format-request-middleware
   ;; coercing response bodys
   coercion/coerce-response-middleware
   ;; coercing request parameters
   coercion/coerce-request-middleware
   ;; logger wrapping
   logger/wrap-with-logger
   ;; [wrap-json-response]
   wrap-json-body
   wrap-json-response
   ;; multipart middleware
   multipart/multipart-middleware])

(def use-middleware
  {;;:reitit.middleware/transform dev/print-request-diffs ;; pretty diffs
   ;;:validate spec/validate ;; enable spec validation for route data
   ;;:reitit.spec/wrap spell/closed ;; strict top-level validation
   :exception pretty/exception
   :data {:coercion coercion-spec/coercion
          :muuntaja muuntaja-core/instance
          :middleware set-middleware}})