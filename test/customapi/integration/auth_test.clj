(ns customapi.integration.auth-test
  (:require [clojure.test :refer [deftest is testing]]
            [customapi.server :refer [app]]
            [ring.mock.request :refer [json-body request]]
            [clojure.data.json :as json]))

(deftest auth-test
  (testing "Login with credentials"
    (let [response (-> (request :post "/auth/login")
                       (json-body {:username "my-username" :password "a-password"})
                       app :body slurp
                       (json/read-str :key-fn keyword))]
      (is (contains? response :token))))

  (testing "Validate auth token"
    (let [response (-> (request :post "/auth/login") 
                       (json-body {:username "my-username" :password "a-password"})
                       app :body slurp 
                       (json/read-str :key-fn keyword))]
      (is (contains? response :token))
      (let [token (:token response)]
        (testing "GET"
          (let [get-response (-> {:request-method :get :uri "/auth/validate" :query-params {"auth-token" token}}
                                 app :body slurp
                                 (json/read-str :key-fn keyword))]

            (is (= (:username get-response) "my-username"))))))))
