(ns customapi.config.middlewares
  (:require [malli.util :as mu]
            [muuntaja.core :as mc]
            [reitit.coercion.malli :as coercion-malli]
            [reitit.dev.pretty :as pretty]
            [reitit.openapi :as openapi]
            [reitit.ring.coercion :as coercion]
            [reitit.ring.middleware.exception :as exception]
            [reitit.ring.middleware.multipart :as multipart]
            ;; [spec-tools.spell :as spell]
            ;; [reitit.ring.middleware.dev :as dev]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [reitit.ring.middleware.parameters :as parameters]
            [reitit.ring.spec :as ring-spec]
            [reitit.swagger :as swagger]))

(def middlewares
  {;;:reitit.middleware/transform dev/print-request-diffs ;; pretty diffs
   :validate ring-spec/validate ;; enable spec validation for route data
   ;;:reitit.spec/wrap spell/closed ;; strict top-level validation
   :exception pretty/exception
   :data {:coercion (coercion-malli/create
                     {;; set of keys to include in error messages
                      :error-keys #{#_:type :coercion :in :schema :value :errors :humanized #_:transformed}
                      ;; schema identity function (default: close all map schemas)
                      :compile mu/closed-schema
                      ;; strip-extra-keys (affects only predefined transformers)
                      :strip-extra-keys true
                      ;; add/set default values
                      :default-values true
                      ;; malli options
                      :options nil})
          :muuntaja mc/instance
          :middleware [;; swagger & openapi
                       swagger/swagger-feature
                       openapi/openapi-feature
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
                       ;; multipart
                       multipart/multipart-middleware]}})

