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
   [ring.logger :as logger]
   [ring.middleware.json :as json]))

(def set-middleware
  [;; swagger feature
   swagger/swagger-feature
   ;; query-params & form-params
   parameters/parameters-middleware
   ;; content-negotiation
   muuntaja/format-negotiate-middleware
   ;; encoding response body
   muuntaja/format-response-middleware
   ;; coercing response body
   coercion/coerce-response-middleware
   ;; coercing request body
   coercion/coerce-request-middleware
   ;; exception handling
   exception/exception-middleware
   ;; decoding request body
   muuntaja/format-request-middleware
   ;; logger wrapper
   logger/wrap-with-logger
   ;; json body wrapper
   json/wrap-json-body
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