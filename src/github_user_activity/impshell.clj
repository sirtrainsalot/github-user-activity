(ns github-user-activity.impshell
  (:require [clojure.data.json :as json])
  (:gen-class))

;; konstanter
(def user-name "sirtrainsalot")
(def github-url "https://api.github.com/users/%s/events")


;; hent data om bruker fra GitHub API
;;
;; håndtere feil, som invalid username eller API failures
(defn get-user-events [username]
  (->> (format github-url username)
       (slurp)
       (json/read)
       ))

(slurp (format github-url "sirtrainsalot"))

(get-user-events "sirtrainsalot")

;; send data om bruker til fk-core.print-user


;; les in username som argument i terminal github-activity <username>
(defn -main [& args]
  (let [[username & rest] args]
    (println "username:" username)

    ))