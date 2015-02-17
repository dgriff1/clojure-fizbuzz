(ns clojure-fizbuzz.handler
  (:require [ring.util.response :as response]
            [compojure.response :as comp-response] ))

; extend renderable so we can return booleans
(extend-protocol comp-response/Renderable
  Boolean
  (render [body _]
    (response/response body)))


(def all-numbers (agent (list)))

(defn get-numbers []
  @all-numbers)

(defn  set-number [n]
  (send all-numbers conj n))

(defn has-number [n]
  (->>
    @all-numbers
      (some #(= n %))
      boolean))

(defn is-even? [n]
  (even? (Integer. n)))
