(ns customapi.integration.math-test
  (:require [clojure.test :refer [deftest is testing]]
            [customapi.server :refer [app]]
            [ring.mock.request :refer [json-body request]]))

(deftest math-test
  (testing "Sum 2 numbers with query-params"
    (is (= (-> (request :get "/math/plus?x=20&y=3")
               app :body slurp)
           (-> {:request-method :get :uri "/math/plus" :query-string "x=20&y=3"}
               app :body slurp)
           (-> {:request-method :get :uri "/math/plus" :query-params {:x 20 :y 3}}
               app :body slurp)
           "{\"total\":23}")))

  (testing "Sum 2 numbers with body params"
    (is (= (-> (request :post "/math/plus") (json-body {:x 40 :y 2})
               app :body slurp)
           (-> {:request-method :post :uri "/math/plus" :body-params {:x 40 :y 2}}
               app :body slurp)
           "{\"total\":42}"))))