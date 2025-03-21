(ns customapi.integration.clothes-routes
  (:require [clojure.data.json :as json]
            [clojure.test :refer [deftest is testing]]
            [customapi.db.core :refer [initialize-db!]]
            [customapi.server :refer [app]]
            [ring.mock.request :refer [json-body request]]))

(initialize-db!)

(deftest test-clothes-routes
  (testing "Get initial list of clothes"
    (let [response (-> (request :get "/clothes")
                       app :body slurp
                       (json/read-str :key-fn keyword))]
      (is (= "Clothes not found" (:error response)))))

  (testing "Post a cloth"
    (let [post-response (-> (request :post "/clothes")
                            (json-body {:name "l-shirt" :type "fancy" :size 12})
                            app :body slurp
                            (json/read-str :key-fn keyword))]
      (is (contains? post-response :uuid))
      (is (= (:name post-response) "l-shirt"))
      (is (= (:type post-response) "fancy"))
      (is (= (:size post-response) 12.0))

      (let [cloth-uuid (:uuid post-response)]
        (testing "Get that cloth"
          (let [get-response (-> {:request-method :get :uri (str "/clothes/" cloth-uuid)}
                                 app :body slurp
                                 (json/read-str :key-fn keyword))]

            (is (contains? get-response :uuid))
            (is (= (:uuid get-response) cloth-uuid))
            (is (= (:name get-response) "l-shirt"))
            (is (= (:type get-response) "fancy"))
            (is (= (:size get-response) 12.0))))

        (testing "Patch that cloth"
          (let [patch-response (-> {:request-method :patch :uri (str "/clothes/" cloth-uuid)}
                                   (json-body {:name "xl-shirt" :type "fancy" :size 14})
                                   app :body slurp
                                   (json/read-str :key-fn keyword))]

            (is (contains? patch-response :uuid))
            (is (= (:uuid patch-response) cloth-uuid))
            (is (= (:name patch-response) "xl-shirt"))
            (is (= (:type patch-response) "fancy"))
            (is (= (:size patch-response) 14.0))))

        (testing "Delete that cloth"
          (let [delete-response (-> {:request-method :delete :uri (str "/clothes/" cloth-uuid)}
                                    app :body slurp
                                    (json/read-str :key-fn keyword))]
            (is (= delete-response {})))))))

  (testing "Post two clothes"
    (let [first-post-response (-> (request :post "/clothes")
                                  (json-body {:name "t-hemd" :type "common" :size 9})
                                  app :body slurp
                                  (json/read-str :key-fn keyword))
          second-post-response (-> (request :post "/clothes")
                                   (json-body {:name "s-kjol" :type "natural" :size 11})
                                   app :body slurp
                                   (json/read-str :key-fn keyword))]
      (is (contains? first-post-response :uuid))
      (is (= (:name first-post-response) "t-hemd"))
      (is (= (:type first-post-response) "common"))
      (is (= (:size first-post-response) 9.0))
      (is (contains? second-post-response :uuid))
      (is (= (:name second-post-response) "s-kjol"))
      (is (= (:type second-post-response) "natural"))
      (is (= (:size second-post-response) 11.0))

      (testing "Retrieve two clothes"
        (let [get-response (-> (request :get "/clothes")
                               app :body slurp
                               (json/read-str :key-fn keyword))]
          (is (vector? get-response))
          (is (= 2 (count get-response)))))

      (testing "Delete two clothes"
        (let [first-uuid (:uuid first-post-response)
              second-uuid (:uuid second-post-response)
              first-delete-response (-> {:request-method :delete :uri (str "/clothes/" first-uuid)}
                                        app :body slurp
                                        (json/read-str :key-fn keyword))
              second-delete-response (-> {:request-method :delete :uri (str "/clothes/" second-uuid)}
                                         app :body slurp
                                         (json/read-str :key-fn keyword))]
          (is (= first-delete-response {}))
          (is (= second-delete-response {}))))

      (testing "Get empty list of clothes"
        (let [response (-> (request :get "/clothes")
                           app :body slurp
                           (json/read-str :key-fn keyword))]
          (is (= "Clothes not found" (:error response))))))))
