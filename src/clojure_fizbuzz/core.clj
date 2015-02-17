(ns clojure-fizbuzz.core
  (:use compojure.core [ring.adapter.jetty :only [run-jetty]] )
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.middleware.json :as middleware]
            [clojure-fizbuzz.handler :as fizbuzz])
  (:gen-class))


(defroutes main-routes
  (GET "/fizbuzz/" [] (fizbuzz/get-numbers))
  (GET "/fizbuzz/:number" [number] (fizbuzz/has-number number))
  (PUT "/fizbuzz/:number" [number] (fizbuzz/set-number number))
  (GET "/fizbuzz/even/:number" [number] (fizbuzz/is-even? number)))


(defn test-middleware [ handler ]
  (fn [m]
    (let [response (handler m) body (:body response)]
            (if
              (not (coll? body))
                (assoc response :body {:response body})
                response))))


(def app
  (-> (handler/api main-routes)
      test-middleware
      middleware/wrap-json-response))


(defn -main
  [port]
  (run-jetty app {:port (Integer. port)}))
