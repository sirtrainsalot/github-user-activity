(ns github-user-activity.impshell
  (:require [clojure.data.json :as json]
            [github-user-activity.fun_core :refer [get-username
                                                   format-activities]])
  (:import [java.net URI HttpURLConnection]
           [java.io InputStreamReader BufferedReader])
  (:gen-class))

(defn get-url [username]
  (->> (str "https://api.github.com/users/" username "/events")
       (URI.)
       (.toURL)))

(defn get-json [connection]
  (with-open [reader (BufferedReader. (InputStreamReader. (.getInputStream connection)))]
    (json/read reader :key-fn keyword))
  )


(defn fetch-activities [username]
  (try
    (let [url (get-url username)
          conn ^HttpURLConnection (.openConnection url)]
      (doto conn
        (.setRequestMethod "GET")
        (.setRequestProperty "User-Agent" "Clojure-GitHub-CLI")) ;; GitHub krever User-Agent
      (let [status (.getResponseCode conn)]
        (case status
          200 (get-json conn)
          404 ["❌ Bruker ikke funnet."]
          [(str "❌ Uventet responskode: " status)])))))


(defn -main [& args]
  (let [activities (->> (get-username args)
                    (fetch-activities)
                    (format-activities)
                    )]
    (if (seq activities)
      (doseq [a (take 10 activities)]
        (println "- " a))
      (println "No events found our failed to parse data."))))



